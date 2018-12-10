package tech.bts.cardgame;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.model.GameUser;
import tech.bts.cardgame.repository.GameRepository;
import tech.bts.cardgame.service.GameService;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner createDummyData(GameService gameService) {
        return (args) -> {
            Game g1 = gameService.createGame();
            Game g2 = gameService.createGame();
            Game g3 = gameService.createGame();
            gameService.joinGame(new GameUser(g1.getId(),"cat"));
            gameService.joinGame(new GameUser(g3.getId(),"bird"));
            gameService.joinGame(new GameUser(g2.getId(),"dog"));
            gameService.joinGame(new GameUser(g2.getId(),"cat"));
        };
    }
}