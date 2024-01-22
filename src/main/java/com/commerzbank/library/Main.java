package com.commerzbank.library;

import com.commerzbank.library.book.BookRepository;
import com.commerzbank.library.book.BookRepositoryImpl;
import com.commerzbank.library.book.BookService;
import com.commerzbank.library.book.SearchCriteria;
import com.commerzbank.library.model.Book;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        final BookRepository bookRepository = new BookRepositoryImpl();
        final BookService bookService = new BookService(bookRepository);
        final SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setAuthor("Martin");

        final List<Book> books = bookService.findBooks(searchCriteria);
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }
}