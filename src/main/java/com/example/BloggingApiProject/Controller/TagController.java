package com.example.BloggingApiProject.Controller;

import com.example.BloggingApiProject.DTO.TagDTO;
import com.example.BloggingApiProject.Exception.AlreadyExistException;
import com.example.BloggingApiProject.Model.Tag;
import com.example.BloggingApiProject.Service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/user/tag/{name}")
    public ResponseEntity<Tag> getTagByName(@PathVariable String name) {
        Tag tag = tagService.findTagByName(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(tag);
    }

    @PostMapping("/admin/tag")
    public ResponseEntity<Tag> saveTag(@Valid  @RequestBody TagDTO tagDTO) {

        if (tagService.existByName(tagDTO.getName())) {
            throw new AlreadyExistException("Tag already exist with name: " + tagDTO.getName());
        }
        Tag tag = new Tag();
        tag.setName(tagDTO.getName());

        Tag saveTag = tagService.saveTag(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveTag);
    }

    @GetMapping("/user/tag")
    public ResponseEntity<List<Tag>> getAllTag() {
        List<Tag> tags = tagService.getAllTag();
        return ResponseEntity.status(HttpStatus.FOUND).body(tags);
    }
}
