package td01;

public class Circle {
    private double radius;
    private PointTD01 center;
    public Circle (PointTD01 center , double radius){
        if(radius <= 0){
            throw new IllegalArgumentException("radius must be positive" + ", received: " + radius);
        }
        this.radius = radius;
        this.center = new PointTD01(center);
    }

    public void move(double dx, double dy){
        center.move(dx, dy);
    }

    public double area(){
        return Math.PI * radius *radius;

    }

    public PointTD01 getCenter(){
        return new PointTD01(center);  //copie défensive
    }

    public void scale(double factor){
        if(factor <= 0){
            throw new IllegalArgumentException("Scale factor must be positive" + ", received " + factor);
        }
        radius *= factor;
    }

    @Override
    public String toString(){
        return "Circle :[" + center + ", " +radius + "]";
    }
}

/**
 * class TestCircle{
 *     public static void main(String[] args) {
 *         Point p = new Point();
 *         Circle c = new Circle(p,5);
 *         System.out.println(c);
 *         c.move(2,5);
 *         System.out.println(c);
 *         c.scale(2);
 *         System.out.println(c);
 *     }
 * }
 */

