package com.bedtaletshop.springbootbackend.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
// Spring Ninja
public abstract class CommonException extends RuntimeException {

	private static final long serialVersionUID = -7072696398216948477L;
	protected HttpStatus status;
	protected String code;

	public CommonException(String message) {
		super(message);
	}

}
