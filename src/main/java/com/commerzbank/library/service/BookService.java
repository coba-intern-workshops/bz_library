package com.commerzbank.library.service;

import com.commerzbank.library.repository.Repository;
import com.commerzbank.library.model.Book;

import java.util.List;

public class BookService {
    private final Repository<Book> bookRepository;

    public BookService(Repository<Book> bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> findBooks(SearchCriteria searchCriteria) {
      return   bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().contains(searchCriteria.getAuthor()))
                .toList();
    }
}
