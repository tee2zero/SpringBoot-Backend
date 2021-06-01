package com.bedtaletshop.springbootbackend.service;

import java.util.List;
import java.util.Optional;

import com.bedtaletshop.springbootbackend.model.SubCategory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bedtaletshop.springbootbackend.controller.request.ProductRequest;
import com.bedtaletshop.springbootbackend.exception.NotFoundException;
import com.bedtaletshop.springbootbackend.model.Product;
import com.bedtaletshop.springbootbackend.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final StroageService stroageService;
	private final SubCategoryService subCategoryService;

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
	}

	@Override
	public Product getProduct(Long id) {
		Optional<Product> product = productRepository.findById(id);

		log.debug(product + " " + product.isPresent());

		if (!product.isPresent())
			throw new NotFoundException("Product not found with Id:" + id);

		return product.get();
	}

	@Override
	public List<Product> getProduct(String name) {
		List<Product> productFindout = productRepository.findByName(name);

		if (productFindout.isEmpty())
			throw new NotFoundException("Product not found with Name:" + name);

		return productFindout;
	}

	@Override
	public Product createProduct(ProductRequest product) {

		String filename = stroageService.store(product.getImage());
		log.debug("filename upload:{}", filename);

		SubCategory subCategory = subCategoryService.getSubCategoryById(product.getSubCategoryId());

		Product data = new Product();
		data.setName(product.getName()).setImage(filename).setPrice(product.getPrice()).setStock(product.getStock()).setSubCategory(subCategory);

		return productRepository.save(data);
	}

	@Override
	public Product editProduct(ProductRequest product, Long id) {

		String filename = stroageService.store(product.getImage());

		Optional<Product> productFind = productRepository.findById(id);
		if (!productFind.isPresent())
			new NotFoundException("Product not found. " + id);

		Product result = productFind.get();
		log.debug("product.getSubCategoryId():"+product.getSubCategoryId());
		SubCategory subCategory = subCategoryService.getSubCategoryById(product.getSubCategoryId());

		result.setName(product.getName()).setImage(filename).setPrice(product.getPrice()).setStock(product.getStock()).setSubCategory(subCategory);

		return productRepository.save(result);
	}

	@Override
	public void deleteProduct(Long id) {
		try {
			productRepository.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException("Product not found with Id:" + id);
		}
	}

	@Override
	public List<Product> getProductByNameAndStock(String name, int stock) {
		List<Product> productFindout = productRepository.findByNameContainingAndStockGreaterThanOrderByStockDesc(name,
				stock);

		if (productFindout.isEmpty())
			throw new NotFoundException("Product not found with Name:" + name + " Stock:" + stock);

		return productFindout;
	}

}
