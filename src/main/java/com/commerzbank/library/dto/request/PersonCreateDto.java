package com.commerzbank.library.dto.request;

import com.commerzbank.library.model.UserType;
import jakarta.validation.constraints.NotBlank;

public record PersonCreateDto(
        @NotBlank(message = "first name can not be blank")
        String firstName,
        @NotBlank(message = "last name can not be blank")
        String lastName,
        @NotBlank(message = "user type can not be blank")
        UserType userType) {
}