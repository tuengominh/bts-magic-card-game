package tech.bts.cardgame.model;

public class Player {

    private String name;
    private int discardCounter;
    private Card pickedCard;
    private Hand hand;
    private int point;

    public Player(String name) {
        this.name = name;
        this.discardCounter = 0;
        this.pickedCard = null;
        this.hand = new Hand();
        this.point = 0;
    }

    public String getName() {
        return name;
    }

    public int getDiscardCounter() {
        return discardCounter;
    }

    public void setDiscardCounter(int counter) {
        this.discardCounter = counter;
    }

    public Card getPickedCard() {
        return pickedCard;
    }

    public void setPickedCard(Card pickedCard) {
        this.pickedCard = pickedCard;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point += point;
    }

}
