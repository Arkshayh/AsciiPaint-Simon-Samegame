package td02;

import java.awt.*;

public class TestPointTD02 {
    public static void main(String[] args) {
        Object p3 = new PointTd02(2, 4);
        Object p4 = new ColoredPoint(2, 4, 0xFF0000FF);
        ColoredPoint p = new ColoredPoint(1,2,0xFF0000FF);
        p.hashCode();

        p.move(1,2);
        System.out.println(p);
        System.out.println("x : " + p.getX());
        System.out.println("color " + String.format("%08X", p.getColor()));
    }
}
