package com.sportyshoespvtltd.shopsportshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoespvtltd.shopsportshoes.entity.PurchaseItem;
import com.sportyshoespvtltd.shopsportshoes.repository.PurchaseItemRepository;
import com.sportyshoespvtltd.shopsportshoes.repository.UserRepository;

@Service(value="purchaseItemService")
public class PurchaseItemServiceImpl implements PurchaseItemService{

	@Autowired
	private PurchaseItemRepository purchageRepository;

	@Autowired
	private UserRepository userRepository;
	@Override
	public PurchaseItem getPurchaseItemById(long id) {
		
		return purchageRepository.findById(id).get();
	}

	@Override
	public List<PurchaseItem> getAllPurchaseItemsByOrder(Long orderId) {
		return purchageRepository.findAll();
	}

	@Override
	public List<PurchaseItem> getAllPurchaseItemsByUser(String emailId) {
	//User user= userRepository.findById(userId).get();
		
		return null;
	}

	@Override
	public int save(PurchaseItem item) {
		purchageRepository.save(item);
		return 1;
	}

	@Override
	public int delete(PurchaseItem item) {
		if(purchageRepository.findById(item.getId())==null)
		{
			return -1;
		}
		else
		{
			purchageRepository.delete(item);
			return 1;
		}
			
		
		
	}

	@Override
	public int deleteAll() {
		
		purchageRepository.deleteAll();
		return 1;
	}


}
