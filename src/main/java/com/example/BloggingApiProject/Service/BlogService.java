package com.example.BloggingApiProject.Service;

import com.example.BloggingApiProject.DTO.BlogDTO;
import com.example.BloggingApiProject.Model.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlog();

    Blog findBlogById(Long id);

    Blog saveBlog(BlogDTO blogDTO);
}
