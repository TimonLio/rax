package erp.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.WareDao;
import erp.model.OrderItem;
import erp.model.StatWare;
import erp.model.Ware;

public class WareDaoImpl extends SqlMapClientDaoSupport implements WareDao {

	@Override
	public int create(Ware newInstance) throws DataAccessException {
		newInstance.setId((Integer) getSqlMapClientTemplate().insert(
				"Ware.create", newInstance));
		return newInstance.getId();
	}

	@Override
	public List<Ware> findByBarcode(String barcode) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("Ware.findByBarcode",
				barcode);
	}

	@Override
	public List<Ware> findByKeywords(List<String> keywordList)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("Ware.findByKeywords",
				keywordList);
	}

	@Override
	public List<Ware> fullTextSearch(String content, int status)
			throws DataAccessException {
		Map param = new HashMap();
		param.put("content", content);
		param.put("status", status);
		return getSqlMapClientTemplate().queryForList("Ware.fullTextSearch",
				param);
	}

	@Override
	public int updateFullTextIndex(int id, String tokens)
			throws DataAccessException {
		Map param = new HashMap();
		param.put("id", id);
		param.put("tokenize", tokens);
		return getSqlMapClientTemplate().update("Ware.updateFullTextIndex",
				param);
	}

	@Override
	public Ware read(int id) throws DataAccessException {
		return (Ware) getSqlMapClientTemplate().queryForObject("Ware.read", id);
	}

	@Override
	public int update(Ware transientObject) throws DataAccessException {
		return getSqlMapClientTemplate().update("Ware.update", transientObject);
	}

	@Override
	public int delete(Ware persistentObject) throws DataAccessException {
		return getSqlMapClientTemplate().delete("Ware.delete",
				persistentObject.getId());
	}

	@Override
	public int count(int status) throws DataAccessException {
		Map param = new HashMap();
		param.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("Ware.count",
				param);
	}

	@Override
	public List<Ware> list(int status, int index, int num)
			throws DataAccessException {
		Map param = new HashMap();
		param.put("index", index);
		param.put("number", num);
		param.put("status", status);
		return getSqlMapClientTemplate().queryForList("Ware.list", param);
	}

	@Override
	public List<Ware> listByCategoryId(int id, int status)
			throws DataAccessException {
		Map param = new HashMap();
		param.put("id", id);
		param.put("status", status);
		return getSqlMapClientTemplate().queryForList("Ware.listByCategoryId",
				param);
	}

	@Override
	public List<Ware> listLowNumber(int status) throws DataAccessException {
		Map param = new HashMap();
		param.put("status", status);
		return getSqlMapClientTemplate().queryForList("Ware.listLowNumber",
				param);
	}

	@Override
	public List<Map> listHistoryPrice(int id) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("Ware.listHistoryPrice",
				id);
	}

	@Override
	public StatWare getStatById(int id) throws DataAccessException {
		StatWare ret = new StatWare();
		StatWare sellStat = (StatWare)getSqlMapClientTemplate().queryForObject(
				"Ware.getLastSellDate", id);
		StatWare buyStat = (StatWare)getSqlMapClientTemplate().queryForObject(
				"Ware.getLastBuyDate", id);
		if (sellStat != null) ret.setLastSellDate(sellStat.getLastSellDate());
		if (buyStat != null) ret.setLastBuyDate(buyStat.getLastBuyDate());
		ret.setId(id);
		return ret;
	}
}
