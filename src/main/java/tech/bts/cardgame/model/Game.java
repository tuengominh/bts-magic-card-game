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

    public Player join(String username) {

        if (!state.equals(State.OPEN)) {
            throw new JoiningNotAllowedException();
        }

        players.put(username, new Player(username));

        if (players.size() == MAXIMUM_PLAYER_NUM) {
            this.state = State.PLAYING;
        }

        return new Player(username);
    }

    public Card pickCard(String username) {

        if (!this.getPlayerNames().contains(username)) {
            throw new PlayerNotInTheGameException();
        }

        if (!state.equals(State.PLAYING)) {
            throw new NotPlayingException();
        }

        Player player = players.get(username);

        if(player.getHand().handSize() >= HAND_SIZE) {
            throw new HandSizeLimitExceededException();
        }

        if (player.getPickedCard() != null) {
            throw new CannotPick2CardsInARowException();
        }

        Card pickedCard = deck.pickCard();

        player.setPickedCard(pickedCard);

        return pickedCard;
    }

    public void discard(String username) {

        Player player = players.get(username);

        if (player.getPickedCard() == null) {
            throw new CannotActWithoutPreviouslyPickingException();
        }

        if (player.getDiscardCounter() >= MAXIMUM_DISCARD) {
            throw new MaximumDiscardLimitExceededException();
        }

        player.setPickedCard(null);
        player.setDiscardCounter(player.getDiscardCounter() + 1);

        if (player.getDiscardCounter() == MAXIMUM_DISCARD) {
            autoFill(username);
        }

        int handsFilled = 0;
        for (String name : getPlayerNames()) {
            player = players.get(name);
            if (player.getHand().handSize() == HAND_SIZE) {
                handsFilled++;
            }
        }

        if (handsFilled == 2) {
            battle();
        }

    }

    public void keep(String username) {
        Player player = players.get(username);

        if (player.getPickedCard() != null) {
            Hand hand = player.getHand();
            if (hand.handSize() < HAND_SIZE) {
                hand.keep(player.getPickedCard());
                player.setHand(hand);
                player.setPickedCard(null);
            } else {
                throw new HandSizeLimitExceededException();
            }
        } else {
            throw new CannotActWithoutPreviouslyPickingException();
        }

        int handsFilled = 0;
        for (String name : getPlayerNames()) {
            player = players.get(name);
            if (player.getHand().handSize() == HAND_SIZE) {
                handsFilled++;
            }
        }

        if (handsFilled == 2) {
            battle();
        }
    }

    public void autoFill(String username) {

        Player player = players.get(username);

        if (player.getDiscardCounter() == MAXIMUM_DISCARD) {
            Hand hand = player.getHand();
            while (hand.handSize() < HAND_SIZE) {
                pickCard(username);
                keep(username);
            }
        }
    }

    public void battle() {

        Map<String, Card> allAccumulateCards = new HashMap<>();

        for (String username : getPlayerNames()) {

            allAccumulateCards.put(username, players.get(username).getHand().calculate());
            players.get(username).setPickedCard(null);
            players.get(username).setHand(new Hand());
            players.get(username).setDiscardCounter(0);

        }

        Card accumulateCard1 = allAccumulateCards.get(getPlayerNames().get(0));
        Card accumulateCard2 = allAccumulateCards.get(getPlayerNames().get(1));
        int points1 = 0;
        int points2 = 0;

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

        if (result > 0) {
            players.get(getPlayerNames().get(0)).setPoint(1);
        } else if (result < 0) {
            players.get(getPlayerNames().get(1)).setPoint(1);
        } else {
            players.get(getPlayerNames().get(0)).setPoint(0);
            players.get(getPlayerNames().get(1)).setPoint(0);
        }

        if (deck.deckSize() < MINIMUM_DECK_SIZE) {
            this.state = State.FINISHED;
        }

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

}