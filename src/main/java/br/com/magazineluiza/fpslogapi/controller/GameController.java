package br.com.magazineluiza.fpslogapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.magazineluiza.fpslogapi.model.Game;
import br.com.magazineluiza.fpslogapi.service.GameService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable String id) {
        return gameService.getGameById(id);
    }

    @PostMapping("/import")
    public ResponseEntity<String> importLogData() throws IOException {

    // Delegate to service with the byte array
    gameService.importLogData();

    return ResponseEntity.ok("Log data imported successfully");
    }
}