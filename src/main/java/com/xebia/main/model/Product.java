package com.xebia.main.model;

import com.xebia.main.service.ItemService;
import com.xebia.main.util.ItemType;

/**
 * @author Yogesh Kumar
 *
 */

/*
 * This Class for actual product items which you want to add.
 */
public class Product implements ItemService {

	private final String name;
	private final double unitPrice;
	private final ItemType type;

	public Product(String name, double priceInDollars, ItemType type) {
		this.name = name;
		this.unitPrice = priceInDollars;
		this.type = type;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public String getName() {
		return name;
	}

	public double priceForQuantity(int quantity) {
		return unitPrice * quantity;
	}

	public ItemType getType() {
		return type;
	}
}
