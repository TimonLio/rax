package erp.service;

import java.util.List;

import erp.dao.SellItemDao;
import erp.dao.WareDao;
import erp.model.Sell;
import erp.model.SellItem;
import erp.model.Ware;

public class SellItemService {

	private SellItemDao sellItemDao;
	private WareDao wareDao;

	public SellItemService() {
	}

	public SellItem getSellItemById(int id) {
		return sellItemDao.read(id);
	}

	public int createSellItem(SellItem obj) {
		Ware ware = wareDao.read(obj.getWareId());
		ware.setNumber(ware.getNumber() - obj.getNumber());
		wareDao.update(ware);
		return sellItemDao.create(obj);
	}

	public boolean deleteSellItem(int id) {
		boolean ret = false;
		SellItem obj = sellItemDao.read(id);
		if (null != obj) {
			Ware ware = wareDao.read(obj.getWareId());
			ware.setNumber(ware.getNumber() + obj.getNumber());
			wareDao.update(ware);
			sellItemDao.delete(obj);
			ret = true;
		}
		return ret;
	}

	public boolean updateSellItem(SellItem obj) {
		SellItem item = sellItemDao.read(obj.getId());
		Ware ware = wareDao.read(obj.getWareId());
		ware.setNumber(ware.getNumber() + item.getNumber() - obj.getNumber());
		wareDao.update(ware);
		sellItemDao.update(obj);
		return true;
	}
	
	public int countBySell(Sell s) {
		return sellItemDao.countBySellId(s.getId());
	}
	
	public int countBySellId(int sellId) {
		return sellItemDao.countBySellId(sellId);
	}

	public List<SellItem> listBySell(Sell s) {
		return sellItemDao.listBySellId(s.getId());
	}

	public List<SellItem> listBySellId(int sellId) {
		return sellItemDao.listBySellId(sellId);
	}
	
	public void setSellItemDao(SellItemDao dao) {
		sellItemDao = dao;
	}

	public void setWareDao(WareDao wareDao) {
		this.wareDao = wareDao;
	}

}
