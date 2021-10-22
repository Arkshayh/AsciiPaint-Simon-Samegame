package esi.g55019.atl.asciipaint;


import esi.g55019.atl.asciipaint.ShapePackage.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Cotton Ian, g55019
 */
public class CircleTest {
    private Circle circle;

    @BeforeEach
    public void setUp() {
        circle = new Circle(new Point(10, 10), 5, 'c');
    }

    @Test
    public void radius_too_small() {
        assertThrows(IllegalArgumentException.class,
                () -> new Circle(new Point(1, 10), 0, 'c'));
    }

    @Test
    public void is_inside_true() {
        assertTrue(circle.isInside(new Point(15, 10)));
    }

    @Test
    public void is_inside_false() {
        assertFalse(circle.isInside(new Point(20, 20)));
    }

}
