package tech.bts.cardgame.repository;

import org.springframework.stereotype.Repository;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.util.DataSourceUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

import static org.apache.tomcat.util.buf.StringUtils.join;

@Repository
public class GameRepositoryJdbc {

    private DataSource dataSource;

    public GameRepositoryJdbc() {
        this.dataSource = DataSourceUtil.getDataSourceInPath();
    }

    public void create(Game game){

        try {
            Connection connection = dataSource.getConnection();
            String state = game.getState().toString();
            String players = join(game.getPlayerNames(),',');

            PreparedStatement statement = connection.prepareStatement("insert into games(state, players) values(?,?)");
            statement.setString(1, state);
            statement.setString(2, players);
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error creating the game", e);
        }

    }

    public Game getById(long id) {

        try {

            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from games where id = " + id);

            Game game = null;

            if (rs.next()) {
                game = getGame(rs);
            }

            rs.close();
            statement.close();
            connection.close();

            return game;

        } catch (Exception e) {
            throw new RuntimeException("Error getting the game", e);
        }
    }

    public List<Game> getAll() {

        try {

            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from games");

            List<Game> games = new ArrayList<>();

            while (rs.next()) {

                Game game = getGame(rs);
                games.add(game);
            }

            rs.close();
            statement.close();
            connection.close();

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