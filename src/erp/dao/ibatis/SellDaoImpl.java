package erp.dao.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.SellDao;
import erp.model.Sell;

public class SellDaoImpl extends SqlMapClientDaoSupport implements SellDao {

	@Override
	public int create(Sell newInstance) throws DataAccessException {
		newInstance.setId((Integer) getSqlMapClientTemplate().insert(
				"Sell.create", newInstance));
		return newInstance.getId();
	}

	@Override
	public Sell read(int id) throws DataAccessException {
		return (Sell) getSqlMapClientTemplate().queryForObject("Sell.read", id);
	}

	@Override
	public int update(Sell transientObject) throws DataAccessException {
		return getSqlMapClientTemplate().update("Sell.update", transientObject);
	}

	@Override
	public int delete(Sell persistentObject) throws DataAccessException {
		return getSqlMapClientTemplate().delete("Sell.delete",
				persistentObject.getId());
	}

	@Override
	public int count(int status) throws DataAccessException {
		Map param = new HashMap();
		param.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("Sell.count",
				param);
	}

	@Override
	public List<Sell> list(int index, int num, int status)
			throws DataAccessException {
		Map param = new HashMap();
		param.put("index", index);
		param.put("number", num);
		if (status >= 0) {
			param.put("status", status);
		}
		return getSqlMapClientTemplate().queryForList("Sell.list", param);
	}

	@Override
	public List<Sell> listByExpress(int expressId, Date from, Date to) throws DataAccessException
	{
        Map param = new HashMap();
        param.put("expressId", expressId);
        param.put("from", from);
        param.put("to", to);
        return getSqlMapClientTemplate().queryForList("Sell.listByExpress", param);	
	}

	@Override
	public List<Sell> findByKeyword(String keyword) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("Sell.findByKeyword",
				keyword);
	}
}
