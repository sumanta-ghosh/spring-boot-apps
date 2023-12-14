package com.ncoderslab.contentcalendar.controller;

import com.ncoderslab.contentcalendar.model.Content;
import com.ncoderslab.contentcalendar.repository.ContentCollectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentCollectionRepository contentCollectionRepository;

    public ContentController(ContentCollectionRepository contentCollectionRepository) {
        this.contentCollectionRepository = contentCollectionRepository;
    }

    @GetMapping("")
    public List<Content> findAll() {
        return contentCollectionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable("id") Integer id) {
        return contentCollectionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Content content) {
        contentCollectionRepository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id) {
        if (!contentCollectionRepository.existById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found..");
        }
        contentCollectionRepository.update(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        contentCollectionRepository.delete(id);
    }

}
