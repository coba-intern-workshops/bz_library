package com.commerzbank.library.dto.request;

import jakarta.validation.constraints.NotBlank;

public record BookCreateDto(
        @NotBlank(message = "title can not be blank")
        String title,
        @NotBlank(message = "author can not be blank")

        String author,
        @NotBlank(message = "status can not be blank")

        BookStatusDto bookStatusDto) {
}
