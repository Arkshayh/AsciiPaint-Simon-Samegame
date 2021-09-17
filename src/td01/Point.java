package td01;

public class Point {

    private double x;
    private double y;

    public Point(double x,double y){
        this.x = x;
        this.y = y;
    }

    public Point(){
        this.x =0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "("+x+", "+y+")";
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }


}
class TestPoint{
    public static void main(String[] args) {
        Point p = new Point();
        System.out.println(p);
        p.move(2,2);
        System.out.println(p);
    }


}
