package erp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import erp.dao.VendorDao;
import erp.model.Vendor;

public class VendorService {

	private VendorDao vendorDao;

	public Map<Integer, String> getVendorMap() {
		Map<Integer, String> mapEx = new HashMap<Integer, String>(16);
		List<Vendor> lst = vendorDao.list();
		for (Vendor obj : lst) {
			mapEx.put(obj.getId(), obj.getTitle());
		}
		mapEx.put(-1, "未设置");
		return mapEx;
	}

	public Vendor getVendorById(int expressId) {
		return vendorDao.read(expressId);
	}

	public int createVendor(Vendor obj) {
		return vendorDao.create(obj);
	}

	public boolean deleteVendor(Vendor obj) {
		boolean ret = false;
		if (null != obj) {
			vendorDao.delete(obj);
			ret = true;
		}
		return ret;
	}

	public boolean deleteVendorById(int id) {
		boolean ret = false;
		Vendor obj = vendorDao.read(id);
		if (null != obj) {
			vendorDao.delete(obj);
			ret = true;
		}
		return ret;
	}

	public boolean updateVendor(Vendor obj) {
		vendorDao.update(obj);
		return true;
	}

	public int getCount() {
		return vendorDao.count();
	}

	public List<Vendor> list() {
		return vendorDao.list();
	}

	public List<Map> listHistoryOffer(int id) {
		return vendorDao.listHistoryOffer(id);
	}

	public VendorDao getVendorDao() {
		return vendorDao;
	}

	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}
}
