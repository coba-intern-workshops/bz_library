package com.commerzbank.library.control;

import com.commerzbank.library.dto.request.BookDto;
import com.commerzbank.library.dto.request.RentalDto;
import com.commerzbank.library.model.Book;
import com.commerzbank.library.service.BookService;
import com.commerzbank.library.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    ResponseEntity<List<BookDto>> findAllBooks() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @PostMapping
    ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.saveBook(bookDto));
    }
}
