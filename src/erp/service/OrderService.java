package erp.service;

import java.util.List;
import java.util.Map;

import erp.dao.OrderDao;
import erp.model.Order;

public class OrderService {

	private OrderDao orderDao;

	public OrderService() {
	}

	public Order getOrderById(int id) {
		return orderDao.read(id);
	}

	public int createOrder(Order obj) {
		return orderDao.create(obj);
	}

	public boolean deleteOrder(Order obj) {
		boolean ret = false;
		if (null != obj) {
			orderDao.delete(obj);
			ret = true;
		}
		return ret;
	}

	public boolean deleteOrderById(int id) {
		boolean ret = false;
		Order obj = orderDao.read(id);
		if (null != obj) {
			orderDao.delete(obj);
			ret = true;
		}
		return ret;
	}

	public boolean updateOrder(Order obj) {
		orderDao.update(obj);
		return true;
	}

	public int getCount(int status) {
		return orderDao.count(status);
	}

	public List<Order> list(int status, int index, int num) {
		return orderDao.list(status, index, num);
	}
	
	public void setOrderDao(OrderDao dao) {
		orderDao = dao;
	}

}
