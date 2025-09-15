package com.example.BloggingApiProject.Service;

import com.example.BloggingApiProject.Model.Tag;

import java.util.List;

public interface TagService {

    Tag findTagByName(String name);

    Tag saveTag(Tag tag);

    List<Tag> getAllTag();

    boolean existByName(String name);
}
