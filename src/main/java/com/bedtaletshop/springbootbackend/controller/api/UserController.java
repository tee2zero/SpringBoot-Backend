package com.bedtaletshop.springbootbackend.controller.api;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bedtaletshop.springbootbackend.controller.request.UserRequest;
import com.bedtaletshop.springbootbackend.exception.NotFoundException;
import com.bedtaletshop.springbootbackend.model.User;
import com.bedtaletshop.springbootbackend.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/auth")
@Log4j2
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	public User regsiter(@Valid @RequestBody UserRequest user, BindingResult bindingResult) {
		log.debug(user.toString());
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fileError -> {
				throw new NotFoundException(fileError.getField().concat(":").concat(fileError.getDefaultMessage()));
			});
		}

		return userService.register(user);
	}
}
