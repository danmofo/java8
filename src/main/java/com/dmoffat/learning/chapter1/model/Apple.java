package com.dmoffat.learning.chapter1.model;

/**
 * @author dan
 */
public class Apple {
    private Colour colour;
    private Double weight;

    public Apple(Colour colour, Double weight) {
        this.colour = colour;
        this.weight = weight;
    }

    private Apple(Builder builder) {
        setColour(builder.colour);
        setWeight(builder.weight);
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }



    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Apple{");
        sb.append("colour=").append(colour);
        sb.append(", weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }

    public static final class Builder {
        private Colour colour;
        private Double weight;

        public Builder() {
        }

        public Builder colour(Colour val) {
            colour = val;
            return this;
        }

        public Builder weight(Double val) {
            weight = val;
            return this;
        }

        public Apple build() {
            return new Apple(this);
        }
    }
}
