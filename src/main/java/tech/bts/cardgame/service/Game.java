package tech.bts.cardgame.service;

import tech.bts.cardgame.exception.CannotPick2CardsInARowException;
import tech.bts.cardgame.exception.JoiningNotAllowedException;
import tech.bts.cardgame.exception.PlayerNotInTheGameException;
import tech.bts.cardgame.model.Card;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Hand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    public enum State { OPEN, PLAYING, FINISHED }

    private Deck deck;
    private State state;
    private List<String> usernames;
    private Map<String, Card> pickedCardbyUserName;

    public final static int DEAL_SIZE = 5;
    public final static int HAND_SIZE = 3;

    public Game(Deck deck) {
        this.deck = deck;
        this.state = State.OPEN;
        this.usernames = new ArrayList<>();
        this.pickedCardbyUserName = new HashMap<>();
    }

    public State getState() {
        return state;
    }

    public void join(String username) {

        if (!state.equals(State.OPEN)) {
            throw new JoiningNotAllowedException();
        }

        usernames.add(username);

        if(usernames.size() == 2) {
            this.state = State.PLAYING;
        }
    }

    public List<String> getPlayerNames() {
        return usernames;
    }

    public Card pickCard(String username) {

        if (!usernames.contains(username)) {
            throw new PlayerNotInTheGameException();
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
        pickedCardbyUserName.remove(username);
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
