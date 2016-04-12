package erp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Order;
import com.taobao.api.domain.Trade;
import com.taobao.api.domain.User;
import com.taobao.api.request.TradeFullinfoGetRequest;
import com.taobao.api.request.TradeMemoUpdateRequest;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.response.TradeFullinfoGetResponse;
import com.taobao.api.response.TradeMemoUpdateResponse;
import com.taobao.api.response.TradesSoldGetResponse;
import com.taobao.api.response.UserGetResponse;

import erp.bean.TaobaoBean;
import erp.model.Sell;
import erp.model.SellItem;
import erp.model.Ware;
import erp.service.ExpressService;
import erp.service.SellItemService;
import erp.service.SellService;
import erp.service.WareService;

public class TradeImportAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(TradeImportAction.class);
	
	private ExpressService expressService = null;
	private SellService sellService = null;
	private SellItemService sellItemService = null;
	private WareService wareService = null;
	
	private TaobaoBean taobao;
	private Map session;

	// list()
	ArrayList<Trade> trades;

	// session_callback()
	private String top_appkey;
	private String top_parameters;
	private String top_session;
	private String top_sign;
	
	// input()
	private String tids;
	ArrayList<Order> orders;
	
	private Date created;
	private String buyerNick;
	private String payment;
	private String postFee;
	private String totalFee;
	private String discountFee;
	private String adjustFee;
	private String buyerMessage;
	private String buyerMemo;
	private String sellerMemo;
	private String receiverName;
	private String receiverAddress;
	private String receiverPhone;
	private String receiverMobile;
	private String receiverZip;
	
	List<String> deliverDateOption;
	Map<Integer, String> expressOption;
	
	// import()
	private int sellId;
	private int expressId;
	private String commentExpress;
	private String commentInvoice;
	private String postFeeReal;
	
	// import_item()
	private int wareId;
	private double price;
	private int number;
	private int id;
	
	public String list() throws Exception {
		try {
			TaobaoClient client = new DefaultTaobaoClient(taobao.getRestUrl(),
					taobao.getAppKey(), taobao.getAppSecret());

			// getUser(client, "timonlio");
			printSession();

			TradesSoldGetRequest tradeSoldReq = new TradesSoldGetRequest();		
			tradeSoldReq.setStatus("WAIT_SELLER_SEND_GOODS");
			tradeSoldReq.setFields("tid,"
					+ "created,"
					+ "buyer_nick,"
					+ "total_fee,"
					+ "discount_fee," 
					+ "adjust_fee,"
					+ "post_fee,"
					+ "payment,"
					+ "receiver_name,"
					+ "receiver_address,"
					+ "receiver_city,"
					+ "receiver_district,"
					+ "receiver_mobile,"
					+ "receiver_phone,"
					+ "receiver_state,"
					+ "receiver_zip,"
					+ "orders");

			TradesSoldGetResponse tradeSoldResp = client.execute(tradeSoldReq, (String)session.get("sess_top_session"));
			logger.info("totalResults:" + tradeSoldResp.getTotalResults());

			if (tradeSoldResp.getTotalResults() > 0) {
				this.trades = (ArrayList<Trade>) tradeSoldResp.getTrades();
				for (Trade t : trades) {
					logger.info("trade.buyer_nick:" + t.getBuyerNick());
					logger.info("trade.created:" + t.getCreated());
					logger.info("trade.tid:" + t.getTid());
					logger.info("trade.total_fee:" + t.getTotalFee());
					logger.info("trade.discount_fee:" + t.getDiscountFee());
					logger.info("trade.adjust_fee:" + t.getAdjustFee());
					logger.info("trade.post_fee:" + t.getPostFee());
					logger.info("trade.payment:" + t.getPayment());
					
					logger.info("trade.receiver_name:" + t.getReceiverName());
					logger.info("trade.receiver_address:" + t.getReceiverAddress());
					logger.info("trade.receiver_city:" + t.getReceiverCity());
					logger.info("trade.receiver_district:" + t.getReceiverDistrict());
					logger.info("trade.receiver_mobile:" + t.getReceiverMobile());
					logger.info("trade.receiver_phone:" + t.getReceiverPhone());
					logger.info("trade.receiver_state:" + t.getReceiverState());
					logger.info("trade.receiver_zip:" + t.getReceiverZip());

					ArrayList<Order> orders = (ArrayList<Order>) t.getOrders();
					logger.info("trade.orders.length:" + orders.size());

					for (Order o : orders) {
						logger.info("order.title:" + o.getTitle());
						logger.info("order.num:" + o.getNum());
						logger.info("order.price:" + o.getPrice());
						logger.info("order.num_iid:" + o.getNumIid());
						logger.info("order.outer_iid:" + o.getOuterIid());
						logger.info("order.outer_sku_id:" + o.getOuterSkuId());
						logger.info("order.total_fee:" + o.getTotalFee());
						logger.info("order.discount_fee:" + o.getDiscountFee());
						logger.info("order.adjust_fee:" + o.getAdjustFee());
						logger.info("order.payment:" + o.getPayment());
					}
					logger.info("");
				}
			}

			// TradeGetRequest tradeReq = new TradeGetRequest();
			// tradeReq.setFields("");
			// tradeReq.setTid(tid);

		} catch (Exception ex) {
			logger.error(ex.toString());
			this.trades = null;
			return LOGIN;
		}
		return SUCCESS;
	}

	public String input() throws Exception {
		try {
			TradeFullinfoGetRequest tradeReq;
			TradeFullinfoGetResponse tradeResp;
			Trade trade;
			TaobaoClient client = new DefaultTaobaoClient(taobao.getRestUrl(),
					taobao.getAppKey(), taobao.getAppSecret());
			
			String[] tids = this.tids.split(",");
			this.trades = new ArrayList<Trade>();
			this.orders = new ArrayList<Order>();
			
			double totalFee = 0;
			double discountFee = 0;				
			double adjustFee = 0;
			double postFee = 0;
			double payment = 0;
			
			for (String tid : tids) {
				tradeReq = new TradeFullinfoGetRequest();
				tradeReq.setTid(Long.parseLong(tid));
				tradeReq.setFields("tid,"
						+ "created,"
						+ "buyer_nick,"
						+ "total_fee,"
						+ "discount_fee," 
						+ "adjust_fee,"
						+ "post_fee,"
						+ "payment,"
						+ "buyer_message,"
						+ "buyer_memo,"
						+ "seller_memo,"
						+ "receiver_name,"
						+ "receiver_address,"
						+ "receiver_city,"
						+ "receiver_district,"
						+ "receiver_mobile,"
						+ "receiver_phone,"
						+ "receiver_state,"
						+ "receiver_zip,"
						+ "orders");
				
				tradeResp = client.execute(tradeReq, (String)session.get("sess_top_session"));
				trade = tradeResp.getTrade();
				totalFee += Double.parseDouble(trade.getTotalFee());
				discountFee = Double.parseDouble(trade.getDiscountFee());
				adjustFee = Double.parseDouble(trade.getAdjustFee());
				postFee = Double.parseDouble(trade.getPostFee());
				payment = Double.parseDouble(trade.getPayment());
				this.created = trade.getCreated();
				this.buyerNick = trade.getBuyerNick();
				this.buyerMessage = trade.getBuyerMessage();
				this.buyerMemo = trade.getBuyerMemo();
				this.sellerMemo = trade.getSellerMemo();
				this.receiverName = trade.getReceiverName();
				this.receiverAddress = String.format("%s %s %s %s", trade.getReceiverState(), trade.getReceiverCity(), trade.getReceiverDistrict(), trade.getReceiverAddress());
				this.receiverMobile = trade.getReceiverMobile();
				this.receiverPhone = trade.getReceiverPhone();
				this.receiverZip = trade.getReceiverZip();
				
				logger.info("trade.created:" + trade.getCreated());
				logger.info("trade.tid:" + trade.getTid());
				logger.info("trade.buyer_nick:" + trade.getBuyerNick());
				logger.info("trade.total_fee:" + trade.getTotalFee());
				logger.info("trade.discount_fee:" + trade.getDiscountFee());
				logger.info("trade.adjust_fee:" + trade.getAdjustFee());
				logger.info("trade.post_fee:" + trade.getPostFee());
				logger.info("trade.payment:" + trade.getPayment());
				logger.info("trade.buyer_message:" + trade.getBuyerMessage());
				logger.info("trade.buyer_memo:" + trade.getBuyerMemo());
				logger.info("trade.seller_memo:" + trade.getSellerMemo());
				logger.info("trade.receiver_name:" + trade.getReceiverName());
				logger.info("trade.receiver_address:" + trade.getReceiverAddress());
				logger.info("trade.receiver_city:" + trade.getReceiverCity());
				logger.info("trade.receiver_district:" + trade.getReceiverDistrict());
				logger.info("trade.receiver_mobile:" + trade.getReceiverMobile());
				logger.info("trade.receiver_phone:" + trade.getReceiverPhone());
				logger.info("trade.receiver_state:" + trade.getReceiverState());
				logger.info("trade.receiver_zip:" + trade.getReceiverZip());
				
				ArrayList<Order> orders = (ArrayList<Order>) trade.getOrders();
				logger.info("trade.orders.length:" + orders.size());
				for (Order o : orders) {
					boolean found = false;
					for (Order io : this.orders) {
						if (io.getNumIid() == o.getNumIid()) {
							io.setNum(io.getNum() + o.getNum());
							found = true;
							break;
						}
					}
					if (false == found) this.orders.add(o);
					logger.info("order.title:" + o.getTitle());
					logger.info("order.num:" + o.getNum());
					logger.info("order.price:" + o.getPrice());
					logger.info("order.num_iid:" + o.getNumIid());
					logger.info("order.outer_iid:" + o.getOuterIid());
					logger.info("order.outer_sku_id:" + o.getOuterSkuId());
					logger.info("order.total_fee:" + o.getTotalFee());
					logger.info("order.discount_fee:" + o.getDiscountFee());
					logger.info("order.adjust_fee:" + o.getAdjustFee());
					logger.info("order.payment:" + o.getPayment());
				}
				logger.info("");
				this.trades.add(trade);
			}
			this.totalFee = String.valueOf(totalFee);
			this.discountFee = String.valueOf(discountFee);
			this.adjustFee = String.valueOf(adjustFee);
			this.postFee = String.valueOf(postFee);
			this.payment = String.valueOf(payment);
			
			Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			this.deliverDateOption = new ArrayList<String>();
			this.deliverDateOption.add(df.format(new Date(0)));
			for (int i = 0; i < 3; i++) {
				c.setTimeInMillis(this.created.getTime() + 86400000 * i);
				this.deliverDateOption.add(df.format(c.getTime()));
			}
			
		} catch (Exception ex) {
			logger.error(ex.toString());
			this.orders = null;
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String session() throws Exception {
		try {
			
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return LOGIN;
	}
	
	public String session_callback() throws Exception {

		logger.info("top_appkey:" + this.top_appkey);
		logger.info("top_parameters:" + this.top_parameters);
		logger.info("top_session:" + this.top_session);
		logger.info("top_sign:" + this.top_sign);

		this.session.put("sess_top_appkey", this.top_appkey);
		this.session.put("sess_top_parameters", this.top_parameters);
		this.session.put("sess_top_session", this.top_session);
		this.session.put("sess_top_sign", this.top_sign);

		return SUCCESS;
	}
	
	public String trade_import() throws Exception {
		try {
			Sell obj = new Sell();
			obj.setId(0);
			obj.setCustomerIM(this.buyerNick);
			obj.setCustomerIMType(0); // 0 means wangwang
            obj.setCustomerName(this.receiverName);
            obj.setCustomerPhone1(this.receiverMobile);
            obj.setCustomerPhone2(this.receiverPhone);
            obj.setCustomerAddress(this.receiverAddress);
            obj.setCustomerPostCode(this.receiverZip);
			obj.setExpressId(this.expressId);
			obj.setCommentExpress(this.commentExpress);
			obj.setCommentInvoice(this.commentInvoice);
			obj.setFee(Double.parseDouble(this.postFee));
			obj.setFeeReal(Double.parseDouble(this.postFeeReal));
			obj.setSendDate(this.created);

			logger.info("Id:" + obj.getId());
			logger.info("Name:" + obj.getCustomerName());
			logger.info("Phone1:" + obj.getCustomerPhone1());
			logger.info("Phone2:" + obj.getCustomerPhone2());
			logger.info("Address:" + obj.getCustomerAddress());
			logger.info("PostCode:" + obj.getCustomerPostCode());
			logger.info("ExpressId:" + obj.getExpressId());
			logger.info("CommentExpress:" + obj.getCommentExpress());
			logger.info("CommentInvoice:" + obj.getCommentInvoice());
			logger.info("Fee:" + obj.getFee());
			logger.info("FeeReal:" + obj.getFeeReal());
			logger.info("SendDate:" + obj.getSendDate());

			this.sellId = sellService.createSell(obj);
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return SUCCESS;
	}
	
	public String trade_import_item() throws Exception {
		if (sellId == 0) return ERROR;
		try {
			SellItem obj = new SellItem();
			obj.setId(0);
			obj.setSellId(this.sellId);
			obj.setWareId(this.wareId);
			obj.setPrice(this.price);
			obj.setNumber(this.number);
			
			logger.info("Id:" + obj.getId());
			logger.info("SellId:" + obj.getSellId());
			logger.info("WareId:" + obj.getWareId());
			logger.info("Price:" + obj.getPrice());
			logger.info("Number:" + obj.getNumber());
			
			this.id = sellItemService.createSellItem(obj);
			Ware w = wareService.getWareById(wareId);
			w.setLastPrice(price);
			wareService.updateWare(w);
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return SUCCESS;
	}
	
	public String trade_import_finish() throws Exception {
		try {
			TradeMemoUpdateRequest req;
			TradeMemoUpdateResponse response;
			TaobaoClient client = new DefaultTaobaoClient(taobao.getRestUrl(),
					taobao.getAppKey(), taobao.getAppSecret());
			
			String[] tids = this.tids.split(",");
			for (String tid : tids) {
				req = new TradeMemoUpdateRequest();
				req.setTid(Long.parseLong(tid));
				req.setFlag(3L); // Set to green color
				response = client.execute(req , (String)session.get("sess_top_session"));
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return SUCCESS;
	}	

	@Override
	public String execute() throws Exception {
		try {
			printSession();
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return LOGIN;
	}

	private void getUser(TaobaoClient client, String nick) {
		try {
			UserGetRequest userReq = new UserGetRequest();
			userReq.setFields("nick,sex,buyer_credit,seller_credit,created,last_visit,alipay_account,email");
			userReq.setNick(nick);

			UserGetResponse userResp = client.execute(userReq);
			User usr = userResp.getUser();
			logger.info("result:" + userResp.getBody());
			logger.info("nick:" + usr.getNick());
			logger.info("buyer_credit:" + usr.getBuyerCredit());
			logger.info("seller_credit:" + usr.getSellerCredit());
			logger.info("created:" + usr.getCreated());
			logger.info("last_visit:" + usr.getLastVisit());
			logger.info("alipay_account:" + usr.getAlipayAccount());
			logger.info("email:" + usr.getEmail());
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
	}

	private void printSession() {
		try {
			logger.info("sess_top_appkey:" + this.session.get("sess_top_appkey"));
			logger.info("sess_top_parameters:" + this.session.get("sess_top_parameters"));
			logger.info("sess_top_session:" + this.session.get("sess_top_session"));
			logger.info("sess_top_sign:" + this.session.get("sess_top_sign"));
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
	}

	public static void main(String[] args) throws Exception {
		TradeImportAction action = new TradeImportAction();
		action.list();
	}

	public void setTaobao(TaobaoBean taobao) {
		this.taobao = taobao;
	}

	public TaobaoBean getTaobao() {
		return taobao;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public Map getSession() {
		return session;
	}

	public ArrayList<Trade> getTrades() {
		return trades;
	}

	public void setTop_appkey(String top_appkey) {
		this.top_appkey = top_appkey;
	}

	public void setTop_parameters(String top_parameters) {
		this.top_parameters = top_parameters;
	}

	public void setTop_session(String top_session) {
		this.top_session = top_session;
	}

	public void setTop_sign(String top_sign) {
		this.top_sign = top_sign;
	}

	public void setTids(String tids) {
		this.tids = tids;
	}

	public String getTop_appkey() {
		return top_appkey;
	}

	public String getTop_parameters() {
		return top_parameters;
	}

	public String getTop_session() {
		return top_session;
	}

	public String getTop_sign() {
		return top_sign;
	}

	public String getCreated() {
		return new SimpleDateFormat("yyyy-MM-dd").format(this.created);
	}

	public void setCreated(String created) {
		try {
			this.created = new SimpleDateFormat("yyyy-MM-dd").parse(created);
		} catch(Exception ex) {
			this.created = new Date();
		}
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public String getPayment() {
		return payment;
	}

	public String getPostFee() {
		return postFee;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public String getDiscountFee() {
		return discountFee;
	}

	public String getAdjustFee() {
		return adjustFee;
	}

	public String getBuyerMessage() {
		return buyerMessage;
	}

	public String getBuyerMemo() {
		return buyerMemo;
	}

	public String getSellerMemo() {
		return sellerMemo;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}	
		
	public String getReceiverPhone() {
		return receiverPhone;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public String getReceiverZip() {
		return receiverZip;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setExpressService(ExpressService expressService) {
		this.expressService = expressService;
	}

	public void setSellService(SellService sellService) {
		this.sellService = sellService;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public void setSellItemService(SellItemService sellItemService) {
		this.sellItemService = sellItemService;
	}

	public void setWareService(WareService wareService) {
		this.wareService = wareService;
	}

	public Map<Integer, String> getExpressOption() {
		return expressService.getExpressSel();
	}

	public List<String> getDeliverDateOption() {
		return deliverDateOption;
	}

	public void setExpressId(int expressId) {
		this.expressId = expressId;
	}

	public void setPostFeeReal(String postFeeReal) {
		this.postFeeReal = postFeeReal;
	}

	public void setCommentExpress(String commentExpress) {
		this.commentExpress = commentExpress;
	}

	public void setCommentInvoice(String commentInvoice) {
		this.commentInvoice = commentInvoice;
	}

	public void setPostFee(String postFee) {
		this.postFee = postFee;
	}

	public int getSellId() {
		return sellId;
	}

	public void setWareId(int wareId) {
		this.wareId = wareId;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getPostFeeReal() {
		return postFeeReal;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public int getExpressId() {
		return expressId;
	}

	public String getCommentExpress() {
		return commentExpress;
	}

	public String getCommentInvoice() {
		return commentInvoice;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}

}
