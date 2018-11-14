package tech.bts.cardgame.service;

import tech.bts.cardgame.model.Card;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Hand;

import java.util.List;

public class PickingCards {
    private Deck deck;

    public PickingCards(){
        this.deck = new Deck().deckGenerator();
    }

    //TODO: initialize 5 random cards???

    // TODO: add pickup method

    public void pickCards(Card card, Hand player) {
        player.keep(card);
        deck.remove(card);
    }
}
