package com.sportyshoespvtltd.shopsportshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoespvtltd.shopsportshoes.entity.CustomerOrder;
import com.sportyshoespvtltd.shopsportshoes.entity.User;
import com.sportyshoespvtltd.shopsportshoes.repository.CustomerOrderrepository;

@Service(value = "customerOrderService")
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	UserService userService;
	@Autowired(required = true)
	private CustomerOrderrepository customerOrderRepository;
	
	@Override
	public CustomerOrder getOrderById(long orderId) {
		
		return customerOrderRepository.findById(orderId).get();
	}

	@Override
	public List<CustomerOrder> getAllOrders() {
		
		return customerOrderRepository.findAll();
	}

	@Override
	public List<CustomerOrder> getAllOrderByUser(String emailId) {
		User user=new User();
		user= userService.getUserByEmail(emailId);
		return user.getOrders();
	}

	@Override
	public CustomerOrder save(CustomerOrder order) {
		return customerOrderRepository.save(order);		
	}

	@Override
	public int delete(CustomerOrder order) {

		if(order!=null)
		{
			if(customerOrderRepository.findById(order.getOrderId())!=null)
			{
				customerOrderRepository.delete(order);
			return 1;
			}
			else
			{
				return -1;
			}
		}
		else
		{
			return 0;
		}
	}

	@Override
	public int deleteAll() {
		customerOrderRepository.deleteAll();
		return 1;
	}


}
