package tech.bts.cardgame.service;

import tech.bts.cardgame.model.Deck;
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
        player1.setHand(deck.deal(HAND_SIZE));
        player2.setHand(deck.deal(HAND_SIZE));

        // 2 - Discarding cards
        //player1.discard(DEAL_SIZE - HAND_SIZE);
        //player2.discard(DEAL_SIZE - HAND_SIZE);

        // 3 - Picking cards
        deck.pickCard();
    }
}
