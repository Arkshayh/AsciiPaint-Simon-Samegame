package asciipaint;

/**
 * @author Cotton Ian g55019
 * Abstract class that implements the shape interface.
 * this class will be inherited by all others forms.
 * This class represents the color of each shape and its methods regarding the color.
 */

public abstract class ColoredShape implements Shape{
    private char color;

    /**
     * constructoer
     * @param color char
     */
    public ColoredShape(char color) {
        this.color = color;
    }

    /**
     * getter
     * @return char
     */
    @Override
    public char getColor() {
        return color;
    }

    /**
     * never used but is in the the diagram
     * @param color char
     */
    public void setColor(char color) {
        this.color = color;
    }
}
