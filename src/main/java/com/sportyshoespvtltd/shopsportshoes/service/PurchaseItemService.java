package com.sportyshoespvtltd.shopsportshoes.service;

import java.util.List;

import com.sportyshoespvtltd.shopsportshoes.entity.PurchaseItem;

public interface PurchaseItemService {
	public PurchaseItem getPurchaseItemById(long id);
	public List<PurchaseItem> getAllPurchaseItemsByOrder(Long orderId);
	public List<PurchaseItem> getAllPurchaseItemsByUser(String emailId);
	public int save(PurchaseItem item);
	public int delete(PurchaseItem item);
	public int deleteAll();
	
}
