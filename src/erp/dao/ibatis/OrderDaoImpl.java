package erp.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.OrderDao;
import erp.model.Order;

public class OrderDaoImpl extends SqlMapClientDaoSupport implements OrderDao {

	@Override
	public int create(Order newInstance) throws DataAccessException {
		newInstance.setId((Integer) getSqlMapClientTemplate().insert(
				"Order.create", newInstance));
		return newInstance.getId();
	}

	@Override
	public Order read(int id) throws DataAccessException {
		return (Order) getSqlMapClientTemplate().queryForObject("Order.read",
				id);
	}

	@Override
	public int update(Order transientObject) throws DataAccessException {
		return getSqlMapClientTemplate()
				.update("Order.update", transientObject);
	}

	@Override
	public int delete(Order persistentObject) throws DataAccessException {
		return getSqlMapClientTemplate().delete("Order.delete",
				persistentObject.getId());
	}

	@Override
	public int count(int status) throws DataAccessException {
		Map param = new HashMap();
		param.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"Order.count", param);
	}

	@Override
	public List<Order> list(int status, int index, int num)
			throws DataAccessException {
		Map param = new HashMap();
		param.put("index", index);
		param.put("number", num);
		if (status >= 0) {
			param.put("status", status);
		}
		return getSqlMapClientTemplate().queryForList("Order.list", param);
	}
	
}
