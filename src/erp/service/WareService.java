package erp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import erp.dao.WareDao;
import erp.model.StatWare;
import erp.model.Ware;
import erp.model.WareCategory;

public class WareService {

	private final static Logger logger = Logger.getLogger(WareService.class);

	private WareDao wareDao;

	public WareService() {
	}

	public Ware getWareById(int id) {
		return wareDao.read(id);
	}

	public int createWare(Ware obj) {
		return wareDao.create(obj);
	}

	public boolean deleteWare(int id) {
		boolean ret = false;
		Ware obj = wareDao.read(id);
		if (null != obj) {
			wareDao.delete(obj);
			ret = true;
		}
		return ret;
	}

	public boolean updateWare(Ware obj) {
		wareDao.update(obj);
		return true;
	}

	public void check(Ware obj, boolean flag, int chkNumber, double chkCost,
			double fee) {
		int oldNumber;
		int newNumber;
		double newCost;
		logger.debug(obj.getName() + " flag:" + String.valueOf(flag)
				+ " chkNumber:" + String.valueOf(chkNumber) + " chkCost:"
				+ String.valueOf(chkCost) + " fee:" + String.valueOf(fee));
		if (flag) {
			oldNumber = (obj.getNumber() < 0) ? 0 : obj.getNumber();
			newNumber = obj.getNumber() + chkNumber;
			newCost = ((oldNumber + chkNumber) > 0) ? (obj.getCost()
					* oldNumber + (chkCost + fee) * chkNumber)
					/ (oldNumber + chkNumber) : 0;
		} else {
			oldNumber = obj.getNumber();
			newNumber = oldNumber - chkNumber;
			newCost = (newNumber > 0) ? (obj.getCost() * oldNumber - (chkCost + fee)
					* chkNumber)
					/ newNumber
					: obj.getCost();
		}
		logger.debug("oldNumber:" + String.valueOf(oldNumber) + " newNumber:"
				+ String.valueOf(newNumber) + " newCost:"
				+ String.valueOf(newCost));
		obj.setCost(newCost);
		obj.setNumber(newNumber);
		obj.setNumberAlarmEnable(newNumber != 0 ? 1 : 0);
		wareDao.update(obj);
	}

	public boolean updateFullTextIndex(Ware obj, String tokens) {
		return wareDao.updateFullTextIndex(obj.getId(), tokens) > 0;
	}

	public int getCount(int status) {
		return wareDao.count(status);
	}

	public List<Ware> list(int status, int index, int num) {
		return wareDao.list(status, index, num);
	}

	public List<Ware> listByCategoryId(int categoryId, int status) {
		return wareDao.listByCategoryId(categoryId, status);
	}

	public List<Ware> listByCategory(WareCategory category, int status) {
		return wareDao.listByCategoryId(category.getId(), status);
	}

	public List<Ware> listLowNumber(int status) {
		return wareDao.listLowNumber(status);
	}

	public List<Map> listHistoryPrice(int id) {
		return wareDao.listHistoryPrice(id);
	}

	public List<Ware> findByBarcode(String barcode) {
		return wareDao.findByBarcode(barcode);
	}
	
	public StatWare getStatById(int id) {
		return wareDao.getStatById(id);
	}

	public List<Ware> findByKeywords(String keywords) {
		List<String> keywordList = new ArrayList<String>();
		String[] list = keywords.split(" ");
		for (int i = 0; i < list.length; i++) {
			keywordList.add(list[i].toLowerCase());
		}
		return wareDao.findByKeywords(keywordList);
	}

	public List<Ware> fullTextSearch(String content, int status) {
		logger.debug("fullTextService:" + content + " status:" + status);
		return wareDao.fullTextSearch(content, status);
	}

	public void setWareDao(WareDao dao) {
		wareDao = dao;
	}

}
