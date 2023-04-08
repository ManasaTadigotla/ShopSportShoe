package com.sportyshoespvtltd.shopsportshoes.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoespvtltd.shopsportshoes.entity.CartItem;
import com.sportyshoespvtltd.shopsportshoes.entity.CustomerOrder;
import com.sportyshoespvtltd.shopsportshoes.entity.Product;
import com.sportyshoespvtltd.shopsportshoes.entity.PurchaseItem;
import com.sportyshoespvtltd.shopsportshoes.entity.User;
import com.sportyshoespvtltd.shopsportshoes.service.CustomerOrderService;
import com.sportyshoespvtltd.shopsportshoes.service.ProductService;
import com.sportyshoespvtltd.shopsportshoes.service.PurchaseItemService;
import com.sportyshoespvtltd.shopsportshoes.service.UserService;

@RestController
public class CartController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	private CustomerOrderService customerOrderService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PurchaseItemService purchaseItemService;
	
	
	
	private int cartCount;
	

	@GetMapping("/addtocart/{productId}")
	public boolean addToCart(long productId,HttpServletRequest request)
	{
		boolean isAdded=false;
		HttpSession session= (HttpSession) request.getSession();
		List<CartItem> cartItems = new ArrayList<CartItem>();
		if (session.getAttribute("cart_items") != null)
			cartItems = (List<CartItem>) session.getAttribute("cart_items");

		if (isItemInCart(cartItems, productId)) {
			System.out.println("Item already in cart - quantity increased by 1");
		} else {
			Product product = productService.getProductById(productId);
			CartItem item = new CartItem();
			item.setProductId(productId);
			item.setQuantity(1);
			item.setRate(product.getPrice());
			BigDecimal dprice = item.getRate().multiply(new BigDecimal(item.getQuantity()));
			item.setRate(dprice);
			//item.setProducName(null);
			item.setImageUrl(product.getImageUrl());

			cartItems.add(item);
			isAdded=true;
		}
		
		BigDecimal totalCost = getCartValue(cartItems);
		session.setAttribute("cartCount", cartCount);
		session.setAttribute("cartValue", totalCost);
		session.setAttribute("cart_items", cartItems);
		return isAdded;
	}


	@DeleteMapping("/deletefromcart/{productId}")
	public List<CartItem> deleteFromCart(@PathVariable Long productId,HttpServletRequest request)
	{
	HttpSession session = request.getSession();
	List<CartItem> cartItems = new ArrayList<CartItem>();
	if (session.getAttribute("cart_items") != null)
		cartItems = (List<CartItem>) session.getAttribute("cart_items");

	for (CartItem item : cartItems) {
		if (item.getProductId() == productId) {
			if (item.getQuantity() == 1) {
				cartItems.remove(item);

				BigDecimal totalValue = getCartValue(cartItems);
				session.setAttribute("cartValue", totalValue);
				System.out.println("###Total " + totalValue);
				break;
			} else {
				item.setQuantity(item.getQuantity() - 1);
				BigDecimal totalValue = getCartValue(cartItems);
				session.setAttribute("cartValue", totalValue);
				System.out.println("###Total " + totalValue);
				break;
			}
		}
	}
	session.setAttribute("cart_items", cartItems);
	return cartItems;

}

	@GetMapping("/showcartitems")
	public List<CartItem> viewCart(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String useremailId = (String) session.getAttribute("userEmailId");
		if (useremailId!=null) {
			session.removeAttribute("errMessage");

		}

		List<CartItem> cartItems = new ArrayList<CartItem>();
		if (session.getAttribute("cart_items") != null)
			cartItems = (List<CartItem>) session.getAttribute("cart_items");

		// get total of all cart items
		BigDecimal totalValue = getCartValue(cartItems);
		session.setAttribute("cartValue", totalValue);
		session.setAttribute("cartItems", cartItems);
		return cartItems;

	}
	
	@GetMapping("/makepayment")
	public boolean payNow(HttpServletRequest request)
	
	{
		boolean isPaid=false;
		HttpSession session = request.getSession();
		String useremailId = (String) session.getAttribute("userEmailId");

		List<CartItem> cartItems = new ArrayList<CartItem>();
		if (session.getAttribute("cart_items") != null)
			cartItems = (List<CartItem>) session.getAttribute("cart_items");
		BigDecimal totalValue = getCartValue(cartItems);

		User user = userService.getUserByEmail(useremailId);
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setUser(user);
		customerOrder.setOrderDate(java.sql.Date.valueOf(LocalDate.now(ZoneId.systemDefault())));
		customerOrder.setTotalPrice(totalValue);
		customerOrder = customerOrderService.save(customerOrder);

		for (CartItem item : cartItems) {
			PurchaseItem pItem = new PurchaseItem();
			pItem.setOrder(customerOrder);
			pItem.setId(item.getProductId());
			pItem.setUser(user);
			pItem.setUnitprice(item.getRate());
			pItem.setQuantity(item.getQuantity());
			//pItem.setTotaPrice(item.ge());

			purchaseItemService.save(pItem);
		}
		isPaid=true;
		session.setAttribute("cartValue", 0);
		session.setAttribute("cartItems", null);
		cartCount = 0;
		session.setAttribute("cartCount", cartCount);

		return isPaid;

	}

	@GetMapping("/checkout")
	public String checkout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String useremailId = (String) session.getAttribute("userEmailId");
		if (useremailId==null) {
			session.setAttribute("errMessage", "Please Login before checkout!!");
			return "Session Expired";
		}
		BigDecimal cartValue = (BigDecimal) session.getAttribute("cartValue");

		System.out.println(cartValue);
		if (cartValue != null) {
			//model.addAttribute("cartTotal", session.getAttribute("cartValue"));
			BigDecimal shipping = (BigDecimal) ((BigDecimal) session.getAttribute("cartValue"))
					.multiply(new BigDecimal(0.10)).setScale(2, RoundingMode.CEILING);
			//model.addAttribute("shipping", shipping);
			BigDecimal total = shipping.add(cartValue).setScale(2, RoundingMode.CEILING);
			//model.addAttribute("total", total);

		} else {
			//model.addAttribute("cartTotal", 0);
			//model.addAttribute("shipping", 0);
			//model.addAttribute("total", 0);
		}
		return "checkout";
	}

	@PostMapping("/confirmorder")
	public boolean completePurchase(HttpServletRequest request) {
		
		boolean isPurchased=false;
		HttpSession session = request.getSession();
		String useremailId = (String) session.getAttribute("userEmailId");

		List<CartItem> cartItems = new ArrayList<CartItem>();
		if (session.getAttribute("cart_items") != null)
			cartItems = (List<CartItem>) session.getAttribute("cart_items");
		BigDecimal totalValue = getCartValue(cartItems);

		User user = userService.getUserByEmail(useremailId);
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setUser(user);
		customerOrder.setOrderDate(java.sql.Date.valueOf(LocalDate.now(ZoneId.systemDefault())));
		customerOrder.setTotalPrice(totalValue);
		customerOrder = customerOrderService.save(customerOrder);

		for (CartItem item : cartItems) {
			PurchaseItem purchaseItem = new PurchaseItem();
			purchaseItem.setOrder(customerOrder);
			//purchaseItem.setPoduct(productService.getProductById(item.getProductId()));
			purchaseItem.setUser(user);
			purchaseItem.setUnitprice(item.getRate());
			purchaseItem.setQuantity(item.getQuantity());
			purchaseItem.setTotaPrice(item.getRate());

			purchaseItemService.save(purchaseItem);
		}
		isPurchased=true;
		session.setAttribute("cartValue", 0);
		session.setAttribute("cartItems", null);
		cartCount = 0;
		session.setAttribute("cartCount", cartCount);

		return isPurchased;
	}

	private boolean isItemInCart(List<CartItem> cartItems, Long productId) {
		boolean isAvailable = false;

		for (CartItem item : cartItems) {
			if (productId == item.getProductId()) {
				item.setQuantity(item.getQuantity() + 1);
				isAvailable = true;
				break;
			}
		}
		return isAvailable;
		}
	
	private BigDecimal getCartValue(List<CartItem> list) {
		BigDecimal total = new BigDecimal(0.0);
		cartCount = 0;

		for (CartItem item : list) {

			cartCount = cartCount + item.getQuantity();
			BigDecimal dprice = item.getRate().multiply(new BigDecimal(item.getQuantity()));
			total = total.add(dprice);
		}
		return total;
	}

}
