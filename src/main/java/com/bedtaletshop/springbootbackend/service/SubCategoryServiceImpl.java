package com.bedtaletshop.springbootbackend.service;

import com.bedtaletshop.springbootbackend.exception.NotFoundException;
import com.bedtaletshop.springbootbackend.model.Category;
import com.bedtaletshop.springbootbackend.model.SubCategory;
import com.bedtaletshop.springbootbackend.repository.CategoryRepository;
import com.bedtaletshop.springbootbackend.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class SubCategoryServiceImpl implements SubCategoryService{

    private final SubCategoryRepository subcategoryRepository;

    @Override
    public List<SubCategory> getSubCategoryAll() {
        List<SubCategory> rootCategoryList = subcategoryRepository.findAll();
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
    public SubCategory getSubCategoryById(Long id){
        Optional<SubCategory> subCategory = subcategoryRepository.findById(id);
        if (!subCategory.isPresent())
            throw new NotFoundException("Product not found with Id:" + id);

        return subCategory.get();
    }
}
