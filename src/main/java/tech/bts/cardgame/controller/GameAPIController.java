package tech.bts.cardgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.bts.cardgame.model.Card;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.model.GameUser;
import tech.bts.cardgame.service.GameService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/games")
public class GameAPIController {

    private GameService gameService;

    @Autowired
    public GameAPIController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Game> getGames(){
        return gameService.getGames();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{gameId}")
    public Game getGameById(@PathVariable long gameId){
        return gameService.getGameById(gameId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public long createGame() {
        Game game = gameService.createGame();
        return game.getId();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{gameId}/join")
    public void joinGame(@RequestBody GameUser gameUser, @PathVariable("gameId") long gameId){
        gameUser.setGameId(gameId);
        gameService.joinGame(gameUser);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{gameId}/pick")
    public Card pickCard(@RequestBody GameUser gameUser, @PathVariable("gameId") long gameId){
        gameUser.setGameId(gameId);
        return gameService.pickCard(gameUser);
    }
}