package com.bedtaletshop.springbootbackend.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnAuthorizeException extends CommonException {

	private static final long serialVersionUID = 6068843060862737298L;
	private final HttpStatus status = HttpStatus.UNAUTHORIZED;
	private final String code = "401";

	public UnAuthorizeException(String message) {
		super(message);
	}
}
