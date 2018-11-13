package tech.bts.cardgame.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerDeck {

    private List<Card> deck;

    public PlayerDeck(){
        this.deck = new ArrayList<>();
    }

    public PlayerDeck(List<Card> cards) {
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
