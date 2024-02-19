package com.commerzbank.library.repository;

import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BookRepositoryImpl implements RepositoryIfc<Book> {
    static final List<Book> bookList = new ArrayList<>();

    static {
        bookList.add(new Book(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c55"), "Clean Code", "Robert Martin", BookStatus.AVAILABLE));
        bookList.add(new Book(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c56"),"Clean Code", "Robert Martin", BookStatus.AVAILABLE));
        bookList.add(new Book(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c57"),"Java Persistence with Spring Data and Hibernate", "Catalin Tudose", BookStatus.AVAILABLE));
        bookList.add(new Book(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c58"),"API Security in Action", "Neil Madden", BookStatus.DELETED));
        bookList.add(new Book(UUID.fromString("345eac60-fa7a-406d-95bd-9555f3608c59"),"Programowanie Aplikacji Bazodanowych w Hibernate", "Christian Bauer", BookStatus.LOST));
    }

    @Override
    public List<Book> findAll() {
        return bookList;
    }

    @Override
    public Book save(Book book) {
        if (book == null) {
            throw new IllegalArgumentException();
        }
        bookList.add(book);
        return book;
    }

    @Override
    public Optional<Book> findById(UUID id) {
        return bookList.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(Book object) {
        bookList.remove(object);
    }

    @Override
    public void deleteById(UUID id) {
        findById(id).ifPresent(bookList::remove);
    }

    @Override
    public Long count() {
        return (long) bookList.size();
    }

    public Optional<Book> findByTitleAndStatus(String title, BookStatus bookStatus) {
        return bookList.stream().filter(book -> book.getTitle().equals(title) && book.getBookStatus() == bookStatus).findFirst();
    }
}
