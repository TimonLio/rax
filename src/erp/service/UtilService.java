package erp.service;

import java.util.HashMap;
import java.util.Map;

import erp.dao.UtilDao;
import erp.model.Util;

public class UtilService {

	//private final static Logger logger = Logger.getLogger(UtilService.class);

	private UtilDao utilDao;
	private Map<Integer, String> IMTypeSel;

	public UtilService() {
		IMTypeSel = new HashMap<Integer, String>(8);
		IMTypeSel.put(0, "旺旺");
		IMTypeSel.put(1, "QQ");
		IMTypeSel.put(2, "易趣通");
		IMTypeSel.put(3, "MSN");
		IMTypeSel.put(4, "百度Hi");
		IMTypeSel.put(-1, "其他");
	}

	public String getSenderName() {
		Util u = utilDao.read("sender_name");
		String ret = (null == u) ? "" : u.getValue();
		return ret;
	}
	
	public String getSenderAddress() {
		Util u = utilDao.read("sender_address");
		return (null == u) ? "" : u.getValue();
	}
	
	public String getSenderPhone() {
		Util u = utilDao.read("sender_phone");
		return (null == u) ? "" : u.getValue();
	}
	
	public String getSenderPostCode() {
		Util u = utilDao.read("sender_postcode");
		return (null == u) ? "" : u.getValue();
	}
	
	public UtilDao getUtilDao() {
		return utilDao;
	}

	public void setUtilDao(UtilDao utilDao) {
		this.utilDao = utilDao;
	}

	public Map<Integer, String> getIMTypeSel() {
		return IMTypeSel;
	}

}
