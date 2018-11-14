package tech.bts.cardgame;

import tech.bts.cardgame.model.Card;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Hand;

import java.util.List;

public class Example {

    public static void main(String[] args) {

      Deck d = new Deck();
      d.deckGenerator();

      List<Card> deck = d.getCards();
      System.out.println(deck.size());
      System.out.println(deck.get(1));
      System.out.println(deck.get(4));
      System.out.println(deck.get(7));
      System.out.println(deck.get(8));
      System.out.println(deck.get(15));
      System.out.println(deck.get(35));
    }

    Hand player1 = new Hand();
    Hand player2 = new Hand();
}
