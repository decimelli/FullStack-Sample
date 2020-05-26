package com.decimelli.backend.service;

import com.decimelli.backend.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BookService {

    @Inject
    MongoService mongodb;

    public void addBook(Book book) {
        System.out.println("Adding: " + book);
        mongodb.addBook(book);
    }

    public List<Book> getAllBooks() {
        return mongodb.getAllBooks();
    }
}
