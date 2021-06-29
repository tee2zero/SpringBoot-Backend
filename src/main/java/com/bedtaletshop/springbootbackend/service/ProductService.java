package com.bedtaletshop.springbootbackend.service;

import java.util.List;

import com.bedtaletshop.springbootbackend.controller.request.ProductRequest;
import com.bedtaletshop.springbootbackend.model.Product;

public interface ProductService {

	public List<Product> getAllProduct();

	public Product getProduct(Long id);

	public List<Product> getProduct(String name);

	public Product createProduct(ProductRequest product) throws Exception;

	public Product editProduct(ProductRequest product, Long id);

	public void deleteProduct(Long id);

	public List<Product> getProductByNameAndStock(String name, int stock);

}
