package com.example.BloggingApiProject.Controller;

import com.example.BloggingApiProject.Exception.AlreadyExistException;
import com.example.BloggingApiProject.Model.Category;
import com.example.BloggingApiProject.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody String name) {
        if (categoryService.existsByName(name)) {
            throw new AlreadyExistException("Category " + name + " already exists.");
        }
        Category savedCategory = categoryService.saveCategory(new Category(name));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        return ResponseEntity.status(HttpStatus.FOUND).body(categories);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Category> getAllCategory(@PathVariable String name) {
        Category category = categoryService.findCategoryByName(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(category);
    }


}
