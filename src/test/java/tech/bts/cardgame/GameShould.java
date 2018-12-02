package tech.bts.cardgame;

import org.junit.Test;
import tech.bts.cardgame.exception.*;
import tech.bts.cardgame.model.Card;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Game;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class GameShould {

    @Test
    public void be_open_when_created() {

        Game g = new Game(new Deck());

        //assertEquals("OPEN", g.getState());

        assertThat(g.getState(), is(Game.State.OPEN));
    }

    @Test
    public void allow_joining_when_open() {

        Game g = new Game(new Deck());

        g.join("john");

        //assertEquals(Arrays.asList("john"), g.getPlayerNames());

        assertThat(g.getPlayerNames(), is(Arrays.asList("john")));
        assertThat(g.getState(), is(Game.State.OPEN));
    }

    @Test
    public void be_playing_when_2_players_join() {

        Game g = new Game(new Deck());

        g.join("john");
        g.join("peter");

        assertThat(g.getState(), is(Game.State.PLAYING));

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

    @Test
    public void allow_picking_cards_when_playing() {

        Card c1 = new Card(1,1,8);
        Deck d = new Deck();
        d.add(c1);

        Game g = new Game(d);

        g.join("john");
        g.join("peter");

        Card c = g.pickCard("john");

        //assertNotEquals(null, c);

        assertThat(c,notNullValue());
        assertThat(c1,is(c));
    }

    @Test (expected = PlayerNotInTheGameException.class)
    public void not_allow_picking_cards_if_not_join() {

        Game g = new Game(new Deck());

        g.join("john");
        g.join("peter");

        g.pickCard("mary");
    }

    @Test (expected = CannotPick2CardsInARowException.class)
    public void not_allow_picking_2_cards_in_a_row() {

        Deck d = new Deck();
        d.add(new Card(1,1,8));
        d.add(new Card(2,1,7));
        d.add(new Card(2,3,5));

        Game g = new Game(d);
        g.join("john");
        g.join("peter");

        g.pickCard("john");
        g.pickCard("john");
    }

    @Test
    public void allow_picking_if_previous_card_was_discarded() {

        Deck d = new Deck();
        Card c1 = new Card(1,1,8);
        d.add(c1);
        Card c2 = new Card(2,3,5);
        d.add(c2);

        Game g = new Game(d);
        g.join("john");
        g.join("peter");

        Card pc1 = g.pickCard("john");
        g.discard("john");
        Card pc2 = g.pickCard("john");

        assertThat(pc1, is(c2));
        assertThat(pc2, is(c1));
    }

    @Test (expected = NotPlayingException.class)
    public void not_allow_picking_if_not_playing() {

        Deck d = new Deck();
        d.add(new Card(1,1,8));

        Game g = new Game(d);
        g.join("john");

        g.pickCard("john");
    }

    @Test (expected = CannotActWithoutPreviouslyPickingException.class)
    public void not_allow_discarding_without_previously_picking() {

        Deck d = new Deck();
        d.add(new Card(1,1,8));

        Game g = new Game(d);
        g.join("john");

        g.discard("john");
    }

    @Test
    public void allow_picking_if_previous_card_was_kept() {

        Deck d = new Deck();
        d.add(new Card(3,5,2));
        d.add(new Card(5,1,4));
        Card c1 = new Card(1,1,8);
        d.add(c1);
        Card c2 = new Card(2,3,5);
        d.add(c2);

        Game g = new Game(d);
        g.join("john");
        g.join("peter");

        Card pc1 = g.pickCard("john");
        g.keep("john");
        Card pc2 = g.pickCard("peter");
        g.keep("peter");

        g.pickCard("john");
        g.keep("john");
        g.pickCard("peter");
        g.keep("peter");

        assertThat(pc1, is(c2));
        assertThat(pc2, is(c1));

    }

    @Test (expected = CannotActWithoutPreviouslyPickingException.class)
    public void not_allow_keeping_without_previously_picking() {

        Deck d = new Deck();
        d.add(new Card(1,1,8));

        Game g = new Game(d);
        g.join("john");

        g.keep("john");
    }

    @Test (expected = MaximumDiscardLimitExceededException.class)
    public void not_allow_discarding_more_than_2_cards() {

        Deck d = new Deck();
        d.generate();

        Game g = new Game(d);
        g.join("john");
        g.join("peter");

        g.pickCard("john");
        g.discard("john");
        g.pickCard("john");
        g.discard("john");
        g.pickCard("john");
        g.discard("john");

    }

    @Test (expected = HandSizeLimitExceededException.class)
    public void not_allow_picking_if_keep_3_cards() {

        Deck d = new Deck();
        d.generate();

        Game g = new Game(d);
        g.join("john");
        g.join("peter");

        g.pickCard("john");
        g.keep("john");
        g.pickCard("john");
        g.keep("john");
        g.pickCard("john");
        g.keep("john");

        g.pickCard("john");

    }

    @Test
    public void automatically_fill_hand_after_discarding_2_cards() {

        Deck d = new Deck();
        d.generate();

        Game g = new Game(d);
        g.join("john");
        g.join("peter");

        g.pickCard("john");
        g.keep("john");
        g.pickCard("john");
        g.keep("john");
        g.pickCard("john");
        g.discard("john");
        g.pickCard("john");
        g.discard("john");

        assertThat(g.getPlayer("john").getHand().handSize(), is(3));
    }

    @Test
    public void give_points_to_winner() {
        Deck d = new Deck();
        d.add(new Card(3,5,2));
        d.add(new Card(5,1,4));
        d.add(new Card(5,2,3));
        d.add(new Card(8,1,1));
        d.add(new Card(4,3,3));
        d.add(new Card(2,7,1));

        Game g = new Game(d);
        g.join("john");
        g.join("peter");

        g.pickCard("john");
        g.keep("john");

        g.pickCard("peter");
        g.keep("peter");

        g.pickCard("john");
        g.keep("john");

        g.pickCard("peter");
        g.keep("peter");

        g.pickCard("john");
        g.keep("john");

        g.pickCard("peter");
        g.keep("peter");

        assertThat(g.getPlayer("john").getHand().getPoint(), is(0));
        assertThat(g.getPlayer("peter").getHand().getPoint(), is(1));
    }

    @Test
    public void finish_when_deck_has_less_than_10_cards(){
        Deck d = new Deck();
        d.add(new Card(1,1,8));
        d.add(new Card(2,1,7));
        d.add(new Card(2,3,5));
        d.add(new Card(1,2,7));
        d.add(new Card(2,6,2));
        d.add(new Card(3,5,2));
        d.add(new Card(5,1,4));
        d.add(new Card(5,2,3));
        d.add(new Card(8,1,1));
        d.add(new Card(4,3,3));
        d.add(new Card(2,7,1));

        Game g = new Game(d);
        g.join("john");
        g.join("peter");

        g.pickCard("john");
        g.keep("john");

        g.pickCard("peter");
        g.keep("peter");

        g.pickCard("john");
        g.keep("john");

        g.pickCard("peter");
        g.keep("peter");

        g.pickCard("john");
        g.keep("john");

        g.pickCard("peter");
        g.keep("peter");

        assertThat(g.getState(), is(Game.State.FINISHED));
    }

    @Test
    public void refresh_hands_and_battle_points_when_starting_next_battle_but_keep_total_game_points() {
        Deck d = new Deck();
        d.add(new Card(3,5,2));
        d.add(new Card(5,1,4));
        d.add(new Card(5,2,3));
        d.add(new Card(8,1,1));
        d.add(new Card(4,3,3));
        d.add(new Card(2,7,1));

        Game g = new Game(d);
        g.join("john");
        g.join("peter");

        g.pickCard("john");
        g.keep("john");

        g.pickCard("peter");
        g.keep("peter");

        g.pickCard("john");
        g.keep("john");

        g.pickCard("peter");
        g.keep("peter");

        g.pickCard("john");
        g.keep("john");

        g.pickCard("peter");
        g.keep("peter");

        g.nextBattle();

        assertEquals(0, g.getPlayer("john").getHand().handSize());
        assertEquals(0, g.getPlayer("peter").getHand().handSize());

        assertThat(g.getPlayer("john").getHand().getPoint(), is(0));
        assertThat(g.getPlayer("peter").getHand().getPoint(), is(0));
        assertThat(g.getPlayer("john").getPoint(), is(0));
        assertThat(g.getPlayer("peter").getPoint(), is(1));
    }
}

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
 * - TODO: picking is only allowed while playing.
 * - After picking a card, a player must keep it or discard it before picking another one.
 * - A player can only discard 2 cards (i.e. must pick 3 cards).
 *
 * The battle (point calculation):
 * - When the 2 players have picked 3 cards, the winner of that round is calculated:
 * - Each player adds all magics, all strengths and all intelligences
 * - Totals of each category is compared between players
 * - Player who wins in 2 categories earns a point (there may be no winner)
 *
 * - After the points are calculated, a new battle starts (players pick cards again)
 * - If there are less than 10 cards in the deck, the game changes to state FINISHED
 */
