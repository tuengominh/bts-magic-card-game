package tech.bts.cardgame.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> player1;
    private List<Card> player2;

    public Hand(){
        this.player1 = new ArrayList<>();
        this.player2 = new ArrayList<>();
    }

    //TODO: initialize 5 random cards

    //TODO: make sure total size of 3

    public List<Card> getPlayer1() {
        return player1;
    }

    public List<Card> getPlayer2() {
        return player2;
    }

    public void keepPlayer1(Card card) {
        player1.add(card);
    }

    public void keepPlayer2(Card card) {
        player2.add(card);
    }

    public void discardPlayer1(Card card) {
        player1.remove(card);
    }

    public void discardPlayer2(Card card) {
        player2.remove(card);
    }
}
