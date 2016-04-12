package erp.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.KeywordDao;

public class KeywordDaoImpl extends SqlMapClientDaoSupport implements
		KeywordDao {

	@Override
	public boolean create(String newInstance) throws DataAccessException {
		try {
			getSqlMapClientTemplate().insert("Keyword.create", newInstance);
		} catch (DataAccessException ex) {
			return false;
		}
		return true;
	}

	@Override
	public String read(String key) throws DataAccessException {
		return (String) getSqlMapClientTemplate().queryForObject(
				"Keyword.read", key);
	}

	@Override
	public boolean update(String transientObject) throws DataAccessException {
		return false;
	}

	@Override
	public boolean delete(String persistentObject) throws DataAccessException {
		getSqlMapClientTemplate().delete("Keyword.delete", persistentObject);
		return true;
	}

	@Override
	public int count(int status) throws DataAccessException {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"Keyword.count");
	}

	@Override
	public List<String> list() throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("Keyword.list");
	}

}
