package tech.bts.cardgame.repository;

import org.springframework.stereotype.Repository;
import tech.bts.cardgame.model.Game;

import java.util.*;

@Repository
public class GameRepositoryMap {

    private Map<Long, Game> gameMap;
    private long nextId;

    public GameRepositoryMap() {
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

    public List<Game> getAll() {
        return new ArrayList<>(gameMap.values());
    }
}