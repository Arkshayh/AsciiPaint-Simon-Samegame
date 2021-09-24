package td02;

import td01.PointTD01;

public class ColoredPoint extends PointTd02 {

    private int color;

    public ColoredPoint(double x, double y, int color) {
        super(x, y);
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @Override
     public String toString() {
        return super.toString() + " - "+ String.format("%08X", color);
    }

}
