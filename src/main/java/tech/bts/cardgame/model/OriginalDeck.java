package tech.bts.cardgame.model;

import java.util.ArrayList;
import java.util.List;

public class OriginalDeck {

    private List<Card> originalDeck;
    int deckSize = 3^8;

    public OriginalDeck(){
        this.originalDeck = new ArrayList<>();
    }

    //TODO: generate full deck of cards

    public void deckGenerator(){
        Card currentCard = new Card();

        for (int i = 0; i <= deckSize; i ++) {
            Card nextCard = new Card.Next(currentCard).generate();
            if (nextCard.getMagicPoint() < 8) {
                nextCard.Next(nextCard).setMagicPoint().generate();
            } else if (nextCard.getStrengthPoint() < 8) {
                nextCard.Next(nextCard).setStrengthPoint().generate();
            } else {
                nextCard.Next(nextCard).setIntelligencePoint().generate();
            }
            this.originalDeck.add(nextCard);
        }
    }

    //TODO: remove 5 initial cards for each player (move to PlayerDeck)

    //TODO: put discarded cards to bottom of the deck (get from PlayerDeck)

}
