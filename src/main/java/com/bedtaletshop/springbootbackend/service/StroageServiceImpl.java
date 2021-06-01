package com.bedtaletshop.springbootbackend.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bedtaletshop.springbootbackend.exception.StroageException;

@Service
public class StroageServiceImpl implements StroageService {

	@Value("${app.upload.path}")
	private String path;

	private Path rootLocation;

	@Override
	@PostConstruct
	public void init() {
		this.rootLocation = Paths.get(path);
		try {
			if (!rootLocation.toFile().exists())
				Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StroageException("Could not init stroage:" + e.getMessage());
		}
	}

	@Override
	public String store(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			return null;
		}
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			fileName = UUID.randomUUID().toString().concat(".")
					.concat(fileName.substring(fileName.lastIndexOf(".") + 1));

			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
				return fileName;
			}

		} catch (IOException e) {
			throw new StroageException("Fail to stroe:".concat(fileName).concat(", ").concat(e.getMessage()));
		}
	}

}
