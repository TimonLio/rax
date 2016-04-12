package erp.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.OrderItemDao;
import erp.model.OrderItem;

public class OrderItemDaoImpl extends SqlMapClientDaoSupport implements
        OrderItemDao {

    @Override
    public int create(OrderItem newInstance) throws DataAccessException {
        newInstance.setId((Integer) getSqlMapClientTemplate().insert(
                "OrderItem.create", newInstance));
        return newInstance.getId();
    }

    @Override
    public OrderItem read(int id) throws DataAccessException {
        return (OrderItem) getSqlMapClientTemplate().queryForObject(
                "OrderItem.read", id);
    }

    @Override
    public int update(OrderItem transientObject) throws DataAccessException {
        return getSqlMapClientTemplate().update("OrderItem.update",
                transientObject);
    }

    @Override
    public int delete(OrderItem persistentObject) throws DataAccessException {
        return getSqlMapClientTemplate().delete("OrderItem.delete",
                persistentObject.getId());
    }

    @Override
    public List<OrderItem> listByOrderId(int orderId) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("OrderItem.list", orderId);
    }
    
	@Override
	public List<OrderItem> listByWareId(int id, int index, int num) throws DataAccessException {
		Map param = new HashMap();
		param.put("id", id);
		param.put("index", index);
		param.put("number", num);
		return getSqlMapClientTemplate().queryForList("OrderItem.listByWareId",
				param);
	}
	
	@Override
	public int getCountByWareId(int id) throws DataAccessException {
		return (Integer) getSqlMapClientTemplate().queryForObject("OrderItem.countByWareId",
				id);
	}

}
