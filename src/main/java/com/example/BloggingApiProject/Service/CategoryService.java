package com.example.BloggingApiProject.Service;

import com.example.BloggingApiProject.Model.Category;

import java.util.List;

public interface CategoryService {

    Category findCategoryByName(String name);

    boolean existsByName(String name);

    Category saveCategory(Category category);

    List<Category> getAllCategory();
}
