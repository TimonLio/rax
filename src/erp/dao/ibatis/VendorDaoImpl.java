package erp.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.VendorDao;
import erp.model.Vendor;

public class VendorDaoImpl extends SqlMapClientDaoSupport implements VendorDao {

	@Override
	public int create(Vendor newInstance) throws DataAccessException {
		newInstance.setId((Integer) getSqlMapClientTemplate().insert(
				"Vendor.create", newInstance));
		return newInstance.getId();
	}

	@Override
	public Vendor read(int id) throws DataAccessException {
		return (Vendor) getSqlMapClientTemplate().queryForObject("Vendor.read",
				id);
	}

	@Override
	public int update(Vendor transientObject) throws DataAccessException {
		return getSqlMapClientTemplate().update("Vendor.update",
				transientObject);
	}

	@Override
	public int delete(Vendor persistentObject) throws DataAccessException {
		return getSqlMapClientTemplate().delete("Vendor.delete",
				persistentObject.getId());
	}

	@Override
	public int count() throws DataAccessException {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"Vendor.count");
	}

	@Override
	public List<Vendor> list() throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("Vendor.list");
	}

	@Override
	public List<Map> listHistoryOffer(int id) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("Vendor.listHistoryOffer",
				id);
	}
}
