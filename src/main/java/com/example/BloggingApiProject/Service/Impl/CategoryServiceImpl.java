package com.example.BloggingApiProject.Service.Impl;

import com.example.BloggingApiProject.Exception.AlreadyExistException;
import com.example.BloggingApiProject.Exception.ResourceNotFoundException;
import com.example.BloggingApiProject.Model.Category;
import com.example.BloggingApiProject.Repository.CategoryRespository;
import com.example.BloggingApiProject.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRespository categoryRespository;

    @Override
    public Category findCategoryByName(String name) {
        Category category = categoryRespository.findCategoryByName(name);

        if (category == null) {
            throw new ResourceNotFoundException("No category with name: " + name);
        }

        return category;
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRespository.existsByName(name);
    }

    @Override
    public Category saveCategory(Category category) {
        if (existsByName(category.getName())) {
            throw new AlreadyExistException("Category already exist with name: " + category.getName());
        }

        return categoryRespository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRespository.findAll();
    }
}
