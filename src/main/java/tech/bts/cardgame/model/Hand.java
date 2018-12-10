package tech.bts.cardgame.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> hand;

    public Hand(List<Card> cards) {
        this.hand = cards;
    }

    public Hand(){
        this.hand = new ArrayList<>();
    }

    public Hand keep (Card card){
        hand.add(card);
        return this;
    }

    public Card calculate() {
        int magic = 0;
        int strength = 0;
        int intelligence = 0;
        for (Card card : hand) {
            magic += card.getMagicPoint();
            strength += card.getStrengthPoint();
            intelligence += card.getIntelligencePoint();
        }
        return new Card(magic, strength, intelligence);
    }

    @Override
    public String toString(){
        String result = "";
        result += "This hand includes " + this.hand.size() + " cards: ";
        for (int i = 0; i < hand.size(); i++) {
            result += "\n";
            result += hand.get(i).toString();
            result += " ";
        }
        return result;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int handSize() {
        return this.hand.size();
    }

}
