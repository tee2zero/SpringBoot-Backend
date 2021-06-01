package com.bedtaletshop.springbootbackend.exception;

public class CustomNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3329902894421931034L;

	public CustomNotFoundException(String msg) {
		super(msg);
	}
}