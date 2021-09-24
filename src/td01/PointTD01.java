package td01;

public class PointTD01 {

    private double x;
    private double y;

    public PointTD01(double x,double y){
        this.x = x;
        this.y = y;
    }

    public PointTD01(){
        this.x =0;
        this.y = 0;
    }

    public PointTD01(PointTD01 p) {
        this(p.x, p.y);
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
        PointTD01 p = new PointTD01();
        System.out.println(p);
        p.move(2,2);
        System.out.println(p);
    }


}
