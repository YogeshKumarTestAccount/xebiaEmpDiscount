package com.xebia.main.model;

import java.time.LocalDateTime;

import com.xebia.main.util.UserType;

/**
 * @author Yogesh Kumar
 *
 */

/*
 * This Model Class store the information about the user
 */
public class User {

	private  UserType type;
	private  String userName;
	private  LocalDateTime joiningDate;

	/*
	 * We can add other information as per future scope of the application.
	 */

	public User(UserType type, String userName) {
		this.type = type;
		this.userName = userName;
		joiningDate = LocalDateTime.now();
	}

	public User(UserType type, String userName, LocalDateTime joiningDate) {
		this.type = type;
		this.userName = userName;
		this.joiningDate = joiningDate;
	}

	public UserType getType() {
		return type;
	}

	public String getUserName() {
		return userName;
	}

	public LocalDateTime getJoiningDate() {
		return joiningDate;
	}

	public User() {
		super();
	}
	
	
	

}
