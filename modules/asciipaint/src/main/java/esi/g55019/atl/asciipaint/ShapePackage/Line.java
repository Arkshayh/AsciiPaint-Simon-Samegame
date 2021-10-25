package esi.g55019.atl.asciipaint.ShapePackage;

import esi.g55019.atl.asciipaint.ColoredShape;
import esi.g55019.atl.asciipaint.Point;

public class Line extends ColoredShape {
    private double pente;
    private Point point1;
    private Point point2;

    public Line(Point point1, Point point2, char color){
        super(color);
        this.point1 = point1;
        this.point2 = point2;
        pente = calculerPente();
    }

    private double calculerPente(){
        return  ((point2.getY() - point1.getY()) / (point2.getX() - point1.getX()));
    }


    private double distanceTo(Point pointC){
        return (pente * pointC.getX() - pointC.getY() - pente * point1.getX() + point1.getY()) /
                (Math.sqrt(Math.pow(pente,2)) + 1);
    }

    public boolean isInside(Point pointC){
        return distanceTo(pointC) == 0;
    }

    @Override
    public void move(double dx, double dy) {
        this.point1.move(dx, dy);
        this.point2.move(dx, dy);
        this.pente = calculerPente();
    }

    @Override
    public String toString() {
        return "Line [" +
                "pente = " + pente +
                " | point1 = " + point1 +
                " | point2 = " + point2 +
                ']';
    }
}
