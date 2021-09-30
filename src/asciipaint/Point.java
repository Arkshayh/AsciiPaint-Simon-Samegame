package asciipaint;

/**
 * @author Cotton Ian, g55019
 * This class defines a point, a point has 2 coordinates: x and y
 */

public class Point {

    private double x;
    private double y;

    /**
     * Constructor of a Point
     * @param x double
     * @param y double
     */
    public Point(double x,double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Second constructor
     * @param p Point
     */
    public Point(Point p) {
        this(p.x, p.y);
    }

    /**
     * getter for x
     * @return double
     */
    public double getX() {
        return x;
    }

    /**
     * getter for y
     * @return double
     */
    public double getY() {
        return y;
    }

    /**
     * move a point :
     * change the coordinates of this point, its x and y attributes by adding those in parameter
     * @param dx double
     * @param dy double
     */
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    /**
     * return the distance between 2 Point
     * @param b Point
     * @return double
     */
    public double distanceTo(Point b){
        double distanceX = Math.pow(b.getX() - this.getX(),2);
        double distanceY = Math.pow(b.getY() - this.getY(),2);

        return Math.sqrt(distanceX + distanceY);
    }

    /**
     * return a string of a point
     * @return String
     */
    @Override
    public String toString() {
        return "("+x+", "+y+")";
    }

}

