package tech.bts.cardgame.repository;

import tech.bts.cardgame.model.Game;

import java.util.HashMap;
import java.util.Map;

public class GameRepository {

    private Map<Long, Game> gameMap;
    private long nextId;

    public GameRepository() {
        gameMap = new HashMap<>();
        nextId = 0;
    }

    public void create(Game game) {
        game.setId(nextId);
        gameMap.put(nextId, game);
        nextId++;
    }

    public Game getById(long id) {
        return gameMap.get(id);
    }
}