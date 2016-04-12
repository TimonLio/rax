package erp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import erp.dao.WareCategoryDao;
import erp.dao.WareDao;
import erp.model.Ware;
import erp.model.WareCategory;

public class WareCategoryService {

	private WareCategoryDao wareCategoryDao;
	private WareDao wareDao;

	public WareCategoryService() {
	}

	public WareCategory getWareCategoryById(int id) {
		return wareCategoryDao.read(id);
	}

	public int createWareCagegory(WareCategory obj) {
		return wareCategoryDao.create(obj);
	}

	public boolean deleteWareCategoryById(int id) {
		WareCategory obj = wareCategoryDao.read(id);
		return deleteWareCategory(obj);
	}

	public boolean deleteWareCategory(WareCategory obj) {
		boolean ret = false;
		if (null != obj) {
			List<Ware> wareList = wareDao.listByCategoryId(obj.getId(), -1);
			for (Ware w : wareList) {
				w.setCategoryId(0);
				wareDao.update(w);
			}
			wareCategoryDao.delete(obj);
			ret = true;
		}
		return ret;
	}

	public boolean updateWareCategory(WareCategory obj) {
		wareCategoryDao.update(obj);
		return true;
	}

	public int getCount() {
		return wareCategoryDao.count();
	}

	public List<WareCategory> list() {
		return wareCategoryDao.list();
	}

	public Map<Integer, String> getMap() {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		List<WareCategory> list = wareCategoryDao.list();
		for (WareCategory category : list) {
			map.put(category.getId(), category.getName());
		}
		return map;
	}

	public void setWareCategoryDao(WareCategoryDao wareCategoryDao) {
		this.wareCategoryDao = wareCategoryDao;
	}

	public void setWareDao(WareDao wareDao) {
		this.wareDao = wareDao;
	}

}
