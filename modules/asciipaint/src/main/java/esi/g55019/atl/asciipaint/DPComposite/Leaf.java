package esi.g55019.atl.asciipaint.DPComposite;

import esi.g55019.atl.asciipaint.Point;
import esi.g55019.atl.asciipaint.Shape;


public class Leaf extends Component{
    private Shape shape;

    public Leaf(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void move(double dx, double dy) {
        shape.move(dx, dy);
    }

    @Override
    public boolean isInside(Point p) {
        return shape.isInside(p);
    }

    @Override
    public char getColor() {
        return shape.getColor();
    }

    @Override
    public void changeColor(char color) {
        shape.changeColor(color);
    }

    @Override
    public String toString() {
        return shape.toString();
    }


}
