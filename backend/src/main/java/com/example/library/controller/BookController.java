package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Nonnull;   // ✅ Use Jakarta standard

import java.util.List;
import java.util.Optional;


import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/books")

public class BookController {

    @Autowired
    private BookService bookService;

    // ➕ Add new book
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Nonnull
    public ResponseEntity<Book> addBook(@RequestBody @Nonnull Book book) {
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    // 📚 Get all books
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Nonnull
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // 🔍 Get book by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Nonnull
    public ResponseEntity<Book> getBookById(@PathVariable @Nonnull String id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(value -> ResponseEntity.ok().body(value))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✏️ Update book details
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Nonnull
    public ResponseEntity<Book> updateBook(@PathVariable @Nonnull String id,
                                           @RequestBody @Nonnull Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        }
        return ResponseEntity.notFound().build();
    }

    // ❌ Delete book by ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Nonnull
    public ResponseEntity<Void> deleteBookById(@PathVariable @Nonnull String id) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            bookService.deleteBookById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // 📅 Find books by publication year
    @GetMapping("/year/{year}")
    @Nonnull
    public ResponseEntity<List<Book>> findBooksByPublicationYear(@PathVariable int year) {
        List<Book> books = bookService.findBooksByPublicationYear(year);
        return ResponseEntity.ok(books);
    }

    // 🎭 Get genre by book ID
    @GetMapping("/{id}/genre")
    @Nonnull
    public ResponseEntity<String> getGenreByBookId(@PathVariable @Nonnull String id) {
        String genre = bookService.getGenreByBookId(id);
        if (genre != null) {
            return ResponseEntity.ok(genre);
        }
        return ResponseEntity.notFound().build();
    }

    // 🗑️ Delete all books published in a specific year
    @DeleteMapping("/year/{year}")
    @Nonnull
    public ResponseEntity<Void> deleteBooksByPublicationYear(@PathVariable int year) {
        bookService.deleteBooksByPublicationYear(year);
        return ResponseEntity.noContent().build();
    }
}

