package erp.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.OrderItem;
import erp.model.StatWare;
import erp.model.Vendor;
import erp.model.Ware;
import erp.model.WareCategory;
import erp.service.KeywordService;
import erp.service.OrderItemService;
import erp.service.OrderService;
import erp.service.VendorService;
import erp.service.WareCategoryService;
import erp.service.WareService;
import erp.util.Pager;

public class WareAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(WareAction.class);

	private WareService wareService = null;
	private WareCategoryService wareCategoryService = null;
	private KeywordService keywordService = null;
	private VendorService vendorService = null;
	private OrderService orderService = null;
	private OrderItemService orderItemService = null;

	private int id;
	private int categoryId = 0;
	private Ware ware;
	private int status = 0;
	private String tokenize;
	private String keyword;

	private List<Ware> wareList;
	private Map<Integer, String> categoryMap;
	private List<WareCategory> categoryList;
	private List<Map> historyList;
	private List<OrderItem> orderItemList;
	private List<StatWare> statList;

	private Pager pager;
	private String fileName = "";

	public String listByCategory() throws Exception {
		categoryList = wareCategoryService.list();
		wareList = wareService.listByCategoryId(categoryId, status);
		statList = new ArrayList<StatWare>();
		for (Ware w : wareList) {
			statList.add(wareService.getStatById(w.getId()));
		}
		return SUCCESS;
	}

	public String listHided() throws Exception {
		int num = wareService.getCount(status);
		wareList = wareService.list(status, 0, num);
		return SUCCESS;
	}

	public String get() throws Exception {
		try {
			ware = wareService.getWareById(id);
			categoryMap = wareCategoryService.getMap();
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	public String delete() throws Exception {
		try {
			// Just hide it, sell history was using them
			Ware obj = wareService.getWareById(id);
			obj.setStatus(1);
			wareService.updateWare(obj);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	public String updateAlarm() throws Exception {
		try {
			Ware obj = wareService.getWareById(id);
			if (obj != null) {
				obj.setNumberAlarmEnable(0);
				wareService.updateWare(obj);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		try {
			Ware obj = (id > 0) ? wareService.getWareById(id) : new Ware();
			obj.setCategoryId(categoryId);
			obj.setName(ware.getName().trim());
			obj.setBarcode(ware.getBarcode().trim());
			obj.setCost(ware.getCost());
			obj.setNumber(ware.getNumber());
			obj.setLastPrice(ware.getLastPrice());
			obj.setNumberAlarm(ware.getNumberAlarm());
			obj.setNumberAlarmEnable(ware.getNumberAlarmEnable());
			obj.setId(id);
			// Do not set obj.Status

			if (id > 0)
				wareService.updateWare(obj);
			else
				id = wareService.createWare(obj);

			if (tokenize != null && tokenize.isEmpty() == false) {
				keywordService.saveTokens(tokenize);
				wareService.updateFullTextIndex(obj, tokenize);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return (status > 0) ? "success_hide" : SUCCESS;
	}

	public String keywordSearch() throws Exception {
		if ((keyword != null) && keyword.isEmpty() == false) {
			logger.debug("findByKeywords:" + keyword);
			wareList = wareService.findByKeywords(keyword);
		}
		return SUCCESS;
	}

	public String fullTextSearch() throws Exception {
		// AJAX
		logger.debug("fullTextSearch:" + keyword);
		List<String> tokenList = keywordService.parseToken(keyword);
		StringBuffer tokenBuf = new StringBuffer();
		boolean skipFirstToken = true;
		for (String token : tokenList) {
			if (skipFirstToken == false) {
				tokenBuf.append("|");
			}
			tokenBuf.append(token);
			skipFirstToken = false;
		}
		wareList = wareService.fullTextSearch(tokenBuf.toString().trim(), 0);
		return SUCCESS;
	}

	public String listHistoryOrder() throws Exception {
		pager.setAction("ware_list_history_order.action?id="
				+ String.valueOf(id) + "&status=" + String.valueOf(status)
				+ "&categoryId=" + String.valueOf(categoryId));
		pager.setTotalItems(orderItemService.getCountByWareId(id));
		pager.generatePageData();
		orderItemList = orderItemService.listByWareId(id, pager
				.getOffsetItems(), pager.getPerPage());
		for (OrderItem item : orderItemList) {
			item.setWare(wareService.getWareById(item.getWareId()));
			item.setOrder(orderService.getOrderById(item.getOrderId()));
		}
		return SUCCESS;
	}

	public String listHistoryPrice() throws Exception {
		historyList = wareService.listHistoryPrice(id);
		HashMap map = new HashMap();
		Vendor vendor = null;
		for (Iterator it = historyList.iterator(); it.hasNext();) {
			map = (HashMap) it.next();
			vendor = vendorService
					.getVendorById((Integer) map.get("vendor_id"));
			map.put("vendor", vendor);
		}
		return SUCCESS;
	}

	public String export() throws Exception {
		WareCategory category = wareCategoryService
				.getWareCategoryById(categoryId);
		if (category != null) {
			fileName = new SimpleDateFormat("yyyyMMdd").format(new Date())
					+ "_" + category.getName();
		}
		return SUCCESS;
	}

	public InputStream getInputStream() throws Exception {
		StringBuffer buf = new StringBuffer();
		DecimalFormat df = new DecimalFormat("####.00");
		try {
			buf.append("宝贝名称\t库存数量\t成本价\t零售价\t\n");
			wareList = wareService.listByCategoryId(categoryId, status);
			for (Ware ware : wareList) {
				buf.append(ware.getName() + "\t");
				buf.append(String.valueOf(ware.getNumber()) + "\t");
				buf.append(df.format(ware.getCost()) + "\t");
				buf.append(df.format(ware.getLastPrice()) + "\t");
				buf.append("\n");
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return new ByteArrayInputStream(buf.toString().getBytes());
	}

	public String getFileName() {
		String ret = fileName;
		try {
			ret = new String(fileName.getBytes(), "ISO8859-1");
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return ret;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setWareService(WareService service) {
		wareService = service;
	}

	public void setWareCategoryService(WareCategoryService wareCategoryService) {
		this.wareCategoryService = wareCategoryService;
	}

	public List<Ware> getWareList() {
		return wareList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setWareList(List<Ware> wareList) {
		this.wareList = wareList;
	}

	public Map<Integer, String> getCategoryMap() {
		return categoryMap;
	}

	public List<WareCategory> getCategoryList() {
		return categoryList;
	}

	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}

	public String getTokenize() {
		return tokenize;
	}

	public void setTokenize(String tokenize) {
		this.tokenize = tokenize;
	}

	public void setWare(Ware ware) {
		this.ware = ware;
	}

	public Ware getWare() {
		return ware;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Map> getHistoryList() {
		return historyList;
	}

	public void setVendorService(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	public void setOrderItemService(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Map<Integer, String> getVendorMap() {
		return vendorService.getVendorMap();
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

	public List<StatWare> getStatList() {
		return statList;
	}
	
	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
		WareAction action = new WareAction();
		action.categoryId = 12;
		action.listByCategory();
	}
}
