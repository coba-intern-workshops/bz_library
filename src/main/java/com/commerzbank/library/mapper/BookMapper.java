package com.commerzbank.library.mapper;

import com.commerzbank.library.dto.request.BookDto;
import com.commerzbank.library.dto.request.BookStatusDto;
import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import org.springframework.stereotype.Component;

@Component
public class BookMapper extends Mapper<BookDto, Book> {
    public BookMapper() {
        super(BookMapper::mapToEntity, BookMapper::mapToDto);
    }

    private static BookDto mapToDto(Book book) {
        return new BookDto(book.getId(),book.getTitle(),book.getAuthor(), BookStatusDto.valueOf(book.getBookStatus().toString()));
    }

    private static Book mapToEntity(BookDto bookDto) {
        return new Book(bookDto.id(),bookDto.title(),bookDto.author(), BookStatus.valueOf(bookDto.bookStatus().toString()));
    }
}
