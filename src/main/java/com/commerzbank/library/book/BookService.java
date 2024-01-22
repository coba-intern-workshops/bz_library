package com.commerzbank.library.book;

import com.commerzbank.library.model.Book;

import java.util.List;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> findBooks(SearchCriteria searchCriteria) {
      return   bookRepository.getAll().stream()
                .filter(book -> book.getAuthor().contains(searchCriteria.getAuthor()))
                .toList();
    }
}
