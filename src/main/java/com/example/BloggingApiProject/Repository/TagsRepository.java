package com.example.BloggingApiProject.Repository;

import com.example.BloggingApiProject.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<Tag, Long> {

    public Tag findTagByName(String name);
}
