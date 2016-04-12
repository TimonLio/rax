package erp.service;

import java.util.List;
import java.util.Map;

import erp.dao.OrderItemDao;
import erp.model.Order;
import erp.model.OrderItem;

public class OrderItemService {

    private OrderItemDao orderItemDao;

    public OrderItemService() {
    }

    public OrderItem getOrderItemById(int id) {
        return orderItemDao.read(id);
    }

    public int createOrderItem(OrderItem obj) {
        return orderItemDao.create(obj);
    }

    public boolean deleteOrderItem(OrderItem obj) {
        boolean ret = false;
        if (null != obj) {
            orderItemDao.delete(obj);
            ret = true;
        }
        return ret;
    }

    public boolean deleteOrderItemById(int id) {
        boolean ret = false;
        OrderItem obj = orderItemDao.read(id);
        if (null != obj) {
            orderItemDao.delete(obj);
            ret = true;
        }
        return ret;
    }

    public boolean updateOrderItem(OrderItem obj) {
        orderItemDao.update(obj);
        return true;
    }

    public List<OrderItem> listByOrder(Order order) {
        return orderItemDao.listByOrderId(order.getId());
    }

    public List<OrderItem> listByOrderId(int orderId) {
        return orderItemDao.listByOrderId(orderId);
    }
    
    public int getCountByWareId(int id) {
    	return orderItemDao.getCountByWareId(id);
    }
    
	public List<OrderItem> listByWareId(int id, int index, int num) {
		return orderItemDao.listByWareId(id, index, num);
	}

    public void setOrderItemDao(OrderItemDao dao) {
        orderItemDao = dao;
    }
    
}
