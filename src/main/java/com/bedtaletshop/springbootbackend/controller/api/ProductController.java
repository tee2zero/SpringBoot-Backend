package com.bedtaletshop.springbootbackend.controller.api;

import java.util.List;

import javax.validation.Valid;

import com.bedtaletshop.springbootbackend.model.Category;
import com.bedtaletshop.springbootbackend.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bedtaletshop.springbootbackend.controller.request.ProductRequest;
import com.bedtaletshop.springbootbackend.exception.NotFoundException;
import com.bedtaletshop.springbootbackend.model.Product;
import com.bedtaletshop.springbootbackend.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor

@Log4j2
public class ProductController {


	private final ProductService productService;

	@GetMapping("/public/getAll")
	public ResponseEntity<List<Product>> getProducts() {
		return ResponseEntity.ok(productService.getAllProduct());
	}

	@GetMapping("/public/get/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable long id) {
		return ResponseEntity.ok(productService.getProduct(id));
	}

	@GetMapping("/public/get/name/{name}")
	public ResponseEntity<List<Product>> getProductByNmae(@PathVariable String name) {
		return ResponseEntity.ok(productService.getProduct(name));

	}

	@GetMapping(path = "/public/search", params = { "name", "stock" })
	public ResponseEntity<List<Product>> getProductByNameAndStock(@RequestParam String name, @RequestParam int stock) {
		return ResponseEntity.ok(productService.getProductByNameAndStock(name, stock));

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@Valid ProductRequest productReq, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fileError -> {
				throw new NotFoundException(fileError.getField().concat(":").concat(fileError.getDefaultMessage()));
			});
		}

		return ResponseEntity.ok(productService.createProduct(productReq));
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<Product> editProduct(@Valid ProductRequest productReq, BindingResult bindingResult,
			@PathVariable long id) {

		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fileError -> {
				throw new NotFoundException(fileError.getField().concat(":").concat(fileError.getDefaultMessage()));
			});
		}

		return ResponseEntity.ok(productService.editProduct(productReq, id));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long id) {

		try {
			productService.deleteProduct(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
