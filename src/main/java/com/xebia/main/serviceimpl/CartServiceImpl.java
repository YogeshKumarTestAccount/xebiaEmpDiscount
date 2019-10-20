package com.xebia.main.serviceimpl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xebia.main.model.User;
import com.xebia.main.service.CartService;
import com.xebia.main.service.DiscountPolicyService;
import com.xebia.main.service.ItemService;
import com.xebia.main.util.UserType;

/**
 * @author Yogesh Kumar
 *
 */
/*
 * Cart Class to calculate complete to add items and contain the final net
 * amount
 */
@Service
public class CartServiceImpl implements CartService {
	private Map<ItemService, Integer> quantities;
	private DiscountPolicyService discountPolicyService;
	private User user;

	public CartServiceImpl(User user) {
		quantities = new LinkedHashMap<ItemService, Integer>();
		this.user = user;
	}

	public CartServiceImpl(User user, DiscountPolicyService discountPolicyService) {
		quantities = new LinkedHashMap<ItemService, Integer>();
		this.user = user;
		this.discountPolicyService = discountPolicyService;
	}

	public CartServiceImpl() {
	}

	public double total() {
		double result = 0;

		/*
		 * Java 8 Foreach with Lambdas expression can not be applied if field is not
		 * final Local variable result defined in an enclosing scope must be final or
		 * effectively final
		 */

		/*
		 * 
		 * quantities.keySet().forEach(eachQty->{ result
		 * +=eachQty.priceForQuantity(quantities.get(eachQty)); });
		 */

		/*
		 * Normal Chain Loop
		 */

		for (ItemService eachqty : quantities.keySet()) {
			result += eachqty.priceForQuantity(quantities.get(eachqty));
		}

		if (discountPolicyService != null) {
			result = discountPolicyService.applyDiscount(result);
		}

		return result;
	}

	public void add(ItemService itemToBuy) {
		add(itemToBuy, 1);
	}

	// To add multiple quantities of item
	public void add(ItemService itemToBuy, Integer numberOfItem) {
		ItemService item;

		// Apply 30% discount If user is an employee of the store
		if (user.getType() == UserType.EMPLOYEE) {
			item = new PromotionPricing(itemToBuy, 30);
		}
		// Apply 10% discount If user is an affiliate of the store,
		else if (user.getType() == UserType.AFFILIATE) {
			item = new PromotionPricing(itemToBuy, 10);
		}

		// apply 5% discount if the user has been a customer for over 2 years.
		else if (user.getType() == UserType.SIMPLE
				&& ChronoUnit.YEARS.between(user.getJoiningDate(), LocalDateTime.now()) >= 2) {
			item = new PromotionPricing(itemToBuy, 5);
		}

		else {
			item = itemToBuy;
		}

		int previousQuantity = quantities.containsKey(item) ? quantities.get(item) : 0;
		quantities.put(item, previousQuantity + numberOfItem);
	}
}
