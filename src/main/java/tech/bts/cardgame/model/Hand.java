package tech.bts.cardgame.model;

import java.util.List;

public class Hand {

    private List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getHand() {
        return cards;
    }

    public void keep (Card card){ cards.add(card); }

    public void discard (Card card){
        cards.remove(card);
    }

}
