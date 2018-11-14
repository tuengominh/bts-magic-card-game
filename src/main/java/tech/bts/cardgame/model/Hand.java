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

    public Card calculate() {

        int magic = 0;
        int strength = 0;
        int intelligence = 0;

        for (Card card : cards) {
            magic += card.getMagicPoint();
            strength += card.getStrengthPoint();
            intelligence += card.getIntelligencePoint();
        }

        return new Card(magic, strength, intelligence);
    }
}
