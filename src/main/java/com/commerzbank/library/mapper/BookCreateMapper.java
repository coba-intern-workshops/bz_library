package com.commerzbank.library.mapper;

import com.commerzbank.library.dto.request.BookCreateDto;
import com.commerzbank.library.dto.request.BookStatusDto;
import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookCreateMapper extends Mapper<BookCreateDto, Book> {
    public BookCreateMapper() {
        super(BookCreateMapper::mapToEntity, BookCreateMapper::mapToDto);
    }

    private static BookCreateDto mapToDto(Book book) {
        return new BookCreateDto(book.getTitle(),book.getAuthor(), BookStatusDto.valueOf(book.getBookStatus().toString()));
    }

    private static Book mapToEntity(BookCreateDto bookCreateDto) {
        return new Book(UUID.randomUUID(), bookCreateDto.title(), bookCreateDto.author(), BookStatus.valueOf(bookCreateDto.bookStatusDto().toString()));
    }
}
