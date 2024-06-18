package br.com.magazineluiza.fpslogapi.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.lang.NonNull;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "br.com.magazineluiza.fpslogapi.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {
 
    @Override
    @NonNull
    protected String getDatabaseName() {
        return "fpslogdb";
    }
 
    @Override
    @NonNull
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://mongodb:27017/fpslogdb");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();        
        return MongoClients.create(mongoClientSettings);
    }
 
    @Override
    @NonNull
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton("br.com.magazineluiza.fpslogapi");
    }
}