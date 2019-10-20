package com.xebia.test;


import org.junit.Before;
import org.junit.Test;

import com.xebia.main.model.Product;
import com.xebia.main.model.User;
import com.xebia.main.service.DiscountPolicyService;
import com.xebia.main.service.ItemService;
import com.xebia.main.serviceimpl.CartServiceImpl;
import com.xebia.main.serviceimpl.ThresholdDiscount;
import com.xebia.main.util.ItemType;
import com.xebia.main.util.UserType;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

/*
 * Test Cases for no discount policy
 */
public class TestDiscounts {

    private ItemService groceryItem;
    private ItemService otherItem;
    private User employee;
    private User affiliate;
    private User simpleUser;
    private User simpleUserWith2Years;
    private DiscountPolicyService discountPolicy;

    @Before
    public void setUp() {
    	employee = new User(UserType.EMPLOYEE, "yogesh");
    	affiliate = new User(UserType.AFFILIATE, "Amit");
    	simpleUser = new User(UserType.SIMPLE, "Raj");
    	simpleUserWith2Years = new User(UserType.SIMPLE, "Rohit", LocalDateTime.of(2014, 7, 19, 6, 40, 45));
        groceryItem = new Product("Suger",20,ItemType.GROCERY);
        otherItem = new Product("TV", 222, ItemType.OTHER);
        discountPolicy = new ThresholdDiscount();
    }

    @Test
    public void test_employeeWithGrocery() {
        CartServiceImpl cart = new CartServiceImpl(employee, discountPolicy);
        cart.add(groceryItem, 4);
        // No discount because of grocery item
        assertEquals(80, cart.total(), 0.01);
        
    }
    
    @Test
    public void test_employeeWithOtherItem() {
    	CartServiceImpl cart = new CartServiceImpl(employee, discountPolicy);
        cart.add(otherItem, 4);
        /*
         *  30% discount then 5 dollars off of each 100 dollars of total price because of other item
         *  Total 222 * 4 = 888
         *  After 30% discount = 621.6
         *  After 30 dollars off due to price over $600 = 591.6 
         */
        assertEquals(591.6, cart.total(), 0.01);
        
    }
    
    @Test
    public void test_affiliateWithGrocery() {
    	CartServiceImpl cart = new CartServiceImpl(affiliate, discountPolicy);
        cart.add(groceryItem, 4);
        // No discount because of grocery item
        assertEquals(80, cart.total(), 0.01);
        
    }
    
    @Test
    public void test_affiliateWithOtherItem() {
    	CartServiceImpl cart = new CartServiceImpl(affiliate, discountPolicy);
        cart.add(otherItem, 4);
        /*
         *  10% discount then 5 dollars off of each 100 dollars of total price because of other item
         *  Total 222 * 4 = 888
         *  After 10% discount = 799.2
         *  After 35 dollars off due to price over $700 = 591.6 
         */
        assertEquals(764.2, cart.total(), 0.01);
        
    }
    
    @Test
    public void test_simpleUserWithGrocery() {
    	CartServiceImpl cart = new CartServiceImpl(simpleUser, discountPolicy);
        cart.add(groceryItem, 4);
        // No discount because of grocery item
        assertEquals(80, cart.total(), 0.01);
        
    }
    
    @Test
    public void test_simpleUserWithOtherItem() {
    	CartServiceImpl cart = new CartServiceImpl(simpleUser, discountPolicy);
        cart.add(otherItem, 4);
        /*
         *  Total 222 * 4 = 888
         *  No percentage discount because user is a customer for less than 2 years
         *  After 40 dollars off due to price over $800 = 848 
         */
        assertEquals(848, cart.total(), 0.01);        
    }
    
    @Test
    public void test_simpleUserWith2YearsWithGrocery() {
    	CartServiceImpl cart = new CartServiceImpl(simpleUserWith2Years, discountPolicy);
        cart.add(groceryItem, 4);
        // No discount because of grocery item
        assertEquals(80, cart.total(), 0.01);
        
    }
    
    @Test
    public void test_simpleUserWith2YearsWithOtherItem() {
    	CartServiceImpl cart = new CartServiceImpl(simpleUserWith2Years, discountPolicy);
        cart.add(otherItem, 4);
        /*
         *  5% discount then 5 dollars off of each 100 dollars of total price because of other item
         *  Total 222 * 4 = 888
         *  After 5% discount = 843.6
         *  After 40 dollars off due to price over $800 =803.6 
         */
        assertEquals(803.6, cart.total(), 0.01);
        
    }
    
    @Test
    public void test_employeeWithGroceryAndOtherItem() {
    	CartServiceImpl cart = new CartServiceImpl(employee, discountPolicy);
        cart.add(groceryItem, 4);
        cart.add(otherItem, 4);
        /*
         *  Total (20 * 4) + (222 * 4) = 968
         *  No discount on grocery items = 968 still
         *  After 30% discount on 4 other items totalling 888 = 701.6
         *  After 35 dollars off due to price over $700 =666.6 
         */
        assertEquals(666.6, cart.total(), 0.01);
        
    }
}
