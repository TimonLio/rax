package erp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import erp.model.Order;

public interface OrderDao {

	public int create(Order newInstance) throws DataAccessException;

	public Order read(int id) throws DataAccessException;

	public int update(Order transientObject) throws DataAccessException;

	public int delete(Order persistentObject) throws DataAccessException;

	public int count(int status) throws DataAccessException;

	public List<Order> list(int status, int index, int num)
			throws DataAccessException;

}
