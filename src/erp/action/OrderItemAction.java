package erp.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Order;
import erp.model.OrderItem;
import erp.service.OrderItemService;
import erp.service.OrderService;
import erp.service.WareService;

public class OrderItemAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(OrderItemAction.class);

	private OrderService orderService = null;
	private OrderItemService orderItemService = null;
	private WareService wareService = null;

	private int id;
	private int orderId;
	private OrderItem orderItem;
	private List<OrderItem> orderItemList;

	public String get() throws Exception {
		try {
			orderItem = orderItemService.getOrderItemById(id);
			if (orderItem != null) {
				orderItem.setWare(wareService
						.getWareById(orderItem.getWareId()));
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		
		orderItemList = orderItemService.listByOrderId(orderId);
		for (OrderItem item : orderItemList) {
			item.setWare(wareService.getWareById(item.getWareId()));
		}
		return SUCCESS;
	}

	public String delete() throws Exception {
		try {
			orderItemService.deleteOrderItemById(id);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		try {
			if (orderId == 0) {
				Order order = new Order();
				orderId = orderService.createOrder(order);
			}
			OrderItem newItem = new OrderItem();
			newItem.setId(id);
			newItem.setOrderId(orderId);
			newItem.setWareId(orderItem.getWareId());
			newItem.setCost(orderItem.getCost());
			newItem.setNumber(orderItem.getNumber());
			if (id > 0) {
				orderItemService.updateOrderItem(newItem);
			} else {
				id = orderItemService.createOrderItem(newItem);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setOrderItemService(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public void setWareService(WareService wareService) {
		this.wareService = wareService;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

}
