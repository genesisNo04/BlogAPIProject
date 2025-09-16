package com.example.BloggingApiProject.Service.Impl;

import com.example.BloggingApiProject.Exception.ResourceNotFoundException;
import com.example.BloggingApiProject.Model.Tag;
import com.example.BloggingApiProject.Repository.TagsRepository;
import com.example.BloggingApiProject.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public Tag findTagByName(String name) {
        Tag tag = tagsRepository.findTagByName(name);

        if (tag == null) {
            throw new ResourceNotFoundException("No tag with name: " + name);
        }

        return tag;
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagsRepository.save(tag);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagsRepository.findAll();
    }

    @Override
    public boolean existByName(String name) {
        return tagsRepository.existsByName(name);
    }
}
