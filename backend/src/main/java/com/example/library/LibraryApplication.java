package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Collections;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        // Force the MongoDB URI programmatically
        String mongoUri = "mongodb+srv://bhanukaprabhath28_db_user:fSlhjxA2JwwXgYVC@labcluster.qindeoa.mongodb.net/library?retryWrites=true&w=majority";
        
        System.setProperty("spring.data.mongodb.uri", mongoUri);
        System.setProperty("spring.data.mongodb.database", "library");

        SpringApplication app = new SpringApplication(LibraryApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
        app.run(args);
    }
}