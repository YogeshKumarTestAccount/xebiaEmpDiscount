package com.xebia.main.serviceimpl;

import org.springframework.stereotype.Service;

import com.xebia.main.service.DiscountPolicyService;

/*
 * This Service  will generate the discount of $5 for every 100 dollars in a Cart Service
 */
@Service
public class ThresholdDiscount implements DiscountPolicyService {

	@Override
	public double applyDiscount(double totalAmount) {
		
		if (totalAmount < 100) {
			return totalAmount;
		}
		
		int discountFactor = (int) totalAmount / 100;
		double discount = discountFactor * 5;
		return totalAmount - discount; 
	}

}
