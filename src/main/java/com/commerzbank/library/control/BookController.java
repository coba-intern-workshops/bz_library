package com.commerzbank.library.control;

import com.commerzbank.library.dto.request.BookCreateDto;
import com.commerzbank.library.dto.request.BookDeleteDto;
import com.commerzbank.library.dto.response.BookDto;
import com.commerzbank.library.service.BookService;
import jakarta.validation.Valid;
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
    ResponseEntity<BookDto> addBook(@Valid @RequestBody BookCreateDto bookCreateDto) {
        return ResponseEntity.ok(bookService.saveBook(bookCreateDto));
    }

    @PatchMapping("/{id}")
   public ResponseEntity<BookDto> deleteBook(@PathVariable String id, @RequestBody BookDeleteDto bookDeleteDto) {
        return ResponseEntity.ok(bookService.deleteBook(id,bookDeleteDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findBook(@PathVariable String id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }
}
