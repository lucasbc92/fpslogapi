package br.com.magazineluiza.fpslogapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magazineluiza.fpslogapi.model.Game;
import br.com.magazineluiza.fpslogapi.repository.GameRepository;

import java.io.IOException;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ParserService parserService;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(String id) {
        return gameRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Game not found")
        );
    }

    public void importLogData() throws IOException {
        parserService.parseLogFile();
    }

}
