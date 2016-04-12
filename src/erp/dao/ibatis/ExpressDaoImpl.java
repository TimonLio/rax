package erp.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.ExpressDao;
import erp.model.Express;

public class ExpressDaoImpl extends SqlMapClientDaoSupport implements
		ExpressDao {

	@Override
	public int create(Express newInstance) throws DataAccessException {
		newInstance.setId((Integer) getSqlMapClientTemplate().insert(
				"Express.create", newInstance));
		return newInstance.getId();
	}

	@Override
	public Express read(int id) throws DataAccessException {
		return (Express) getSqlMapClientTemplate().queryForObject(
				"Express.read", id);
	}

	@Override
	public int update(Express transientObject) throws DataAccessException {
		return getSqlMapClientTemplate().update("Express.update",
				transientObject);
	}

	@Override
	public int delete(Express persistentObject) throws DataAccessException {
		return getSqlMapClientTemplate().delete("Express.delete",
				persistentObject.getId());
	}

	@Override
	public int count() throws DataAccessException {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"Express.count");
	}

	@Override
	public List<Express> list() throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("Express.list");
	}

}
