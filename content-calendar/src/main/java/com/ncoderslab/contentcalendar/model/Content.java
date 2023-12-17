package com.ncoderslab.contentcalendar.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name="CONTENT")
public record Content(
        @Id
        Integer id,
        @NotEmpty
        String title,
        String desc,
        Status status,
        Type ContentType,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url
) {
}
