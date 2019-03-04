package tech.bts.cardgame.controller;

import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.model.GameUser;
import tech.bts.cardgame.service.GameService;
import tech.bts.cardgame.util.HandlebarsUtil;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/games")
public class GameWebController {

    private GameService gameService;

    @Autowired
    public GameWebController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayGames() throws IOException {
        Template template = HandlebarsUtil.compile("games");
        Map<String, Object> map = new HashMap<>();
        map.put("games", gameService.getGames());
        return template.apply(map);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{gameId}")
    public String displayGameById(@PathVariable long gameId) throws IOException {
        Game game = gameService.getGameById(gameId);
        Template template = HandlebarsUtil.compile("detailGame");
        Map<String, Object> map = new HashMap<>();
        map.put("game", game);
        map.put("isOpen", game.getState() == Game.State.OPEN);

        //String result = template.apply(map);
        //if (game.getState() == Game.State.OPEN) {
        //    result += "<button onclick= location.href=\"/games/" + game.getId() + "/joinString\">Join this game</button>";
        //}

        return template.apply(map);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/create")
    public void createGame(HttpServletResponse response) throws IOException {
        gameService.createGame();
        response.sendRedirect("/games");
    }

    @RequestMapping(method = RequestMethod.POST, path = "/join", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void joinGame(HttpServletResponse response, GameUser gameUser) throws IOException {
        gameService.joinGame(gameUser);
        response.sendRedirect("/games/" + gameUser.getGameId());
    }
}
