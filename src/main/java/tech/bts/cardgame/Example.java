package tech.bts.cardgame;

import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.service.Game;

public class Example {

    public static void main(String[] args) {

        //1 - create prepareGame function
        Deck d = new Deck().generate().shuffle();
        Game g = prepareGame(d);

        //2 - create pickingCards function

        pickingCards(g);

        //3 - create battle function
        //with a loop to execute nextBattle() & battle() while deck.deckSize() > 10

        g.battle("John", "Peter");

        int deckSize = d.deckSize();

        while (deckSize > Game.MINIMUM_DECK_SIZE) {
            g.nextBattle();
            //Game g1 = prepareGame(d);
            //pickingCards(g1);
            //g1.battle("John", "Peter");
        }

    }

    public static Game prepareGame(Deck d){

        Game g = new Game(d);
        g.join("John"); //join based on user's input
        g.join("Peter"); //join based on user's input
        return g;
    }

    public static void pickingCards(Game g) {

        g.pickCard("John");
        g.keep("John"); //keep or discard based on user's input

        g.pickCard("Peter");
        g.discard("Peter"); //keep or discard based on user's input

        g.pickCard("John");
        g.keep("John"); //keep or discard based on user's input

        g.pickCard("Peter");
        g.keep("Peter"); //keep or discard based on user's input

        g.pickCard("John");
        g.keep("John"); //keep or discard based on user's input

        g.pickCard("Peter");
        g.discard("Peter"); //keep or discard based on user's input

    }
}
