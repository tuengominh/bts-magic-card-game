package tech.bts.cardgame;

import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.service.Game;

public class Example {

    public static void main(String[] args) {

        //1 - create prepareGame function
        Deck d = new Deck().generate().shuffle();

        System.out.println(d.deckSize());

        Game g = new Game(d);
        g.join("John"); //join based on user's input
        g.join("Peter"); //join based on user's input

        //2 - create pickingCards function

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

        g.fillHand("Peter");

        System.out.println(g.getPlayerHand("Peter").handSize());
        System.out.println(g.getPlayerHand("John").handSize());
        System.out.println(d.deckSize());

        //3 - create battle function
        //with a loop to execute battles while deck.deckSize() > 10

        g.battle("John", "Peter");

        System.out.println(g.getBattlePoint("John"));
        System.out.println(g.getBattlePoint("Peter"));

        while (d.deckSize() > Game.MINIMUM_DECK_SIZE) {
            g.nextBattle();
        }

    }
}
