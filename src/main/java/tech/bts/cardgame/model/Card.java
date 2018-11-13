package tech.bts.cardgame.model;

public class Card {

    private int magicPoint;
    private int strengthPoint;
    private int intelligencePoint;

    //TODO: ensure fields only have values from 1-8

    public Card(int magicPoint, int strengthPoint) {
        this.magicPoint = magicPoint;
        this.strengthPoint = strengthPoint;
        this.intelligencePoint = 10 - this.magicPoint - this.strengthPoint;
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

    public void setMagicPoint(int magicPoint) {
        this.magicPoint = magicPoint;
    }

    public void setStrengthPoint(int strengthPoint) {
        this.strengthPoint = strengthPoint;
    }

    public void setIntelligencePoint(int intelligencePoint) {
        this.intelligencePoint = intelligencePoint;
    }
}
