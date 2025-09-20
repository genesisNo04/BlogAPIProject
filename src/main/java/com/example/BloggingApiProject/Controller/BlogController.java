package com.example.BloggingApiProject.Controller;

import com.example.BloggingApiProject.DTO.BlogDTO;
import com.example.BloggingApiProject.Model.Blog;
import com.example.BloggingApiProject.Model.Category;
import com.example.BloggingApiProject.Model.Tag;
import com.example.BloggingApiProject.Service.BlogService;
import com.example.BloggingApiProject.Service.CategoryService;
import com.example.BloggingApiProject.Service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/user/blog")
    public ResponseEntity<List<Blog>> getAllBlog(@RequestParam(required = false) String term) {
        List<Blog> blogs = blogService.getAllBlog();

        if (term != null && !term.isBlank()) {
            blogs = blogs.stream().filter(blog -> blog.getCategories() != null
                    && blog.getCategories().stream().anyMatch(cat -> cat != null && cat.getName().equals(term))
                    || blog.getTitle().contains(term) || blog.getContent().contains(term))
                    .collect(Collectors.toList());
        }
        return ResponseEntity.ok(blogs);
    }

    @PostMapping("/admin/blog")
    public ResponseEntity<Blog> saveBlog(@Valid @RequestBody BlogDTO blogDTO) {
        List<Tag> tags = blogDTO.getTags().stream().map(name -> {
            if (tagService.existByName(name)) {
                return tagService.findTagByName(name);
            } else {
                return tagService.saveTag(new Tag(name));
            }
        }).toList();

        List<Category> categories = blogDTO.getCategories().stream().map(name -> {
            if (categoryService.existsByName(name)) {
                return categoryService.findCategoryByName(name);
            } else {
                return categoryService.saveCategory(new Category(name));
            }
        }).toList();

        Blog blog = new Blog();
        blog.setTags(tags);
        blog.setContent(blogDTO.getBlogContent());
        blog.setTitle(blogDTO.getBlogTitle());
        blog.setCreatedAt(LocalDateTime.now());
        blog.setModifiedAt(LocalDateTime.now());
        blog.setCategories(categories);

        Blog savedBlog = blogService.saveBlog(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBlog);
    }

    @GetMapping("/user/blog/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Blog blog = blogService.findBlogById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(blog);
    }

    @DeleteMapping("/admin/blog/{id}")
    public ResponseEntity<String> deleteBlogById(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.ok("Blog is deleted");
    }

    @PutMapping("/admin/blog/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @Valid @RequestBody BlogDTO blogDTO) {
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
                }).collect(Collectors.toList());
        blog.setTags(tags);

        Blog savedblog = blogService.updateBlog(blog);
        return ResponseEntity.ok(savedblog);
    }

    @PatchMapping("/admin/blog/{id}")
    public ResponseEntity<Blog> partiallyUpdateBlog(@PathVariable Long id, @RequestBody BlogDTO blogDTO) {
        Blog blog = blogService.findBlogById(id);

        blog.setModifiedAt(LocalDateTime.now());

        if (blogDTO.getBlogTitle() != null && !blogDTO.getBlogTitle().isEmpty()) {
            blog.setTitle(blogDTO.getBlogTitle());
        }

        if (blogDTO.getBlogContent() != null && !blogDTO.getBlogContent().isEmpty()) {
            blog.setContent(blogDTO.getBlogContent());
        }

        if (blogDTO.getTags() != null && !blogDTO.getTags().isEmpty()) {
            List<Tag> tags = blogDTO.getTags().stream().map(
                    name -> {
                        if (tagService.existByName(name)) {
                            return tagService.findTagByName(name);
                        } else {
                            return tagService.saveTag(new Tag(name));
                        }
                    }).collect(Collectors.toList());
            blog.setTags(tags);
        }

        if (blogDTO.getCategories() != null && !blogDTO.getCategories().isEmpty()) {
            List<Category> categories = blogDTO.getCategories().stream().map(
                    name -> {
                        if (categoryService.existsByName(name)) {
                            return categoryService.findCategoryByName(name);
                        } else {
                            return categoryService.saveCategory(new Category(name));
                        }
                    }).collect(Collectors.toList());
            blog.setCategories(categories);
        }

        Blog savedblog = blogService.updateBlog(blog);
        return ResponseEntity.ok(savedblog);
    }
}
