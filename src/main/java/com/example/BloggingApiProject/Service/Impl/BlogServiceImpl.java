package com.example.BloggingApiProject.Service.Impl;

import com.example.BloggingApiProject.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl {

    @Autowired
    private BlogRepository blogRepository;


}
