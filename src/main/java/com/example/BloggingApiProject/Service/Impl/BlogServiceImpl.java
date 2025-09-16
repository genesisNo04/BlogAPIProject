package com.example.BloggingApiProject.Service.Impl;

import com.example.BloggingApiProject.DTO.BlogDTO;
import com.example.BloggingApiProject.Exception.ResourceNotFoundException;
import com.example.BloggingApiProject.Model.Blog;
import com.example.BloggingApiProject.Repository.BlogRepository;
import com.example.BloggingApiProject.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findBlogById(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog with id: " + id + " is not exist"));
        return blog;
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog is not found with id: " + id));
        blogRepository.delete(blog);
    }

    @Override
    public Blog updateBlog(Blog blog) {
        return blogRepository.save(blog);
    }
}
