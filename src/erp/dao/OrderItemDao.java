package erp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import erp.model.OrderItem;

public interface OrderItemDao {

	public int create(OrderItem newInstance) throws DataAccessException;

	public OrderItem read(int id) throws DataAccessException;

	public int update(OrderItem transientObject) throws DataAccessException;

	public int delete(OrderItem persistentObject) throws DataAccessException;

	public List<OrderItem> listByOrderId(int orderId)
			throws DataAccessException;

	public List<OrderItem> listByWareId(int id, int index, int num)
			throws DataAccessException;

	public int getCountByWareId(int id) throws DataAccessException;
}
