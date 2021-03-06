package tech.bts.cardgame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.bts.cardgame.model.Card;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.model.GameUser;
import tech.bts.cardgame.repository.GameRepository;
import tech.bts.cardgame.repository.GameRepositoryJdbc;

import java.util.List;

@Service
public class GameService {

    private GameRepositoryJdbc gameRepo;
    //private GameRepository gameRepo;

    @Autowired
    public GameService(GameRepositoryJdbc gameRepo) {
        this.gameRepo = gameRepo;
    }

    public Game createGame() {

        Deck deck = new Deck();
        deck.generate();
        deck.shuffle();

        Game game = new Game(deck);
        gameRepo.createOrUpdate(game);

        return game;
    }

    public void joinGame(GameUser gameUser){

        Game game = gameRepo.getById(gameUser.getGameId());
        game.join(gameUser.getUsername());
        gameRepo.createOrUpdate(game);
    }

    public Card pickCard(GameUser gameUser){

        Game game = gameRepo.getById(gameUser.getGameId());
        return game.pickCard(gameUser.getUsername());
    }

    public List<Game> getGames(){
        return gameRepo.getAll();
    }

    public Game getGameById(long gameId){
        return gameRepo.getById(gameId);
    }
}