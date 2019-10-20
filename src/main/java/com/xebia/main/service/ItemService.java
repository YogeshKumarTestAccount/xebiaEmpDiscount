package com.xebia.main.service;

import org.springframework.stereotype.Service;

import com.xebia.main.util.ItemType;

/*
 * Shared Interface for all the items this is our common gate for all Item. 
 */

public interface ItemService {
    double getUnitPrice();
    
    double priceForQuantity(int quantity);
    
    String getName();
    
    ItemType getType();
   
}
