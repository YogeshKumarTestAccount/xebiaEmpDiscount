package com.xebia.main.dto;

import java.util.List;

import com.xebia.main.model.Product;
import com.xebia.main.model.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yogesh Kumar
 *
 */

@Getter
@Setter
public class DiscountUserDetailDto {

	private User user;
	private List<Product> listOfProduct;
	private Integer numberOfGroceryItem;
	private Integer numberOfOtherItem;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getListOfProduct() {
		return listOfProduct;
	}

	public void setListOfProduct(List<Product> listOfProduct) {
		this.listOfProduct = listOfProduct;
	}

	public Integer getNumberOfGroceryItem() {
		return numberOfGroceryItem;
	}

	public void setNumberOfGroceryItem(Integer numberOfGroceryItem) {
		this.numberOfGroceryItem = numberOfGroceryItem;
	}

	public Integer getNumberOfOtherItem() {
		return numberOfOtherItem;
	}

	public void setNumberOfOtherItem(Integer numberOfOtherItem) {
		this.numberOfOtherItem = numberOfOtherItem;
	}

}
