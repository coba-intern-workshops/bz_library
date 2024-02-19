package com.commerzbank.library.service;

import com.commerzbank.library.dto.request.BookCreateDto;
import com.commerzbank.library.dto.request.BookDeleteDto;
import com.commerzbank.library.dto.response.BookDto;
import com.commerzbank.library.exception.RecordNotFoundException;
import com.commerzbank.library.exception.RequestValidationException;
import com.commerzbank.library.mapper.Mapper;
import com.commerzbank.library.model.BookStatus;
import com.commerzbank.library.repository.BookRepositoryImpl;
import com.commerzbank.library.repository.RepositoryIfc;
import com.commerzbank.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    private final BookRepositoryImpl bookRepository;
    private final Mapper<BookDto, Book> bookMapper;
    private final Mapper<BookCreateDto, Book> bookCreateMapper;

    public BookService(BookRepositoryImpl bookRepository, Mapper<BookDto, Book> bookMapper, Mapper<BookCreateDto, Book> bookCreateMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.bookCreateMapper = bookCreateMapper;
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

    public BookDto saveBook(BookCreateDto bookCreateDto) {
        var result = bookRepository.save(bookCreateMapper.mapFromDto(bookCreateDto));
        return bookMapper.mapFromEntity(result);
    }

    public BookDto deleteBook(String id, BookDeleteDto bookDeleteDto) {
        if (bookDeleteDto.bookStatus() != BookStatus.DELETED) {
            throw new RequestValidationException("book status must be deleted");
        }
    final Book book =  bookRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RecordNotFoundException("Book not found"));
        book.setBookStatus(BookStatus.DELETED);
        bookRepository.save(book);
        return bookMapper.mapFromEntity(book);
    }

    public Book findByTitleAndStatus(String title, BookStatus bookStatus) {
        return bookRepository.findByTitleAndStatus(title, bookStatus).orElseThrow(() -> new RecordNotFoundException("Book not found"));
    }

    public Book findById(String id) {
        return bookRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RecordNotFoundException("Book not found"));
    }

    public BookDto findBookById(String id) {
        var result = bookRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RecordNotFoundException("Book not found"));
        return bookMapper.mapFromEntity(result);
    }
}
