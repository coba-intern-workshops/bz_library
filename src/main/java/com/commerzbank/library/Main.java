package com.commerzbank.library;

import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import com.commerzbank.library.repository.BookRepositoryImpl;
import com.commerzbank.library.repository.Repository;
import com.commerzbank.library.service.BookService;
import com.commerzbank.library.service.SearchCriteria;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        final Repository repository = new BookRepositoryImpl();
        final BookService bookService = new BookService(repository);
        final SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setAuthor("Martin");

        final List<Book> books = bookService.findBooks(searchCriteria);
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }


}