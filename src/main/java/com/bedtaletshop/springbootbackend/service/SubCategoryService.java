package com.bedtaletshop.springbootbackend.service;

import com.bedtaletshop.springbootbackend.model.SubCategory;

import java.util.List;

public interface SubCategoryService {

    public List<SubCategory> getSubCategoryAll();
    public SubCategory getSubCategoryById(Long id);
}
