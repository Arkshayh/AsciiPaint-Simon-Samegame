package esi.g55019.atl.asciipaint.ShapePackage;

import esi.g55019.atl.asciipaint.ColoredShape;
import esi.g55019.atl.asciipaint.Point;

/**
 * This class represent a Line.
 * A line need 2 point and a color to be created
 */
public class Line extends ColoredShape {
    private double pente;
    private Point point1;
    private Point point2;

    /**
     * constructor
     * @param point1    Point
     * @param point2    Point
     * @param color char
     */
    public Line(Point point1, Point point2, char color){
        super(color);
        this.point1 = point1;
        this.point2 = point2;
        pente = calculerPente();
    }

    /**
     * return the slope of a line
     * @return
     */
    private double calculerPente(){
        return  ((point2.getY() - point1.getY()) / (point2.getX() - point1.getX()));
    }

    /**
     * calculate the distance between a point and the line
     * @param pointC    Point
     * @return  double
     */
    private double distanceTo(Point pointC){
        return (pente * pointC.getX() - pointC.getY() - pente * point1.getX() + point1.getY()) /
                (Math.sqrt(Math.pow(pente,2)) + 1);
    }

    /**
     * Say if a point is inside the line
     * @param pointC
     * @return
     */
    public boolean isInside(Point pointC){
        return distanceTo(pointC) == 0;
        // @pbt prefers < .5
    }

    /**
     * move the line
     * @param dx double
     * @param dy double
     */
    @Override
    public void move(double dx, double dy) {
        this.point1.move(dx, dy);
        this.point2.move(dx, dy);
        this.pente = calculerPente();
    }

    @Override
    public String toString() {
        return "Line";
    }

    /**
     * change the color of the line
     * @param color char
     */
    @Override
    public void changeColor(char color) {
        setColor(color);
    }
}
