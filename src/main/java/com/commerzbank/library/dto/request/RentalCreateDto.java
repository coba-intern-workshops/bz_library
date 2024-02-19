package com.commerzbank.library.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record RentalCreateDto(
        @NotBlank(message = "person id can not be blank")
        UUID personId,
        @NotBlank(message = "title can not be blank")
        String title) {
}
