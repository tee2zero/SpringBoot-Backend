package com.bedtaletshop.springbootbackend.service;

import com.bedtaletshop.springbootbackend.model.Product;
import com.bedtaletshop.springbootbackend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    StroageService stroageService;

    @Mock
    SubCategoryService subCategoryService;

//    @Spy
    List<Product> list = new ArrayList<Product>();

    Product productTest;

    @BeforeEach
    void init() {
        productService = new ProductServiceImpl(productRepository, stroageService,subCategoryService);

        productTest = new Product();
        productTest.setId(1L);
        productTest.setName("abcs");

    }

    @Test
    @DisplayName("Should_Success_When_GetAllProduct")
    void getAllProduct() {
        System.out.println(productTest.getId());
        list.add(productTest);


        when(productRepository.findAll(Mockito.any(Sort.class))).thenReturn(list);

        assertEquals(productService.getAllProduct(), list);
        assertNotEquals(productService.getAllProduct(), Arrays.asList(new Product().setId(3L)));
    }

    @Test
    void getProduct() {
    }

    @Test
    void testGetProduct() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void editProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getProductByNameAndStock() {
    }
}