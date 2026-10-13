package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // POST /api/books  -> add a book
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        Book saved = bookService.addBook(book);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // GET /api/books/1  -> search by id
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // PATCH /api/books/1/availability?available=false  -> update availability
    @PatchMapping("/{id}/availability")
    public Book updateAvailability(@PathVariable Long id, @RequestParam boolean available) {
        return bookService.updateAvailability(id, available);
    }

    // DELETE /api/books/1  -> delete a book
    @DeleteMapping("/{id}")
    public Map<String, String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return Map.of("message", "Book deleted successfully");
    }

    // GET /api/books  -> list all books (bonus)
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
