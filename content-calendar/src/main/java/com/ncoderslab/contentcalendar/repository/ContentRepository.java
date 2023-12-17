package com.ncoderslab.contentcalendar.repository;

import com.ncoderslab.contentcalendar.model.Content;
import org.springframework.data.repository.ListCrudRepository;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {
}
