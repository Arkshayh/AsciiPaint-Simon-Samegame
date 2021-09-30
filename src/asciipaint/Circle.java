package asciipaint;

/**
 * @author Cotton Ian, g55019
 * a circle is a shape has a point (its center), a radius and a color
 */

public class Circle extends ColoredShape{

    private double radius;
    private Point center;

    /**
     * constructor of a circle
     * @param center Point
     * @param radius double
     * @param color char
     * @throws IllegalArgumentException if the radius is smaller or equals to 0
     */
    public Circle (Point center , double radius, char color){
        super(color);
        if(radius <= 0){
            throw new IllegalArgumentException("radius must be positive" + ", received: " + radius);
        }
        this.radius = radius;
        this.center = new Point(center);
    }

    /**
     * changes the position of the circle, this is defined by its center attribute,
     * this method will therefore move this point according to the coordinates given in parameter
     * @param dx double
     * @param dy double
     */
    @Override
    public void move(double dx, double dy) {
        this.center.move(dx, dy);
    }

    /**
     * check if the given point is inside the circle if not -> return false otherwise true
     * @param p Point
     * @return boolean
     */
    @Override
    public boolean isInside(Point p) {
        if(p.distanceTo(center) <= radius){
            return true;
        }
        return false;
    }
}
