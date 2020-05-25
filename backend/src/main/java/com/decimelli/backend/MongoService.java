package com.decimelli.backend;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class MongoService {

    private MongoClient mongoClient;

    @Inject
    @ConfigProperty(name = "mongo.admin.username")
    private String user;     // the user name

    @Inject
    @ConfigProperty(name = "mongo.admin.source")
    private String source;   // the source where the user is defined

    @Inject
    @ConfigProperty(name = "mongo.admin.password")
    private String pw; // the password as a character array

    @Inject
    @ConfigProperty(name = "mongo")
    private Provider<String> host;

    @Inject
    @ConfigProperty(name = "mongo.port")
    private int port;

    @PostConstruct
    private void init() {
        System.out.println("Connecting to MongoDB...");
        List<ServerAddress> servers = Arrays.asList(new ServerAddress(host.get(), port));
        MongoCredential credential = MongoCredential.createCredential(user, source, pw.toCharArray());
        mongoClient = MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.hosts(servers))
                .credential(credential)
                .build());
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
