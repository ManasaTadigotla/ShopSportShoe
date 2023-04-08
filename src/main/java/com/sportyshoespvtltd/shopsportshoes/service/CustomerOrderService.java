package com.sportyshoespvtltd.shopsportshoes.service;

import java.util.List;

import com.sportyshoespvtltd.shopsportshoes.entity.CustomerOrder;

public interface CustomerOrderService {
	public CustomerOrder getOrderById(long orderId);
	public List<CustomerOrder> getAllOrders();
	public List<CustomerOrder> getAllOrderByUser(String emailId);
	public CustomerOrder save(CustomerOrder order);
	public int delete(CustomerOrder order);
	public int deleteAll();
}
