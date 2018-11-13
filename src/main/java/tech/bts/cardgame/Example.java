package tech.bts.cardgame;

import tech.bts.cardgame.model.Card;
import tech.bts.cardgame.model.Deck;

import java.util.List;

public class Example {

    public static void main(String[] args) {

      Deck d = new Deck();
      d.deckGenerator();

      List<Card> deck = d.getCards();
      System.out.println(deck.size());
      System.out.println(deck.get(4));
      System.out.println(deck.get(10));
    }
}
