package tech.bts.cardgame.service;

import tech.bts.cardgame.exception.*;
import tech.bts.cardgame.model.Card;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Hand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    public enum State {OPEN, PLAYING, FINISHED}

    private Deck deck;
    private State state;
    private Map<String, Hand> players;
    private Map<String, Card> pickedCardbyUserName;
    private Map<String, Integer> discardedCounterbyUserName;

    public final static int HAND_SIZE = 3;
    public final static int MAXIMUM_DISCARD = 2;
    public final static int MAXIMUM_PLAYER_NUM = 2;

    public Game(Deck deck) {
        this.deck = deck;
        this.state = State.OPEN;
        this.players = new HashMap<>();
        this.pickedCardbyUserName = new HashMap<>();
        this.discardedCounterbyUserName = new HashMap<>();
    }

    public State getState() {
        return state;
    }

    public Map<String, Hand> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return new ArrayList<>(players.keySet());
    }

    public Hand getPlayerHand(String username) {
        return players.get(username);
    }

    public int getPoints(String username) {
        return players.get(username).getPoint();
    }

    public Map<String, Card> getPickedCardbyUserName() {
        return pickedCardbyUserName;
    }

    public void join(String username) {

        if (!state.equals(State.OPEN)) {
            throw new JoiningNotAllowedException();
        }

        players.put(username, new Hand());
        discardedCounterbyUserName.put(username, 0);

        if (players.size() == MAXIMUM_PLAYER_NUM) {
            this.state = State.PLAYING;
        }
    }

    public Card pickCard(String username) {

        if (!state.equals(State.PLAYING)) {
            throw new CannotPickCardsIfNotPlayingException();
        }

        if (!this.getPlayerNames().contains(username)) {
            throw new PlayerNotInTheGameException();
        }

        Hand hand = getPlayerHand(username);
        if(hand.handSize() >= HAND_SIZE) {
            throw new HandSizeLimitExceededException();
        }

        Card pickedCard = pickedCardbyUserName.get(username);
        if (pickedCard != null) {
            throw new CannotPick2CardsInARowException();
        }

        Card newPickedCard = deck.pickCard();

        pickedCardbyUserName.put(username, newPickedCard);

        return newPickedCard;
    }

    public void discard(String username) {

        Card pickedCard = pickedCardbyUserName.get(username);
        int discardCounter = discardedCounterbyUserName.get(username);

        if (pickedCard != null) {

            if(discardCounter >= MAXIMUM_DISCARD) {
                throw new CannotDiscard3CardsException();
            } else {
                discardCounter++;
            }

        } else {
            throw new CannotDiscardWithoutPreviouslyPickingException();
        }

        pickedCardbyUserName.remove(username);
        discardedCounterbyUserName.put(username, discardCounter);
    }

    public void keep(String username) {
        Card pickedCard = pickedCardbyUserName.get(username);
        Hand hand = getPlayerHand(username);

        if (pickedCard != null) {

            if(hand.handSize() >= HAND_SIZE) {
                throw new HandSizeLimitExceededException();
            } else {
                Hand hand1 = hand.keep(pickedCard);
                players.put(username, hand1);
            }

        } else {
            throw new CannotKeepWithoutPreviouslyPickingException();
        }

        pickedCardbyUserName.remove(username);
    }

    public void fillHand(String username) {

        int discardCounter = discardedCounterbyUserName.get(username);
        Hand hand = getPlayerHand(username);

        if(discardCounter >= MAXIMUM_DISCARD) {

            if(hand.handSize() >= HAND_SIZE) {
                throw new HandSizeLimitExceededException();

            } else {
                for(int i = 0; i < HAND_SIZE - hand.handSize(); i++) {
                    pickCard(username);
                    keep(username);
                }
            }

        } else {

            throw new HaventDiscard2CardsException();
        }
    }

    public void battle (Hand hand1, Hand hand2) {

        int points1 = 0;
        int points2 = 0;

        if(hand1.handSize() < HAND_SIZE || hand2.handSize() < HAND_SIZE) {

            throw new CannotBattleIfHandsNotFilledException();

        } else {

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

        }

        if(deck.deckSize() < 10) {
            this.state = State.FINISHED;
        }

        int result = points1 - points2;

        if (result < 0) {
            players.get(1).setPoint(1);

        } else if (result > 0) {
            players.get(0).setPoint(1);
        }

    }

    public void nextBattle() {

        this.players.put(getPlayerNames().get(0), new Hand());
        this.players.put(getPlayerNames().get(1), new Hand());
        this.pickedCardbyUserName = new HashMap<>();
        this.discardedCounterbyUserName = new HashMap<>();

    }
}