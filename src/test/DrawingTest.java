package test;

import asciipaint.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Cotton Ian, g55019
 */

public class DrawingTest {
    private Drawing drawing;

    @BeforeEach
    public void setUp(){
        drawing = new Drawing();
    }

    @Test
    public void getShapeAt_when_no_shape_at_the_point(){
        assertEquals(null,drawing.getShapeAt(new Point(10,10)));
    }

    @Test
    public void getShapeAt_when_shape_at_the_point(){
        Rectangle rec = new Rectangle(new Point(0,10),20,10,'r');
        drawing.addShape(rec);
        assertEquals(rec,drawing.getShapeAt(new Point(5,5)));
    }
}
