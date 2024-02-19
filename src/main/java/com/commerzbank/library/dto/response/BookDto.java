package com.commerzbank.library.dto.response;

import com.commerzbank.library.dto.request.BookStatusDto;

import java.util.UUID;

public record BookDto(UUID id, String title, String author, BookStatusDto bookStatus) {
}
