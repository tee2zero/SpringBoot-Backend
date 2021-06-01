package com.bedtaletshop.springbootbackend.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Spring Ninja
public class NotFoundException extends CommonException {

	private static final long serialVersionUID = 2584041582678967709L;
	private final HttpStatus status = HttpStatus.NOT_FOUND;
	private final String code = "404";

	public NotFoundException(String message) {
		super(message);
	}

}
