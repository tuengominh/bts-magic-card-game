package tech.bts.cardgame.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.hand = cards;
    }

    public void keep(Card card) {
        hand.add(card);
    }

    public void discard(Card card) {
        hand.remove(card);
    }

    public List<Card> getHand() {
        return hand;
    }
}
