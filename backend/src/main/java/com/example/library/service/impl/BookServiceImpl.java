package com.example.library.service.impl;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Nonnull
    public Book addBook(@Nonnull Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Nonnull
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books != null ? books : List.of();
    }

    @Override
    @Nonnull
    public Optional<Book> getBookById(@Nonnull String id) {
        return bookRepository.findById(id);
    }

    @Nullable
    @Override
    public Book updateBook(@Nonnull String id, @Nonnull Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if (bookDetails.getTitle() != null) book.setTitle(bookDetails.getTitle());
            if (bookDetails.getAuthor() != null) book.setAuthor(bookDetails.getAuthor());
            if (bookDetails.getGenre() != null) book.setGenre(bookDetails.getGenre());
            book.setPublicationYear(bookDetails.getPublicationYear());
            if (bookDetails.getShelfLocation() != null) book.setShelfLocation(bookDetails.getShelfLocation());

            return bookRepository.save(book);
        }
        return null;
    }

    @Override
    public void deleteBookById(@Nonnull String id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Nonnull
    public List<Book> findBooksByPublicationYear(int year) {
        List<Book> books = bookRepository.findByPublicationYear(year);
        return books != null ? books : List.of();
    }

    @Nullable
    @Override
    public String getGenreByBookId(@Nonnull String id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.map(Book::getGenre).orElse(null);
    }

    @Override
    public void deleteBooksByPublicationYear(int year) {
        List<Book> books = bookRepository.findByPublicationYear(year);
        if (books != null && !books.isEmpty()) {
            bookRepository.deleteAll(books);
        }
    }
}

