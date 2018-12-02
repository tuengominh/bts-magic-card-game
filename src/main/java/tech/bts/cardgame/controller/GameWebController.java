package tech.bts.cardgame.controller;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.service.GameService;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GameWebController {

    private GameService gameService;

    @Autowired
    public GameWebController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/web/games")
    public String displayGames() throws IOException {

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
