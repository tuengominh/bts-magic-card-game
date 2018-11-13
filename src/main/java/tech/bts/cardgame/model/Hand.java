package tech.bts.cardgame.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> deck;

    public Hand(){
        this.deck = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.deck = cards; }

    public List<Card> getCards() {
        return deck;
    }

    public void add(Card card) {
        deck.add(card);
    }

    public void remove(Card card) {
        deck.remove(card);
    }
}
