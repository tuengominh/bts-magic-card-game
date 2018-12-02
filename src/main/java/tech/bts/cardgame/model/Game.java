package tech.bts.cardgame.model;

import tech.bts.cardgame.exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    public enum State {OPEN, PLAYING, FINISHED}

    private long id;
    private final Deck deck;
    private State state;

    private Map<String, Player> players;

    public final static int HAND_SIZE = 3;
    public final static int MAXIMUM_DISCARD = 2;
    public final static int MAXIMUM_PLAYER_NUM = 2;
    public final static int MINIMUM_DECK_SIZE = 10;

    public Game(Deck deck) {
        this.deck = deck;
        this.state = State.OPEN;
        this.players = new HashMap<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public Player getPlayer(String username) {
        return players.get(username);
    }

    public List<String> getPlayerNames() {
        return new ArrayList<>(players.keySet());
    }

    public void join(String username) {

        if (!state.equals(State.OPEN)) {
            throw new JoiningNotAllowedException();
        }

        players.put(username, new Player(username));

        if (players.size() == MAXIMUM_PLAYER_NUM) {
            this.state = State.PLAYING;
        }
    }

    public Card pickCard(String username) {

        if (!state.equals(State.PLAYING)) {
            throw new NotPlayingException();
        }

        if (!this.getPlayerNames().contains(username)) {
            throw new PlayerNotInTheGameException();
        }

        Player player = players.get(username);
        Hand hand = player.getHand();

        if(hand.handSize() >= HAND_SIZE) {
            throw new HandSizeLimitExceededException();
        }

        Card pickedCard = player.getPickedCard();
        if (pickedCard != null) {
            throw new CannotPick2CardsInARowException();
        }

        Card newPickedCard = deck.pickCard();

        player.setPickedCard(newPickedCard);

        return newPickedCard;
    }

    public void discard(String username) {

        Player player = players.get(username);
        Card pickedCard = player.getPickedCard();
        int discardCounter = player.getDiscardCounter();
        Hand hand = player.getHand();

        if(pickedCard == null) {
            throw new CannotActWithoutPreviouslyPickingException();
        } else {
            if (discardCounter >= MAXIMUM_DISCARD) {
                throw new MaximumDiscardLimitExceededException();
            } else {
                player.setDiscardCounter(discardCounter + 1);
                player.setPickedCard(null);
            }
        }

        if (discardCounter == MAXIMUM_DISCARD) {
            while(hand.handSize() < HAND_SIZE) {
                pickCard(username);
                keep(username);
            }
        }

        battle();
    }

    public void keep(String username) {
        Player player = players.get(username);
        Card pickedCard = player.getPickedCard();
        Hand hand = player.getHand();

        if (pickedCard != null) {
            if(hand.handSize() >= HAND_SIZE) {
                throw new HandSizeLimitExceededException();
            } else {
                Hand hand1 = hand.keep(pickedCard);
                player.setHand(hand1);
                players.put(username, player);
            }
        } else {
            throw new CannotActWithoutPreviouslyPickingException();
        }
        player.setPickedCard(null);
    }

    public void battle () {

        Player player1 = players.get(0);
        Player player2 = players.get(1);

        Hand hand1 = player1.getHand();
        Hand hand2 = player2.getHand();
        int points1 = 0;
        int points2 = 0;

        if(deck.deckSize() < MINIMUM_DECK_SIZE) {
            this.state = State.FINISHED;
        }

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

        int result = points1 - points2;

        if (result < 0) {
            hand2.setPoint(1);

        } else if (result > 0) {
            hand1.setPoint(1);
        }

        player1.setHand(hand1);
        players.put(player1.getName(), player1);
        player1.setPoint(hand1.getPoint());

        player2.setHand(hand2);
        players.put(player2.getName(), player2);
        player2.setPoint(hand2.getPoint());

    }

    public void nextBattle() {

        Player player1 = players.get(0);
        Player player2 = players.get(1);

        player1.setHand(new Hand());
        player2.setHand(new Hand());

        player1.setPickedCard(null);
        player2.setPickedCard(null);

        player1.setDiscardCounter(0);
        player1.setDiscardCounter(0);
    }

}