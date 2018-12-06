package tech.bts.cardgame.controller;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.model.GameUser;
import tech.bts.cardgame.service.GameService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
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
        return buildGameList();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{gameId}")
    public String displayGameById(@PathVariable long gameId) throws IOException {

        Game game = gameService.getGameById(gameId);

        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".html.hbs");

        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("detailGame");

        Map<String, Game> map = new HashMap<>();
        map.put("games", game);

        String result = template.apply(map);

        if (game.getState() == Game.State.OPEN) {
            result += "<button onclick= location.href=\"/games/" + game.getId() + "/join\">Join this game</button></h2>";
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/create")
    public void createGame(HttpServletResponse response) throws IOException {
        gameService.createGame();
        response.sendRedirect("/games");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{gameId}/join")
    public void joinGame(HttpServletResponse response, @PathVariable long gameId) throws IOException {
        GameUser gameUser = new GameUser(gameId, "tue");
        gameService.joinGame(gameUser);
        response.sendRedirect("/games/" + gameId);
    }

    private String buildGameList() throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".html.hbs");

        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("games");

        Map<String, Collection<Game>> map = new HashMap<>();
        map.put("games", gameService.getGames());

        return template.apply(map);
    }
}
