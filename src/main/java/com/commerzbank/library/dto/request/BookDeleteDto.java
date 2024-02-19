package com.commerzbank.library.dto.request;

import com.commerzbank.library.model.BookStatus;
import jakarta.validation.constraints.NotBlank;

public record BookDeleteDto(
        @NotBlank(message = "book status can not be blank")
        BookStatus bookStatus) {
}
