package com.commerzbank.library.model;

import java.util.Objects;
import java.util.UUID;

public class Book {
    private UUID id;
    private String title;
    private String author;
    private BookStatus bookStatus;

    public Book(String title, String author, BookStatus bookStatus) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.bookStatus = bookStatus;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && bookStatus == book.bookStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, bookStatus);
    }
}
