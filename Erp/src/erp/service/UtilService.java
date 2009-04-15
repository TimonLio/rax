package erp.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import erp.dao.UtilDao;

public class UtilService {

	private final static Logger logger = Logger.getLogger(UtilService.class);

	private UtilDao utilDao;
	private Map<Integer, String> IMTypeSel;

	public UtilService() {
		IMTypeSel = new HashMap<Integer, String>(8);
		IMTypeSel.put(0, "旺旺");
		IMTypeSel.put(1, "QQ");
		IMTypeSel.put(2, "易趣通");
		IMTypeSel.put(3, "MSN");
		IMTypeSel.put(-1, "其他");
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
