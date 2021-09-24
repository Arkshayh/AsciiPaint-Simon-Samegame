package asciipaint;

public class Circle extends ColoredShape{

    private double radius;
    private Point center;

    public Circle (Point center , double radius, char color){
        super(color);
        if(radius <= 0){
            throw new IllegalArgumentException("radius must be positive" + ", received: " + radius);
        }
        this.radius = radius;
        this.center = new Point(center);
    }

    @Override
    public void move(double dx, double dy) {

    }

    @Override
    public boolean isInside(Point p) {
        double distanceFromCenter = Math.sqrt( ((p.getX() - center.getX())*(p.getX() - center.getX())) +
                (p.getY() - center.getY())*(p.getY() - center.getY()));
        if(distanceFromCenter < radius){
            return true;
        }
        return false;
    }
}
