package erp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import erp.dao.ExpressDao;
import erp.model.Express;

public class ExpressService {

	private ExpressDao expressDao;

	public Map<Integer, String> getExpressSel() {
		Map<Integer, String> mapEx = new HashMap<Integer, String>(16);
		List<Express> lstEx = expressDao.list();
		for (Express ex : lstEx) {
			mapEx.put(ex.getId(), ex.getExName());
		}
		return mapEx;
	}

	public Express getExpressById(int expressId) {
		return expressDao.read(expressId);
	}

	public int createExpress(Express obj) {
		return expressDao.create(obj);
	}

	public boolean deleteExpress(Express obj) {
		boolean ret = false;
		if (null != obj) {
			expressDao.delete(obj);
			ret = true;
		}
		return ret;
	}

	public boolean deleteExpressById(int id) {
		boolean ret = false;
		Express obj = expressDao.read(id);
		if (null != obj) {
			expressDao.delete(obj);
			ret = true;
		}
		return ret;
	}

	public boolean updateExpress(Express obj) {
		expressDao.update(obj);
		return true;
	}

	public int getCount() {
		return expressDao.count();
	}

	public List<Express> list() {
		return expressDao.list();
	}

	public ExpressDao getExpressDao() {
		return expressDao;
	}

	public void setExpressDao(ExpressDao expressDao) {
		this.expressDao = expressDao;
	}
}
