package tech.bts.cardgame.model;

public class Card {

    private int magicPoint;
    private int strengthPoint;
    private int intelligencePoint;

    public Card(int magicPoint, int strengthPoint, int intelligencePoint) {
        this.magicPoint = magicPoint;
        this.strengthPoint = strengthPoint;
        this.intelligencePoint = intelligencePoint;
    }

    public String toString(){
        return "This card has " + this.magicPoint + " magic points, " + this.strengthPoint + " strength points, " + this.intelligencePoint + " intelligence points.";
    }

    public int getMagicPoint() {
        return magicPoint;
    }

    public int getStrengthPoint() {
        return strengthPoint;
    }

    public int getIntelligencePoint() {
        return intelligencePoint;
    }

}
