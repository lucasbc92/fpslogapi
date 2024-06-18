package br.com.magazineluiza.fpslogapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.com.magazineluiza.fpslogapi.model.Game;
import br.com.magazineluiza.fpslogapi.model.Kill;
import br.com.magazineluiza.fpslogapi.repository.GameRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ParserService {

    @Autowired
    private GameRepository gameRepository;

    public enum LogPatternsEnum {
        INITGAME_PATTERN("\\d{1,2}:\\d{2} InitGame: .+"),
        KILL_PATTERN("\\d{1,2}:\\d{2} Kill: \\d+ \\d+ \\d+: (.+?) killed (.+?) by MOD_(?:[A-Z]+_)*[A-Z]+$"),
        SHUTDOWNGAME_PATTERN("\\d{1,2}:\\d{2} ShutdownGame:");

        private final Pattern pattern;

        LogPatternsEnum(String regex) {
            this.pattern = Pattern.compile(regex);
        }

        public Pattern getPattern() {
            return pattern;
        }

        public static LogPatternsEnum getMatchingPatternEnum(String input) {
            for (LogPatternsEnum patternEnum : LogPatternsEnum.values()) {
                Matcher matcher = patternEnum.getPattern().matcher(input);
                if (matcher.find()) {
                    return patternEnum;
                }
            }
            return null;
        }
    }

    public void parseLogFile() throws IOException {
        System.out.println(ResourceUtils.getFile("classpath:games.log").getPath());
        BufferedReader reader = new BufferedReader(
            new FileReader(
                ResourceUtils.getFile("classpath:games.log")
            )
        );
        String line;
        Game currentGame = null;
        while ((line = reader.readLine()) != null) {
            LogPatternsEnum matchingPatternEnum = LogPatternsEnum.getMatchingPatternEnum(line);
            if (matchingPatternEnum != null) {
                System.out.println(matchingPatternEnum.name());
                switch(matchingPatternEnum){
                    case INITGAME_PATTERN: {
                        currentGame = new Game();
                        break;
                    }                        
                    case KILL_PATTERN: {
                        if (currentGame != null) {
                            Matcher matcher = matchingPatternEnum.getPattern().matcher(line);
                            matcher.find();
                            String killer = matcher.group(1);
                            String victim = matcher.group(2);
                            currentGame.addKill(new Kill(killer, victim));
                        }
                        break;
                    }                        
                    case SHUTDOWNGAME_PATTERN: {
                        if (currentGame != null) {
                            gameRepository.save(currentGame);
                            currentGame = null;
                        }
                        break;
                    }                        
                    default:
                        break;
                }
            }
        }
        reader.close();
    }
}