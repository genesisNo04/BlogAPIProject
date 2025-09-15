package com.example.BloggingApiProject.Controller;

import com.example.BloggingApiProject.DTO.BlogDTO;
import com.example.BloggingApiProject.Model.Blog;
import com.example.BloggingApiProject.Model.Tag;
import com.example.BloggingApiProject.Service.BlogService;
import com.example.BloggingApiProject.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/v1/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlog() {
        List<Blog> blogs = blogService.getAllBlog();
        return ResponseEntity.ok(blogs);
    }

    @PostMapping
    public ResponseEntity<Blog> saveBlog(@RequestBody BlogDTO blogDTO) {
        Blog blog = new Blog();
        blog.setContent(blogDTO.getBlogContent());
        blog.setTitle(blogDTO.getBlogTitle());
        blog.setCreatedAt(LocalDateTime.now());
        blog.setModifiedAt(LocalDateTime.now());

        List<Tag> tags = blogDTO.getTags().stream().map(name -> {
            if (tagService.existByName(name)) {
                return tagService.findTagByName(name);
            } else {
                return tagService.saveTag(new Tag(name));
            }
        }).toList();

        blog.setTags(tags);
        Blog savedBlog = blogService.saveBlog(blog);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBlog);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Blog blog = blogService.findBlogById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(blog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteblogById(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.ok("Blog is deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody BlogDTO blogDTO) {
        Blog blog = blogService.findBlogById(id);

        blog.setModifiedAt(LocalDateTime.now());
        blog.setTitle(blogDTO.getBlogTitle());
        blog.setContent(blogDTO.getBlogContent());
        List<Tag> tags = blogDTO.getTags().stream().map(
                name -> {
                    if (tagService.existByName(name)) {
                        return tagService.findTagByName(name);
                    } else {
                        return tagService.saveTag(new Tag(name));
                    }
                }).toList();
        blog.setTags(tags);

        Blog savedblog = blogService.updateBlog(blog);
        return ResponseEntity.ok(savedblog);
    }
}
