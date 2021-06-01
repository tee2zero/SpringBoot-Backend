package com.bedtaletshop.springbootbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bedtaletshop.springbootbackend.model.ResponseMsg;

@RestControllerAdvice
public class WebRestControllerAdvice {

	@ExceptionHandler(CustomNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseMsg handleNotFoundException(CustomNotFoundException ex) {
		ResponseMsg responseMsg = new ResponseMsg(ex.getMessage());
		return responseMsg;
	}
}
