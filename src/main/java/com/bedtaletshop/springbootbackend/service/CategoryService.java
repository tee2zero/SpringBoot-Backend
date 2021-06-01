package com.bedtaletshop.springbootbackend.service;

import com.bedtaletshop.springbootbackend.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategoryAll();
    Category getCategoryById(Long id);
}
