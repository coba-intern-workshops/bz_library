package com.commerzbank.library.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ExtendRentDto(
        @NotBlank(message = "book id can not be blank")
        String bookId,
        @NotBlank(message = "person id can not be blank")
        String personId) {
}
