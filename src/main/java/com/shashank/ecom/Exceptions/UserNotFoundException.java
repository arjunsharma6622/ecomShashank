package com.shashank.ecom.Exceptions;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(String message) {
	super(message);
	}
}
