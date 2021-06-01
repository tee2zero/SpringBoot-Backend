package com.bedtaletshop.springbootbackend.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StroageException extends CommonException {

	private static final long serialVersionUID = -1688686575728783819L;
	private final HttpStatus status = HttpStatus.BAD_REQUEST;
	private final String code = "400";

	public StroageException(String message) {
		super(message);
	}
}
