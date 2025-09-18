package com.example.BloggingApiProject.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class BlogDTO {

    @NotBlank(message = "Blog title is required")
    private String blogTitle;

    @NotBlank(message = "Blog content is required")
    private String blogContent;

    @NotEmpty(message = "Tags cannot be empty")
    private List<String> tags;

    @NotEmpty(message = "Category cannot be empty")
    private List<String> categories;

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
