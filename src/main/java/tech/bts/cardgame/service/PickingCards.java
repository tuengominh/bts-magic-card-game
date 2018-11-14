package tech.bts.cardgame.service;

import tech.bts.cardgame.model.Card;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Hand;

public class PickingCards {
    private Deck deck;

    public PickingCards(){
        this.deck = new Deck().deckGenerator();
    }

    //TODO: initialize 5 random cards???

    //TODO: add pickup method and make sure total size of 3???

    public void pickCards(Card card, Hand player) {
        player.keep(card);
        deck.remove(card);
    }
}
