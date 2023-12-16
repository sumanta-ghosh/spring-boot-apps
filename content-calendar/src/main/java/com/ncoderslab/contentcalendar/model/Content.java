package com.ncoderslab.contentcalendar.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record Content(
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
