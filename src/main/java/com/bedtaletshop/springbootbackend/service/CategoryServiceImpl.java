package com.bedtaletshop.springbootbackend.service;

import com.bedtaletshop.springbootbackend.exception.NotFoundException;
import com.bedtaletshop.springbootbackend.model.Category;
import com.bedtaletshop.springbootbackend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategoryAll() {
        List<Category> rootCategoryList = categoryRepository.findAll();
        log.debug("category service:"+rootCategoryList);
//        for (Category cat : rootCategoryList) {
//            recursiveTree(cat);
//        }
        return rootCategoryList;
    }

//    private void recursiveTree(Category cat) {
//        System.out.println(cat.getCategoryName());
//        if (cat.getChildren().size() > 0) {
//            for (Category c : cat.getChildren()) {
//                recursiveTree(c);
//            }
//        }
//    }

    @Override
    public Category getCategoryById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent())
            throw new NotFoundException("Product not found with Id:" + id);


        return category.get();
    }
}
