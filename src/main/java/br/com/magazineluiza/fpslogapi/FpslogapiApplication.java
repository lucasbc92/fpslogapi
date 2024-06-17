package br.com.magazineluiza.fpslogapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableMongoRepositories(basePackages = "br.com.magazineluiza.fpslogapi.repository")
public class FpslogapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FpslogapiApplication.class, args);
    }

}