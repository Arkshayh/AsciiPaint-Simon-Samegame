package esi.g55019.atl.asciipaint;

/**
 * @author Cotton Ian, g55019
 * <p>
 * interface for implement by ColoredShape class
 */

public interface Shape {

    /**
     * move the shape.
     *
     * @param dx double
     * @param dy double
     */
    void move(double dx, double dy);

    /**
     * check if the given Point is inside the shape
     *
     * @param p Point
     * @return boolean
     */
    boolean isInside(Point p);

    /**
     * Return the color of the shape
     *
     * @return char
     */
    char getColor();

    /**
     * chang the color of the shape
     * @param color char
     */
    void changeColor(char color);
}
