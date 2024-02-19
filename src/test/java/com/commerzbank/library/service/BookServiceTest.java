package com.commerzbank.library.service;

import com.commerzbank.library.dto.request.BookCreateDto;
import com.commerzbank.library.dto.request.BookDeleteDto;
import com.commerzbank.library.dto.response.BookDto;
import com.commerzbank.library.dto.request.BookStatusDto;
import com.commerzbank.library.mapper.BookCreateMapper;
import com.commerzbank.library.mapper.BookMapper;
import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import com.commerzbank.library.repository.BookRepositoryImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookServiceTest implements WithAssertions {

    private BookService bookService;
    private BookRepositoryImpl bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepositoryImpl();
        bookService = new BookService(bookRepository, new BookMapper(), new BookCreateMapper());
    }

    @Test
    void shouldDeleteBook() {
      final BookDto bookDto = bookService.saveBook(new BookCreateDto("title","author", BookStatusDto.AVAILABLE));

        bookService.deleteBook(bookDto.id().toString(),new BookDeleteDto(BookStatus.DELETED));

        final Book book = bookRepository.findById(bookDto.id()).get();

        assertThat(book.getBookStatus()).isEqualTo(BookStatus.DELETED);
    }

    @Test
    void shouldThrowException_WhenBookDoesNotExist() {
    }

    @Test
    void shouldThrowException_WhenStatusDifferentThanDeleted() {
    }
}
