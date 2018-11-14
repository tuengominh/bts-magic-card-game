package tech.bts.cardgame.service;

import tech.bts.cardgame.model.Deck;

public class PickingCards {
    private Deck deck;

    public PickingCards(){
        this.deck = new Deck().deckGenerator();
    }

    //TODO: initialize 5 random cards???

    //TODO: add pickup method and make sure total size of 3???

}
