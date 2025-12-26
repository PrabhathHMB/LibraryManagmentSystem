package com.example.library.service;

import com.example.library.model.Book;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    @Nonnull
    Book addBook(@Nonnull Book book);

    @Nonnull
    List<Book> getAllBooks();

    @Nonnull
    Optional<Book> getBookById(@Nonnull String id);

    @Nullable
    Book updateBook(@Nonnull String id, @Nonnull Book bookDetails);

    void deleteBookById(@Nonnull String id);

    @Nonnull
    List<Book> findBooksByPublicationYear(int year);

    @Nullable
    String getGenreByBookId(@Nonnull String id);

    void deleteBooksByPublicationYear(int year);
}
