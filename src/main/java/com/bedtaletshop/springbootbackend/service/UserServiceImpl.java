package com.bedtaletshop.springbootbackend.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bedtaletshop.springbootbackend.controller.request.UserRequest;
import com.bedtaletshop.springbootbackend.exception.UserDuplicateException;
import com.bedtaletshop.springbootbackend.model.User;
import com.bedtaletshop.springbootbackend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User register(UserRequest user) {
		User result = userRepository.findByUsername(user.getUsername());
		if (result == null) {
			result = new User().setUsername(user.getUsername())
					.setPassword(bCryptPasswordEncoder.encode(user.getPassword())).setRole(user.getRole());
			return userRepository.save(result);
		}
		throw new UserDuplicateException("Username existing:" + user.getUsername());
	}

	@Override
	public User findUserByUsername(String username) {
		Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

}
