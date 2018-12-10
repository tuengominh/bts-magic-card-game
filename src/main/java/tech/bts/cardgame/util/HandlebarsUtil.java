package tech.bts.cardgame.util;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

public class HandlebarsUtil {

    public static Handlebars getHandleBars() {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".html.hbs");
        return new Handlebars(loader);
    }
}
