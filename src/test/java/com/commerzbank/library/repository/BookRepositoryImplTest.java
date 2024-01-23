package com.commerzbank.library.repository;

import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BookRepositoryImplTest {

    private BookRepositoryImpl bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepositoryImpl();
    }

    @Test
    void getAllBooks_whenListIsNotEmpty_thenReturnBookList() {
        assertFalse(bookRepository.findAll().isEmpty());
    }

    @Test
    void addBook_whenWhenDataValid_thenBookListSizeShouldBeIncrementend() {
        //given
        final int bookListSize = bookRepository.findAll().size();
        final Book book = new Book("title","author", BookStatus.AVAILABLE);

        //when
        bookRepository.save(book);

        //then
        assertEquals(bookListSize + 1, bookRepository.findAll().size());
    }

    @Test
    void addBook_whenWhenDataValid_thenBookObject() {
        //given
        final Book book = new Book("title","author", BookStatus.AVAILABLE);

        //when
        final Book savedBook = bookRepository.save(book);

        //then
        assertEquals(book.getTitle(),savedBook.getTitle());
        assertEquals(book.getAuthor(),savedBook.getAuthor());
        assertEquals(book.getBookStatus(),savedBook.getBookStatus());
    }

    @Test
    void addBook_whenDataNotValid_thenThrowException() {
    assertThrows(IllegalArgumentException.class,
            () -> bookRepository.save(null));
    }
}