package com.xebia.main.serviceimpl;

import org.springframework.stereotype.Service;

import com.xebia.main.service.ItemService;
import com.xebia.main.util.ItemType;

/*
 * This Class to implement pricing at each item level
 */

public class PricingPolicy implements ItemService {

    private final ItemService baseItem;
   
	public PricingPolicy(ItemService baseItem) {
        this.baseItem = baseItem;
    }
	public double getUnitPrice() { 
    	return baseItem.getUnitPrice();
    }

    public String getName() { 
    	return baseItem.getName(); 
    }
    
    public ItemType getType() {
    	return baseItem.getType(); 
    }

    public double priceForQuantity(int quantity) {
        return baseItem.priceForQuantity(quantity);
    }
}
