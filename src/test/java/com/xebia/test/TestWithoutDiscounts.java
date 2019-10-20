package com.xebia.test;

import org.junit.Before;
import org.junit.Test;

import com.xebia.main.model.Product;
import com.xebia.main.model.User;
import com.xebia.main.service.ItemService;
import com.xebia.main.serviceimpl.CartServiceImpl;
import com.xebia.main.util.ItemType;
import com.xebia.main.util.UserType;

import static org.junit.Assert.assertEquals;

/*
 * Unit Test Cases for no discount policy for any type and just to see if cart is working
 */
public class TestWithoutDiscounts {

	private CartServiceImpl cart;
	private ItemService item;

	@Before
	public void setUp() {
		User user = new User(UserType.SIMPLE, "John");
		cart = new CartServiceImpl(user);
		item = new Product("something", 1000, ItemType.OTHER);
	}

	@Test
	public void test_emptyCartCostsZero() {
		assertEquals(0, cart.total(), 0.01);
	}

	@Test
	public void test_singleBasicItemCostsItsUnitPrice() {
		cart.add(item);
		assertEquals(item.getUnitPrice(), cart.total(), 0.01);
	}

	@Test
	public void test_multipleBasicItemsCostProportionally() {
		int howMany = 3;
		cart.add(item, howMany);
		assertEquals(howMany * item.getUnitPrice(), cart.total(), 0.01);
	}

	@Test
	public void test_separatelyAdding() {
		int howMany = 3;
		for (int i = howMany; i > 0; i--) {
			cart.add(item);
		}
		assertEquals(howMany * item.getUnitPrice(), cart.total(), 0.01);
	}
}
