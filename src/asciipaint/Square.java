package asciipaint;

/**
 * @author g55019 / Cotton Ian
 * This square class extends the Rectangle class.
 * A square is a rectangle with the only difference that all its sides are identical.
 * A square must have a Point, the size of one side (int) and a color (char) to be created.
 */

public class Square extends Rectangle{

    /**
     * Constructor of a Square, The side of a square must be >= 0.
     * @param upperLeft
     * @param side
     * @param color
     */

    public Square(Point upperLeft, double side, char color) {
        super(upperLeft, side, side, color);
    }
}
