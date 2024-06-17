package br.com.magazineluiza.fpslogapi.repository;

import br.com.magazineluiza.fpslogapi.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
}