package test;

import asciipaint.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author Ian Cotton | g55019
 */

public class PointTest {
    private Point point;

    @BeforeEach
    public void setUp(){
        point = new Point(10,10);
    }

    @Test
    public void test_distance_to_second_point_bigger_XY(){
        Point test = new Point(50,50);
        assertEquals(56, (int)point.distanceTo(test));
    }

    @Test
    public void test_distance_to_second_point_smaller_XY(){
        Point test = new Point(-10,-10);
        assertEquals(28, (int)point.distanceTo(test));
    }

    @Test
    public void test_distance_to_same_point(){
        assertEquals(0,(int)point.distanceTo(point));
    }

}
