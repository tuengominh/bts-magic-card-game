package tech.bts.cardgame.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringUtil {

    public static String join(Collection<String> texts, String separator) {
        String result = "";
        List<String> strings = new ArrayList<>(texts);

        for (int i = 0; i < strings.size() - 1; i ++) {
            result += strings.get(i) + " " + separator + " ";
        }

        result += strings.get(strings.size() - 1);

        return result;
    }
}
