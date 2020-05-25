package com.decimelli.backend;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Provider;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MongoService {

    private MongoClient mongoClient;

    @Inject
    @ConfigProperty(name = "MONGODB-CONNECTION-URL")
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

}
