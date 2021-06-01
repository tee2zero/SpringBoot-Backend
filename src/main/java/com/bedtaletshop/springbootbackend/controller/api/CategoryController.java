package com.bedtaletshop.springbootbackend.controller.api;

import com.bedtaletshop.springbootbackend.model.Category;
import com.bedtaletshop.springbootbackend.model.OrderDetail;
import com.bedtaletshop.springbootbackend.model.SubCategory;
import com.bedtaletshop.springbootbackend.repository.OrderDetailRepository;
import com.bedtaletshop.springbootbackend.service.CategoryService;
import com.bedtaletshop.springbootbackend.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor

@Log4j2
public class CategoryController {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    @GetMapping("/public/getCatAll")
    public ResponseEntity<List<Category>> getCategoryAll() {
        List<Category> categoryList = categoryService.getCategoryAll();
        log.debug("categoryList :{}",categoryList);
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/public/get/{id}")
    public ResponseEntity<Category> getProductById(@PathVariable long id) {
        Category category = categoryService.getCategoryById(id);

        SubCategory subCat = subCategoryService.getSubCategoryById(id);
        log.debug("subCat :{}",subCat.toString());
        return ResponseEntity.ok(category);
    }
}
