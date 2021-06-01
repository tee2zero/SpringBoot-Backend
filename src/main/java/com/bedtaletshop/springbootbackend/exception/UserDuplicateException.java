package com.bedtaletshop.springbootbackend.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDuplicateException extends CommonException {

	private static final long serialVersionUID = -2096537709891303954L;
	private final HttpStatus status = HttpStatus.NOT_FOUND;
	private final String code = "404";

	public UserDuplicateException(String message) {
		super(message);
	}
}
