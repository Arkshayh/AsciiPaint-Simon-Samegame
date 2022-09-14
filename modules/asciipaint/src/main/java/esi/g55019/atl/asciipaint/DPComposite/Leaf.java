package esi.g55019.atl.asciipaint.DPComposite;

import esi.g55019.atl.asciipaint.Point;
import esi.g55019.atl.asciipaint.Shape;

/**
 * @author Cotton Ian| g55019
 * the Leaf class extends component
 * It's a shape.
 */
// @pbt no need to compose shape. the shapes are the leafs
public class Leaf extends Component{
    private Shape shape;

    /**
     * constructor
     * @param shape Shape
     */
    public Leaf(Shape shape) {
        this.shape = shape;
    }

    /**
     * move the shape by dx and dy
     * @param dx double
     * @param dy double
     */
    @Override
    public void move(double dx, double dy) {
        shape.move(dx, dy);
    }

    /**
     * return true if the given point is in the shape otherwise false
     * @param p Point
     * @return boolean
     */
    @Override
    public boolean isInside(Point p) {
        return shape.isInside(p);
    }

    /**
     * return the calor of the shape
     * @return char
     */
    @Override
    public char getColor() {
        return shape.getColor();
    }

    /**
     * change the color of the shape
     * @param color char
     */
    @Override
    public void changeColor(char color) {
        shape.changeColor(color);
    }


    @Override
    public String toString() {
        return shape.toString();
    }


}
