package com.xebia.main.service;

import org.springframework.stereotype.Service;

/*
 * This Service Interface for Overall Discount Policy provide for all of the Cart
 */


public interface DiscountPolicyService {
	double applyDiscount(double totalAmount);
}
