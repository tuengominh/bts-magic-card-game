package tech.bts.cardgame.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Repository;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.model.GameSearch;

import java.util.Collection;

@Repository
public class GameRepositoryMongo {

    private MongoCollection<Document> gamesCol;

    public GameRepositoryMongo() {

        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("cardgame");
        this.gamesCol = database.getCollection("games");
    }

    public void create(Game game) {
        gamesCol.insertOne(new Document().append("state", game.getState()).append("players", game.getPlayerNames()));
    }

    public void update(Game game) {

    }

    public Game getById(long id) {
        return null;
    }

    public Collection<Game> getAll() {
        return null;
    }

    public Collection<Game> find(GameSearch gameSearch) {
        return null;
    }

}
