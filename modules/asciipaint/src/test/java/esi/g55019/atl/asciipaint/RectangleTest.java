package esi.g55019.atl.asciipaint;

import esi.g55019.atl.asciipaint.ShapePackage.Rectangle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author Ian Cotton | g55019
 */


public class RectangleTest {
    private Rectangle rectangle;

    @BeforeEach
    public void setUp(){
        rectangle = new Rectangle(new Point(10,10), 10,5,'R');
    }

    @Test
    public void width_small_or_equals_zero(){
       assertThrows(IllegalArgumentException.class,
               () -> new Rectangle(new Point(10,10),0,10,'R'));
    }

    @Test
    public void height_small_or_equals_zero(){
        assertThrows(IllegalArgumentException.class,
                () -> new Rectangle(new Point(10,10),0,10,'R'));
    }

    @Test
    public void is_inside_true(){
        assertTrue(rectangle.isInside(new Point(10,10)));
    }

    @Test
    public void is_inside_false(){
        assertFalse(rectangle.isInside(new Point(5,5)));
    }
}
