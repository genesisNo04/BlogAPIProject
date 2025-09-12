package com.example.BloggingApiProject.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Blog")
@Setter
@Getter
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "blog_title")
    private String title;

    @Column(name = "blog_content")
    private String content;

    @JoinTable(
        name = "blog_tags",
        joinColumns = @JoinColumn(name = "blogs_id"),
        inverseJoinColumns = @JoinColumn(name = "tags_id")
    )
    private List<String> tags;

    @JsonFormat(pattern = "MMMM dd, yyyy")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "MMMM dd, yyyy")
    private LocalDateTime modifiedAt;

    public Blog(String title, String content, List<String> tags, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
