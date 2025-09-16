package com.example.BloggingApiProject.Repository;

import com.example.BloggingApiProject.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRespository extends JpaRepository<Category, Long> {

    Category findCategoryByName(String name);

    boolean existsByName(String name);
}
