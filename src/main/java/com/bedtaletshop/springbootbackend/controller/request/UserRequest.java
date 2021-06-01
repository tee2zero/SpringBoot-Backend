package com.bedtaletshop.springbootbackend.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

	@NotEmpty
	@Size(min = 1, max = 100)
	private String username;

	@NotEmpty
	@Length(min = 8, message = "The field must be at least {min} characters")
	private String password;

	@NotEmpty
	private String role;
}
