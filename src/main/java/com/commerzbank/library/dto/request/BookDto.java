package com.commerzbank.library.dto.request;

import java.util.UUID;

public record BookDto(UUID id, String title, String author, BookStatusDto bookStatus) {
}