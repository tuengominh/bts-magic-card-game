import org.junit.Test;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.service.Game;
import tech.bts.cardgame.service.JoiningNotAllowedException;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Creating a game:
 * - A game is created with a deck of cards (each card has 3 numbers (>=1) that added make 10).
 * - Note: the 3 numbers represent magic, strength, intelligence
 * - When a game is created, its state is OPEN.
 *
 * Joining a game:
 * - A player can join an OPEN game (for simplicity, a player is indicated by its username).
 * - When 2 players join the game, the state of the game changes to PLAYING.
 * - A player can't join if the game state is not OPEN (throw an exception if someone tries).
 *
 * Picking cards:
 * - When the game is PLAYING, any player that joined the game can pick a card.
 * - After picking a card, a player must keep it or discard it.
 * - A player can only discard 2 cards (i.e. must pick 3 cards).
 *
 * The battle (point calculation):
 * - When the 2 players have picked 3 cards, the winner of that round is calculated:
 *   - Each player adds all magics, all strengths and all intelligences
 *   - Totals of each category is compared between players
 *   - Player who wins in 2 categories earns a point (there may be no winner)
 *
 * - After the points are calculated, a new battle starts (players pick cards again)
 * - If there are less than 10 cards in the deck, the game changes to state FINISHED
 */

public class GameShould {

    @Test
    public void be_open_when_created() {

        Game g = new Game(new Deck());

        //assertEquals("OPEN", g.getState());

        assertThat(g.getState(), is("OPEN"));
    }

    @Test
    public void allow_joining_when_open() {

        Game g = new Game(new Deck());

        g.join("john");

        //assertEquals(Arrays.asList("john"), g.getPlayerNames());

        assertThat(g.getPlayerNames(), is(Arrays.asList("john")));
        assertThat(g.getState(), is("OPEN"));
    }

    @Test
    public void be_playing_when_2_players_join() {

        Game g = new Game(new Deck());

        g.join("john");
        g.join("peter");

        assertThat(g.getState(), is("PLAYING"));

    }

    @Test
    public void not_allow_joining_if_not_open_long() {

        //tests that expect fail/exception/error

        Game g = new Game(new Deck());

        g.join("john");
        g.join("peter");

        try {
            g.join("mary");
            fail();

        } catch (JoiningNotAllowedException e) {
        }
    }

    @Test(expected = JoiningNotAllowedException.class)
    public void not_allow_joining_if_not_open_short() {

        //tests that expect fail/exception/error

        Game g = new Game(new Deck());

        g.join("john");
        g.join("peter");
        g.join("mary");
    }
}
