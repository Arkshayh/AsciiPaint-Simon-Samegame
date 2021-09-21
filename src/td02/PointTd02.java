package td02;

import td01.Point;

public class PointTd02 {
    private double x;
    private double y;

    public PointTd02(double x, double y){
        this.x = x;
        this.y =y;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public PointTd02 move(double dx, double dy) {
        x += dx;
        y += dy;
        return this;
    }

    @Override
    public String toString(){
        return  "("+x+", "+y+")";
    }
}
