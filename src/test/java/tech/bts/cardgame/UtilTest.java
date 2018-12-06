package tech.bts.cardgame;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import tech.bts.cardgame.util.StringUtil;

import java.util.Arrays;

public class UtilTest {

    @Test
    public void join_2_strings() {

        String result = StringUtil.join(Arrays.asList("one", "two"), "-");

        assertThat(result, is("one - two"));
    }

    @Test
    public void join_5_strings() {

        String result = StringUtil.join(Arrays.asList("one","two","three","four","five"), "-");

        assertThat(result, is("one - two - three - four - five"));
    }

}
