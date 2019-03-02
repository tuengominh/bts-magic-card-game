package tech.bts.cardgame.repository;

import org.springframework.stereotype.Repository;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.util.DataSourceUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GameRepositoryJbdc {

    private Map<Long, Game> gameMap;
    //private long nextId;
    private ResultSet rs;

    public GameRepositoryJbdc() throws SQLException{
        gameMap = new HashMap<>();
        //nextId = 0;
        rs = DataSourceUtil.getDataSourceInPath().getConnection().createStatement().executeQuery("select * from games");
    }

    public void create(Game game) {
        //TODO: double-check create method
        //game.setId(nextId);
        gameMap.put(game.getId(), game);
        //nextId++;
    }

    public Game getById(long id) throws SQLException{

        rs = DataSourceUtil.getDataSourceInPath().getConnection().createStatement().executeQuery("select * from games WHERE id = " + id);
        String state = rs.getString("state");
        String players = rs.getString("players");

        Game g = new Game(new Deck());
        g.setId(id);
        g.setState(Game.State.valueOf(state));
        String[] playersArray = players.split(",");
        for ( String player : playersArray) {
            g.join(player);
        }
        create(g);
        return gameMap.get(id);
    }

    public List<Game> getAll() throws SQLException{

        rs = DataSourceUtil.getDataSourceInPath().getConnection().createStatement().executeQuery("select * from games");

        while (rs.next()) {
            int id = rs.getInt("id");
            String state = rs.getString("state");
            String players = rs.getString("players");

            System.out.println(id + ", " + state + ", " + players);

            Game g = new Game(new Deck());
            g.setId(id);
            g.setState(Game.State.valueOf(state));
            String[] playersArray = players.split(",");
            for ( String player : playersArray) {
                g.join(player);
            }
            create(g);
        }

        rs.close();
        DataSourceUtil.getDataSourceInPath().getConnection().createStatement().close();
        DataSourceUtil.getDataSourceInPath().getConnection().close();

        return new ArrayList<>(gameMap.values());
    }
}