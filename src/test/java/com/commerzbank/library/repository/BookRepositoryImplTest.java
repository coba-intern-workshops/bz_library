package com.commerzbank.library.repository;

import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

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
        final Long bookListSize = bookRepository.count();
        final Book book = new Book("title", "author", BookStatus.AVAILABLE);

        //when
        bookRepository.save(book);

        //then
        assertEquals(bookListSize + 1, bookRepository.count());
    }

    @Test
    void addBook_whenDataValid_thenBookObject() {
        //given
        final Book book = new Book("title", "author", BookStatus.AVAILABLE);

        //when
        final Book savedBook = bookRepository.save(book);

        //then
        assertEquals(book.getTitle(), savedBook.getTitle());
        assertEquals(book.getAuthor(), savedBook.getAuthor());
        assertEquals(book.getBookStatus(), savedBook.getBookStatus());
    }

    @Test
    void addBook_whenDataNotValid_thenThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> bookRepository.save(null));
    }

    @Test
    void deleteBook_whenBookExists_thenDeleteBook() {
        final Book book = new Book(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c55"), "Clean Code", "Robert Martin", BookStatus.AVAILABLE);
        final Long bookListSize = bookRepository.count();

        bookRepository.delete(book);
        assertEquals(bookListSize - 1, bookRepository.count());
    }

    @Test
    void deleteBook_whenBookDoesNotExist_thenDoNothing() {
        final Book book = new Book(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c54"), "Clean Code", "Robert Martin", BookStatus.AVAILABLE);
        final Long bookListSize = bookRepository.count();

        bookRepository.delete(book);
        assertEquals(bookListSize, bookRepository.count());
    }

    @Test
    void deleteBookById_whenBookExists_thenDeleteBook() {
        final Long bookListSize = bookRepository.count();

        bookRepository.deleteById(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c56"));
        assertEquals(bookListSize - 1, bookRepository.count());
    }

    @Test
    void deleteBookById_whenBookDoesNotExist_thenDoNothing() {
        final Long bookListSize = bookRepository.count();

        bookRepository.deleteById(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c52"));
        assertEquals(bookListSize, bookRepository.count());
    }

    @Test
    void findBook_whenBookExists_thenReturnBook() {
        assertTrue(bookRepository.findById(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c57")).isPresent());
    }

    @Test
    void findBookById_whenBookDoesNotExist_thenReturnEmptyOptional() {
        assertTrue(bookRepository.findById(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c53")).isEmpty());
    }

    @Test
    void updateBookEntity_whenAllDataValid_thenSaveEntity() {
        Book book = bookRepository.findById(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c58")).get();

        assertEquals(BookStatus.DELETED, book.getBookStatus());

        book.setBookStatus(BookStatus.LOST);
        bookRepository.save(book);

        book = bookRepository.findById(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c58")).get();

        assertEquals(BookStatus.LOST, book.getBookStatus());
    }
}