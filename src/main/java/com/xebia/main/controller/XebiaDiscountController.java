package com.xebia.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xebia.main.dto.DiscountUserDetailDto;
import com.xebia.main.model.Product;
import com.xebia.main.model.User;
import com.xebia.main.service.CartService;
import com.xebia.main.service.DiscountPolicyService;
import com.xebia.main.service.ItemService;
import com.xebia.main.serviceimpl.CartServiceImpl;
import com.xebia.main.serviceimpl.ThresholdDiscount;
import com.xebia.main.util.ItemType;
import com.xebia.main.util.UserType;

/**
 * @author Yogesh Kumar
 *
 */
@RestController
public class XebiaDiscountController {
	private static final Logger logger = LoggerFactory.getLogger(XebiaDiscountController.class);

	@Autowired
	private CartService cartService;

	@SuppressWarnings("unlikely-arg-type")
	@PostMapping(value = "/xebia/totalDiscount/")
	public double totalAmmountToBePaidAfterDiscount(@RequestBody DiscountUserDetailDto discountUserDetailDto) {

		String userName = discountUserDetailDto.getUser().getUserName();
		final String GROCERY="GROCERY";
		final String OTHER="OTHER";

		UserType userType = discountUserDetailDto.getUser().getType();

		DiscountPolicyService discountPolicy = new ThresholdDiscount();
		User user = null;
		if (userType.equals(UserType.EMPLOYEE)) {
			user = new User(userType, userName);
		}
		if (userType.equals(UserType.AFFILIATE)) {
			user = new User(userType, userName);
		}
		if (userType.equals(UserType.SIMPLE)) {
			user = new User(userType, userName);
		}

		CartServiceImpl cartService = new CartServiceImpl(user, discountPolicy);

		discountUserDetailDto.getListOfProduct().stream().forEach(product -> {

			if (product.getType().name().toString().equals(GROCERY)) {
				ItemService groceryItem = new Product(product.getName(), product.getUnitPrice(),ItemType.GROCERY );
				cartService.add(groceryItem, discountUserDetailDto.getNumberOfGroceryItem());
			}

			if (product.getType().name().toString().equals(OTHER)) {
				ItemService OtherItem = new Product(product.getName(), product.getUnitPrice(),ItemType.OTHER);
				cartService.add(OtherItem, discountUserDetailDto.getNumberOfOtherItem());
			}

		});

		logger.debug("Total Result After Discount ..");

		return cartService.total();

	}

}
