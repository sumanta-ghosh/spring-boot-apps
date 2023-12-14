package com.ncoderslab.contentcalendar.repository;

import com.ncoderslab.contentcalendar.model.Content;
import com.ncoderslab.contentcalendar.model.Status;
import com.ncoderslab.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {
    private final List<Content> content = new ArrayList<>();

    public ContentCollectionRepository() {
    }

    public List<Content> findAll() {
        return content;
    }

    public Optional<Content> findBYId(Integer id) {
        return content.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    @PostConstruct
    private void init() {
        Content c = new Content(1, "My First Blog post", "Description", Status.COMPLETED, Type.ARTICLE, LocalDateTime.now(), null, "");
        content.add(c);
    }
}
