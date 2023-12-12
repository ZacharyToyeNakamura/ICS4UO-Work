import java.util.Random;

public class Die implements Comparable<Die> {
    private int sides;
    private int value;
    private static final Random RNG = new Random();

    public Die(int sides) {
        this.sides = sides;
    }

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public int getValue() {
        return value;
    }

    public void roll() {
        value = RNG.nextInt(sides) + 1;
    }

    @Override
    public String toString() {
        return "d" + sides + ": " + value;
    }

    public boolean equals(Die d) {
        return d.value == value;
    }


    @Override
    public int compareTo(Die d) {
        return this.value - d.value;
    }


}
