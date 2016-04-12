package erp.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.SellItem;
import erp.model.Ware;
import erp.service.SellItemService;
import erp.service.SellService;
import erp.service.WareService;

public class SellItemAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(SellItemAction.class);

	private WareService wareService = null;
	private SellService sellService = null;
	private SellItemService sellItemService = null;

	private int id;
	private int sellId;
	private int wareId;
	private double price;
	private int number;
	private Ware ware;

	private List<SellItem> sellItemList;

	public String get() throws Exception {
		try {
			SellItem item = sellItemService.getSellItemById(id);
			if (item != null) {
				sellId = item.getSellId();
				wareId = item.getWareId();
				price = item.getPrice();
				number = item.getNumber();
				ware = wareService.getWareById(wareId);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		sellItemList = sellItemService.listBySellId(sellId);
		for (SellItem item : sellItemList) {
			item.setWare(wareService.getWareById(item.getWareId()));
		}
		return SUCCESS;
	}

	public String delete() throws Exception {
		try {
			sellItemService.deleteSellItem(id);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		// AJAX 方法
		if (sellId == 0) return ERROR;
		try {
			SellItem obj = new SellItem();
			obj.setId(id);
			obj.setSellId(sellId);
			obj.setWareId(wareId);
			obj.setPrice(price);
			obj.setNumber(number);
			if (id > 0) {
				sellItemService.updateSellItem(obj);
			} else {
				id = sellItemService.createSellItem(obj);
			}
			Ware w = wareService.getWareById(wareId);
			w.setLastPrice(price);
			wareService.updateWare(w);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SellItemService getSellItemService() {
		return sellItemService;
	}

	public void setSellItemService(SellItemService sellItemService) {
		this.sellItemService = sellItemService;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public int getWareId() {
		return wareId;
	}

	public void setWareId(int wareId) {
		this.wareId = wareId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public SellService getSellService() {
		return sellService;
	}

	public void setSellService(SellService sellService) {
		this.sellService = sellService;
	}

	public List<SellItem> getSellItemList() {
		return sellItemList;
	}

	public void setSellItemList(List<SellItem> sellItemList) {
		this.sellItemList = sellItemList;
	}

	public WareService getWareService() {
		return wareService;
	}

	public void setWareService(WareService wareService) {
		this.wareService = wareService;
	}

	public Ware getWare() {
		return ware;
	}

	public void setWare(Ware ware) {
		this.ware = ware;
	}

}
