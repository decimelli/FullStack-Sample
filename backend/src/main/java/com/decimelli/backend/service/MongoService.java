package com.decimelli.backend.service;

import com.decimelli.backend.model.Book;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MongoService {

    private MongoClient mongoClient;
    private final Jsonb jsonb = JsonbBuilder.create();

    @Inject
    @ConfigProperty(name = "MONGODB_CONNECTION_URL")
    private Provider<String> url;

    @PostConstruct
    private void init() {
        System.out.println("Connecting to MongoDB...");
        mongoClient = MongoClients.create(url.get());
        System.out.println("...connected to MongoDB: " + mongoClient.getClusterDescription());
    }

    public List<String> getAllDbs() {
        List<String> dbnames = new ArrayList<>();
        mongoClient.listDatabaseNames().into(dbnames);
        return dbnames;
    }

    public List<String> getDb(String dbname) {
        List<String> collectionNames = new ArrayList<>();
        mongoClient.getDatabase(dbname).listCollectionNames().into(collectionNames);
        return collectionNames;
    }

    protected void addBook(Book book) {
        MongoCollection<Document> bookCollection = mongoClient.getDatabase(Book.bookDbName)
                .getCollection(Book.bookCollectionName);
        String bookJson = jsonb.toJson(book);
        Document bookDocument = Document.parse(bookJson);
        bookCollection.insertOne(bookDocument);
    }

    protected List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        MongoCursor<Document> bookCollection = mongoClient.getDatabase(Book.bookDbName)
                .getCollection(Book.bookCollectionName)
                .find()
                .cursor();
        while(bookCollection.hasNext()) {
            books.add(jsonb.fromJson(bookCollection.next().toJson(), Book.class));
        }
        return books;
    }
}
