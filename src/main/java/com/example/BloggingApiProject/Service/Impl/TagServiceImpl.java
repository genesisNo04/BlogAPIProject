package com.example.BloggingApiProject.Service.Impl;

import com.example.BloggingApiProject.Model.Tag;
import com.example.BloggingApiProject.Repository.TagsRepository;
import com.example.BloggingApiProject.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public Tag findTagByName(String name) {
        Tag tag = tagsRepository.findTagByName(name);

        if (tag == null) {
            throw new RuntimeException("No tag with name: " + name);
        }

        return tag;
    }
}
