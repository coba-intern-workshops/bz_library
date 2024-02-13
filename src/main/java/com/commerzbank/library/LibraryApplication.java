package com.commerzbank.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {



    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
        //        final Repository repository = new BookRepositoryImpl();
//        final BookService bookService = new BookService(repository);
//        final BookSearchCriteria bookSearchCriteria = new BookSearchCriteria();
//        bookSearchCriteria.setAuthor("Martin");
//
//        final List<Book> books = bookService.findBooks(bookSearchCriteria);
//        for (Book book : books) {
//            System.out.println(book.getTitle());
//        }
    }
}