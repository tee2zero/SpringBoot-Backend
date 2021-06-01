package com.bedtaletshop.springbootbackend.service;

import com.bedtaletshop.springbootbackend.controller.request.UserRequest;
import com.bedtaletshop.springbootbackend.model.User;

public interface UserService {
	User register(UserRequest user);

	User findUserByUsername(String username);
}
