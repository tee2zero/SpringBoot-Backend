package com.bedtaletshop.springbootbackend.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {

	@NotEmpty
	@Size(min = 2)
	private String name;
	private MultipartFile image;
	private int price;
	private int stock;
	private Long subCategoryId;
}
