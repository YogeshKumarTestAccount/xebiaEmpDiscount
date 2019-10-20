package com.xebia.main.service;

import com.xebia.main.util.ItemType;

/**
 * @author Yogesh Kumar
 *
 */
/*
 * Shared Interface for all the items this is our common gate for all Item.
 */

public interface ItemService {
	double getUnitPrice();

	double priceForQuantity(int quantity);

	String getName();

	ItemType getType();

}
