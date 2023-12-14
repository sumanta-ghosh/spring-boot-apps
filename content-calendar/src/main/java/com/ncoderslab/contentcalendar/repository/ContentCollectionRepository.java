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

    public Optional<Content> findById(Integer id) {
        return content.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public void save(Content content) {
        this.content.add(content);
    }

    @PostConstruct
    private void init() {
        Content content = new Content(1, "My First Blog post", "Description", Status.COMPLETED, Type.ARTICLE, LocalDateTime.now(), null, "");
        this.content.add(content);
    }

    public boolean existById(Integer id) {
        return content.stream().filter(c -> c.id().equals(id)).count() == 1;
    }

    public void update(Content content) {
        this.content.removeIf(c -> c.id().equals(content.id()));
        this.content.add(content);
    }

    public void delete(Integer id) {
        this.content.removeIf(c -> c.id().equals(id));
    }
}
