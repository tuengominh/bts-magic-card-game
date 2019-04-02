package tech.bts.cardgame;

import tech.bts.cardgame.repository.GameRepository;
import tech.bts.cardgame.util.DataSourceUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Program that reads and displays the games from the database
 * using JDBC (Java utilities to access databases).
 */
public class ReadGamesJdbcExample {

    public static void main(String... args) throws SQLException {

        DataSource dataSource = DataSourceUtil.getDataSourceInPath();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from games");

        while (rs.next()) {

            int id = rs.getInt("id");
            String state = rs.getString("state");
            String players = rs.getString("players");

            System.out.println(id + ", " + state + ", " + players);

        }

        rs.close();
        statement.close();
        connection.close();

        System.out.println(new GameRepository().getAll());
        System.out.println(new GameRepository().getById(0));
    }
}