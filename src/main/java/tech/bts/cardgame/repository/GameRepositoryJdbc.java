package tech.bts.cardgame.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.util.DataSourceUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
public class GameRepositoryJdbc {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public GameRepositoryJdbc() {
        this.dataSource = DataSourceUtil.getDataSourceInPath();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Game game){

        jdbcTemplate.update("insert into games (state, players)" +
                " values ('" + game.getState() + "', NULL)");

        /**try {

            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("insert into games(state, players) values(?,?)");
            preparedStatement.setString(1, game.getState().toString());
            preparedStatement.setString(2, join(game.getPlayerNames(),',')); //NULL in this case
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            throw new RuntimeException("Error creating the game", e);
        }*/
    }

    public Game getById(long id) {

        RowMapper<Game> rowMapper = (rs, rowNum) -> getGame(rs);

        return jdbcTemplate.queryForObject("select * from games where id = " + id, rowMapper);

        /**return applyStatement((statement -> {

            ResultSet rs = statement.executeQuery("select * from games where id = " + id);

            Game game = null;

            if (rs.next()) {
                game = getGame(rs);
            }

            rs.close();

            return game;

        }));*/
    }

    public List<Game> getAll() {

        RowMapper<Game> rowMapper = (rs, rowNum) -> getGame(rs);

        return jdbcTemplate.query("select * from games", rowMapper);

        /**return applyStatement((statement -> {

            ResultSet rs = statement.executeQuery("select * from games");

            List<Game> games = new ArrayList<>();

            while (rs.next()) {

                Game game = getGame(rs);
                games.add(game);
            }

            rs.close();

            return games;

        }));*/
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

    /**private interface Function<P, R> {
        R apply(P t) throws Exception;
    }

    private <T> T applyStatement(Function<Statement,T> executeStatement){

        //Function interface -> takes an argument and returns a value
        //Supplier interface -> doesn't take an argument and returns a value
        //Consumer interface -> take an argument and doesn't return a value

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            T result = executeStatement.apply(statement);

            statement.close();
            connection.close();

            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
}