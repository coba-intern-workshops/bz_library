package com.commerzbank.library.dto.request;

import com.commerzbank.library.model.UserType;

import java.util.UUID;

public record PersonDto(UUID id, String firstName, String lastName,UserType userType) {
}