package tech.bts.cardgame.model;

public class Card {

    private int magicPoint;
    private int strengthPoint;
    private int intelligencePoint;

    //TODO: ensure fields only have values from 1-8

    //TODO: ensure total points of 10

    public Card() {
        this.magicPoint = 1;
        this.strengthPoint = 1;
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

    //TODO: ++ each parameters, 1-8
    public static class Next {
        private Card card;

        public Next(Card currentCard) {
            this.card = currentCard;
        }

        public Next setMagicPoint() {

            this.card.magicPoint = Math.min(this.card.magicPoint + 1,8);
            return this;
        }

        public Next setStrengthPoint() {
            this.card.strengthPoint = Math.min(this.card.strengthPoint + 1,8);
            return this;
        }

        public Next setIntelligencePoint() {
            this.card.intelligencePoint = Math.min(this.card.intelligencePoint + 1,8);
            return this;
        }

        public Card generate() {
            return this.card;
        }
    }
}
