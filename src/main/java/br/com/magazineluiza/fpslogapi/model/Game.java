package br.com.magazineluiza.fpslogapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@Document(collection = "games")
public class Game {
    @Id
    private String id;
    private int totalKills;
    private Set<String> players = new HashSet<>();
    private Map<String, Integer> kills = new HashMap<>();

    public void addKill(Kill kill) {
        String killer = kill.getKiller();
        String victim = kill.getVictim();
        String WORLD = "<world>";

        if (!WORLD.equals(killer)) {
            players.add(killer);
            if(!killer.equals(victim)) {
                kills.put(killer, kills.getOrDefault(killer, 0) + 1);
            }            
        } else {
            if(kills.getOrDefault(victim, 0) > 0) {
                kills.put(victim, kills.getOrDefault(victim, 0) - 1);
            }            
        }
        players.add(victim);
        totalKills++;
    }
}
