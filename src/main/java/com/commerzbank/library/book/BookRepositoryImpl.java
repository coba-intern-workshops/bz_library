package com.commerzbank.library.book;

import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;

import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    static final List<Book> bookList = new ArrayList<>();

    static {
        bookList.add(new Book("Clean Code", "Robert Martin", BookStatus.AVAILABLE));
        bookList.add(new Book("Clean Code", "Robert Martin", BookStatus.AVAILABLE));
        bookList.add(new Book("Java Persistence with Spring Data and Hibernate", "Catalin Tudose", BookStatus.AVAILABLE));
        bookList.add(new Book("API Security in Action", "Neil Madden", BookStatus.DELETED));
        bookList.add(new Book("Programowanie Aplikacji Bazodanowych w Hibernate", "Christian Bauer", BookStatus.LOST));
    }

    @Override
    public List<Book> getAll() {
        return bookList;
    }
}
