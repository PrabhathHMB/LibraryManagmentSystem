package com.example.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private String shelfLocation;

}

