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

    public void cardGenerator(){
        Card currentCard = new Card();
        for (int i = 0; i <= deckSize; i ++) {
            Card nextCard = new Card.Next(currentCard).generate();
            if (nextCard.getMagicPoint() < 8) {
                Card nextCard2 = new Card.Next(nextCard).setMagicPoint().generate();
            } else if (nextCard.getStrengthPoint() < 8) {
                Card nextCard3 = new Card.Next(nextCard).setStrengthPoint().generate();
            } else {
                Card nextCard4 = new Card.Next(nextCard).setIntelligencePoint().generate();
            }
        }
    }

}
