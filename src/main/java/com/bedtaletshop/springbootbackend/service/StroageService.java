package com.bedtaletshop.springbootbackend.service;

import org.springframework.web.multipart.MultipartFile;

public interface StroageService {

	void init();

	String store(MultipartFile file);
}
