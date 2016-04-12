package erp.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Sell;
import erp.model.SellItem;
import erp.service.ExpressService;
import erp.service.SellItemService;
import erp.service.SellService;
import erp.service.UtilService;
import erp.service.WareService;
import erp.util.Pager;

public class SellAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(SellAction.class);

	private SellService sellService = null;
	private SellItemService sellItemService = null;
	private WareService wareService = null;
	private ExpressService expressService = null;

	private int id;
	private String customerName;
	private String customerAddress;
	private String customerPhone1;
	private String customerPhone2;
	private String customerPostCode;
	private String customerIM;
	private int customerIMType;
	private String customerIMComment;
	private String fee;
	private String feeReal;
	private String discount;
	private int expressId;
	private String commentExpress;
	private String commentInvoice;
	private String sender;
	private String date;
	private int status = 0;
	private int s = 0;

	private String keyword;

	private List<String> dateSel;
	private Map<Integer, String> expressSel;
	private Map<Integer, String> imTypeSel;

	private List<Sell> sellList;
	private List<SellItem> sellItemList;
	private Pager pager;

	public String list() throws Exception {
		pager.setAction("sell_list.action?status=" + String.valueOf(status));
		pager.setTotalItems(sellService.getCount(status));
		pager.generatePageData();
		sellList = sellService.list(pager.getOffsetItems(), pager.getPerPage(),
				status);

		logger.debug(pager.getCurrentPage() + "/" + pager.getTotalPage());
		return SUCCESS;
	}

	public String get() throws Exception {
		expressSel = expressService.getExpressSel();
		date = genDate();
		dateSel = genDateSel();
		try {
			DecimalFormat f = new DecimalFormat("###0.00");
			Sell s = sellService.getSellById(id);
			if (s != null) {
				customerName = s.getCustomerName();
				customerAddress = s.getCustomerAddress();
				customerPhone1 = s.getCustomerPhone1();
				customerPhone2 = s.getCustomerPhone2();
				customerPostCode = s.getCustomerPostCode();
				customerIM = s.getCustomerIM();
				customerIMType = s.getCustomerIMType();
				customerIMComment = s.getCustomerIMComment();
				fee = f.format(s.getFee());
				feeReal = f.format(s.getFeeReal());
				discount = f.format(s.getDiscount());
				expressId = s.getExpressId();
				commentExpress = s.getCommentExpress();
				commentInvoice = s.getCommentInvoice();
				sender = s.getSender();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				date = df.format(s.getSendDate());

				sellItemList = sellItemService.listBySell(s);
				for (SellItem item : sellItemList) {
					item.setWare(wareService.getWareById(item.getWareId()));
				}
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	public String delete() throws Exception {
		try {
			Sell s = sellService.getSellById(id);
			sellItemList = sellItemService.listBySell(s);
			for (SellItem item : sellItemList) {
				sellItemService.deleteSellItem(item.getId());
			}
			sellService.deleteSell(s);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	public String search() throws Exception {
		if ((keyword != null) && keyword.trim().isEmpty() == false) {
			logger.debug("findByKeyword:" + keyword);
			sellList = sellService.findByKeyword(keyword.trim());
		}
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		try {
			Sell obj = (id > 0) ? sellService.getSellById(id) : new Sell();
			obj.setCustomerName(customerName.trim());
			obj.setCustomerAddress(customerAddress.trim());
			obj.setCustomerPhone1(customerPhone1.trim());
			obj.setCustomerPhone2(customerPhone2.trim());
			obj.setCustomerPostCode(customerPostCode.trim());
			obj.setCustomerIM(customerIM.trim());
			obj.setCustomerIMType(customerIMType);
			obj.setCustomerIMComment(customerIMComment.trim());
			obj.setExpressId(expressId);
			obj.setCommentExpress(commentExpress.trim());
			obj.setCommentInvoice(commentInvoice.trim());
			obj.setSender(sender.trim());
			if (fee.isEmpty() == false) {
				obj.setFee(Double.parseDouble(fee));
			}
			if (feeReal.isEmpty() == false) {
				obj.setFeeReal(Double.parseDouble(feeReal));
			}
			if (discount.isEmpty() == false) {
				obj.setDiscount(Double.parseDouble(discount));
			}
			if (date != null) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				obj.setSendDate(df.parse(date));
			}

			if (id > 0) {
				sellService.updateSell(obj);
			} else {
				id = sellService.createSell(obj);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	public String updateStatus() throws Exception {
		try {
			if (id > 0) {
				Sell obj = sellService.getSellById(id);
				obj.setStatus(s);
				sellService.updateSell(obj);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return status == 0 ? SUCCESS : INPUT;
	}

	private String genDate() throws Exception {
		String sendDate = "";
		try {
			Date d = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(d);

			if (c.get(Calendar.HOUR_OF_DAY) > 18) {
				c.setTimeInMillis(d.getTime() + 86400000);
				d = c.getTime();
			}
			sendDate = df.format(d);
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return sendDate;
	}

	private List<String> genDateSel() {
		List<String> dateSel = new ArrayList<String>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			
			Date d0 = new Date(0);
			dateSel.add(df.format(d0));
			
			Date d1 = new Date();
			c.setTimeInMillis(d1.getTime() + 86400000);
			Date d2 = c.getTime();
			c.setTimeInMillis(d2.getTime() + 86400000);
			Date d3 = c.getTime();

			dateSel.add(df.format(d1));
			dateSel.add(df.format(d2));
			dateSel.add(df.format(d3));

		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return dateSel;
	}

	public SellService getSellService() {
		return sellService;
	}

	public void setSellService(SellService sellService) {
		this.sellService = sellService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone1() {
		return customerPhone1;
	}

	public void setCustomerPhone1(String customerPhone1) {
		this.customerPhone1 = customerPhone1;
	}

	public String getCustomerPhone2() {
		return customerPhone2;
	}

	public void setCustomerPhone2(String customerPhone2) {
		this.customerPhone2 = customerPhone2;
	}

	public String getCustomerPostCode() {
		return customerPostCode;
	}

	public void setCustomerPostCode(String customerPostCode) {
		this.customerPostCode = customerPostCode;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getFeeReal() {
		return feeReal;
	}

	public void setFeeReal(String feeReal) {
		this.feeReal = feeReal;
	}

	public int getExpressId() {
		return expressId;
	}

	public void setExpressId(int expressId) {
		this.expressId = expressId;
	}

	public Map<Integer, String> getExpressSel() {
		return expressSel;
	}

	public List<Sell> getSellList() {
		return sellList;
	}

	public void setSellList(List<Sell> sellList) {
		this.sellList = sellList;
	}

	public WareService getWareService() {
		return wareService;
	}

	public void setWareService(WareService wareService) {
		this.wareService = wareService;
	}

	public SellItemService getSellItemService() {
		return sellItemService;
	}

	public String getCommentExpress() {
		return commentExpress;
	}

	public void setCommentExpress(String commentExpress) {
		this.commentExpress = commentExpress;
	}

	public String getCommentInvoice() {
		return commentInvoice;
	}

	public void setCommentInvoice(String commentInvoice) {
		this.commentInvoice = commentInvoice;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setSellItemService(SellItemService sellItemService) {
		this.sellItemService = sellItemService;
	}

	public List<SellItem> getSellItemList() {
		return sellItemList;
	}

	public void setSellItemList(List<SellItem> sellItemList) {
		this.sellItemList = sellItemList;
	}

	public ExpressService getExpressService() {
		return expressService;
	}

	public void setExpressService(ExpressService expressService) {
		this.expressService = expressService;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public int getCurrentPage() {
		return pager.getCurrentPage();
	}

	public void setCurrentPage(int page) {
		pager.setCurrentPage(page);
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public List<String> getDateSel() {
		return dateSel;
	}

	public String getCustomerIM() {
		return customerIM;
	}

	public void setCustomerIM(String customerIM) {
		this.customerIM = customerIM;
	}

	public int getCustomerIMType() {
		return customerIMType;
	}

	public void setCustomerIMType(int customerIMType) {
		this.customerIMType = customerIMType;
	}

	public String getCustomerIMComment() {
		return customerIMComment;
	}

	public void setCustomerIMComment(String customerIMComment) {
		this.customerIMComment = customerIMComment;
	}

	public Map<Integer, String> getImTypeSel() {
		return imTypeSel;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public void setUtilService(UtilService utilService) {
		this.imTypeSel = utilService.getIMTypeSel();
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

}
