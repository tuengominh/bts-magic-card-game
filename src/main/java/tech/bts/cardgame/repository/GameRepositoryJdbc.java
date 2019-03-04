package tech.bts.cardgame.repository;

import org.springframework.stereotype.Repository;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.util.DataSourceUtil;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class GameRepositoryJdbc {

    private DataSource dataSource;

    public GameRepositoryJdbc() {
        this.dataSource = DataSourceUtil.getDataSourceInPath();
    }

    public void create(Game game) {
        throw new RuntimeException("Not implemented yet"); // TODO: insert game into database
    }

    public Game getById(long id) {

        try {

            ResultSet rs = dataSource.getConnection().createStatement().executeQuery("select * from games where id = " + id);

            Game game = null;

            if (rs.next()) {
                game = getGame(rs);
            }

            rs.close();
            dataSource.getConnection().createStatement().close();
            dataSource.getConnection().close();

            return game;

        } catch (Exception e) {
            throw new RuntimeException("Error getting the games", e);
        }
    }

    public List<Game> getAll() {

        try {

            ResultSet rs = dataSource.getConnection().createStatement().executeQuery("select * from games");

            List<Game> games = new ArrayList<>();

            while (rs.next()) {

                Game game = getGame(rs);
                games.add(game);
            }

            rs.close();
            dataSource.getConnection().createStatement().close();
            dataSource.getConnection().close();

            return games;


        } catch (Exception e) {
            throw new RuntimeException("Error getting the games", e);
        }
    }

    private Game getGame(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String players = rs.getString("players");

        Game game = new Game(new Deck());
        game.setId(id);

        if (players != null) {
            String[] usernames = players.split(",");
            for (String username : usernames) {
                game.join(username);
            }
        }

        //g.setState(Game.State.valueOf(rs.getString("state")));

        return game;
    }
}