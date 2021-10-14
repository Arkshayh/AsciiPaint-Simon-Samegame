package esi.g55019.atl.asciipaint;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ian Cotton g55019
 * This class contains all the shapes created as well as the dimensions that the drawing will have
 */

public class Drawing {

    private List<Shape> shapes;
    private int height;
    private int width;

    /**
     * default constructor
     */
    public Drawing() {
        this.height = 50;
        this.width = 50;
        this.shapes = new ArrayList<>();
    }

    /**
     * constructor
     *
     * @param height int
     * @param width  int
     */
    public Drawing(int height, int width) {
        this.height = height;
        this.width = width;
        this.shapes = new ArrayList<>();
    }

    /**
     * add to the drawing the given shape
     *
     * @param shape Shape
     */
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    /**
     * Returns the first form containing the point given in parameter,
     * if no form contains this point then this method returns null
     *
     * @param p Point
     * @return Shape or null
     */
    public Shape getShapeAt(Point p) {
        for (Shape shape : shapes) {
            if (shape.isInside(p)) {
                return shape;
            }
        }
        return null;
    }

    /**
     * getter height
     *
     * @return int
     */
    public int getHeight() {
        return height;
    }

    /**
     * getter width
     *
     * @return int
     */
    public int getWidth() {
        return width;
    }


}
