package com.commerzbank.library.service;

import com.commerzbank.library.dto.request.BookDto;
import com.commerzbank.library.dto.request.RentalDto;
import com.commerzbank.library.mapper.BookMapper;
import com.commerzbank.library.mapper.Mapper;
import com.commerzbank.library.model.Rental;
import com.commerzbank.library.repository.RepositoryIfc;
import com.commerzbank.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final RepositoryIfc<Book> bookRepository;
    private final Mapper<BookDto, Book> bookMapper;

    public BookService(RepositoryIfc<Book> bookRepository, Mapper<BookDto, Book> bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<Book> findBooks(BookSearchCriteria bookSearchCriteria) {
      return   bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().contains(bookSearchCriteria.getAuthor()))
                .toList();
    }

    public List<BookDto> findAllBooks( ) {

        List<Book> bookList =  bookRepository.findAll();

        return bookList.stream().map(bookMapper::mapFromEntity).toList();
    }

    public BookDto saveBook(BookDto bookDto) {
        var result = bookRepository.save(bookMapper.mapFromDto(bookDto));
        return bookMapper.mapFromEntity(result);
    }
}
