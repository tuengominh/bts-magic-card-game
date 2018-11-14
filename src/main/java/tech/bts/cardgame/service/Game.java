package tech.bts.cardgame.service;

import tech.bts.cardgame.model.Card;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Hand;
import tech.bts.cardgame.model.Player;

public class Game {

    private Player player1;
    private  Player player2;
    private Deck deck;

    public final static int DEAL_SIZE = 5;
    public final static int HAND_SIZE = 3;

    public Game(Player player1, Player player2, Deck deck) {
        this.player1 = player1;
        this.player2 = player2;
        this.deck = deck;
    }

    public void play() {

        // Battle

        // 1 - Dealing cards

        Hand hand1 = deck.deal(HAND_SIZE);
        player1.setHand(hand1);

        Hand hand2 = deck.deal(HAND_SIZE);
        player2.setHand(hand2);

        // 2 - Discarding cards
        // player1.discard(DEAL_SIZE - HAND_SIZE);
        // player2.discard(DEAL_SIZE - HAND_SIZE);

        // 3 - Fight with the cards
        int result = compare(hand1, hand2);
        System.out.println(result);

        // print the name of the winner
    }

    private int compare(Hand hand1, Hand hand2) {

        int points1 = 0;
        int points2 = 0;

        Card accumulateCard1 = hand1.calculate();
        Card accumulateCard2 = hand2.calculate();

        if (accumulateCard1.getMagicPoint() > accumulateCard2.getMagicPoint()) {
            points1++;
        } else if (accumulateCard1.getMagicPoint() < accumulateCard2.getMagicPoint()) {
            points2++;
        }

        if (accumulateCard1.getStrengthPoint() > accumulateCard2.getStrengthPoint()) {
            points1++;
        } else if (accumulateCard1.getStrengthPoint() < accumulateCard2.getStrengthPoint()) {
            points2++;
        }

        if (accumulateCard1.getIntelligencePoint() > accumulateCard2.getIntelligencePoint()) {
            points1++;
        } else if (accumulateCard1.getIntelligencePoint() < accumulateCard2.getIntelligencePoint()) {
            points2++;
        }

        return points1 - points2;
    }
}
