package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Nonnull;  // use Jakarta standard

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    // Custom query methods
    @Nonnull
    List<Book> findByPublicationYear(int publicationYear);
    
    // Override methods from MongoRepository with @Nonnull annotations
    @Override
    @Nonnull
    <S extends Book> S save(@Nonnull S entity);
    
    @Override
    @Nonnull
    Optional<Book> findById(@Nonnull String id);
    
    @Override
    @Nonnull
    List<Book> findAll();
}
