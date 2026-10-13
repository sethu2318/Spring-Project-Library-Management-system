package com.example.library.service;

import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    // This Map is our "database". Data is lost when the app stops.
    private final Map<Long, Book> books = new HashMap<>();

    // Used to give each new book the next id: 1, 2, 3...
    private long nextId = 1;

    // Feature 1: Add a book
    public Book addBook(Book book) {
        book.setBookId(nextId);
        books.put(nextId, book);
        nextId++;
        return book;
    }

    // Feature 2: Search a book by id
    public Book getBookById(Long id) {
        Book book = books.get(id);
        if (book == null) {
            throw new BookNotFoundException("Book not found with id " + id);
        }
        return book;
    }

    // Feature 3: Update availability
    public Book updateAvailability(Long id, boolean available) {
        Book book = getBookById(id);   // throws error if not found
        book.setAvailable(available);
        return book;
    }

    // Feature 4: Delete a book
    public void deleteBook(Long id) {
        getBookById(id);               // throws error if not found
        books.remove(id);
    }

    // Bonus: list all books
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }
}
