package com.bedtaletshop.springbootbackend.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3948892078969408870L;

	public ProductNotFoundException(Long id) {
		super("Cannot found id:" + id);
	}
}
