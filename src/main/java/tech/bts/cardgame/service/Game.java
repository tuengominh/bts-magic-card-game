package tech.bts.cardgame.service;

import tech.bts.cardgame.model.Card;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Hand;
import tech.bts.cardgame.model.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Deck deck;
    private String state;
    private List<String> usernames;

    public final static int DEAL_SIZE = 5;
    public final static int HAND_SIZE = 3;

    public Game(Deck deck) {
        this.deck = deck;
        this.state = "OPEN";
        this.usernames = new ArrayList<>();
    }

    public String getState() {
        return state;
    }

    public List<String> getPlayerNames() {
        return usernames;
    }

    public void join(String username) {

        if (!state.equals("OPEN")) {
            throw new JoiningNotAllowedException();
        }

        usernames.add(username);

        if(usernames.size() == 2) {
            this.state = "PLAYING";
        }
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
