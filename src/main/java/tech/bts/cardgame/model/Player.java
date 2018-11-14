package tech.bts.cardgame.model;

public class Player {

    private String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    //public void discard(int discardQuant) {
    //}
}
