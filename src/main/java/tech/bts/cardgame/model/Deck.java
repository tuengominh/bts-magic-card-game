package tech.bts.cardgame.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();
    }

    public Deck(List<Card> cards) {
        this.deck = cards;
    }

    public List<Card> getCards() {
        return deck;
    }

    public void add (Card card){ deck.add(card); }

    public void remove (Card card){
        deck.remove(card);
    }

    public Deck deckGenerator() {

        for (int magicPoint = 1; magicPoint <= 8; magicPoint++) {
            for (int strengthPoint = 1; strengthPoint <= (9 - magicPoint); strengthPoint++) {
                    Card card = new Card(magicPoint, strengthPoint);
                    deck.add(card);
            }
        }
        return this;
    }

    //TODO: empty deck

}
