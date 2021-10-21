package esi.g55019.atl.asciipaint;

/**
 * @author Cotton Ian g55019
 * This class has the methods allowing to add shapes to the drawings
 * as well as to return the drawing as a string
 * (No test for this class bc there is nothing to test)
 */

public class AsciiPaint {

    private Drawing drawing;

    /**
     * Constructor when you choose the dimension of the drawing
     *
     * @param drawing
     */
    public AsciiPaint(Drawing drawing) {
        this.drawing = drawing;
    }

    /**
     * default constructor, the dimension of the drawing will be 50x50
     */
    public AsciiPaint() {
        drawing = new Drawing();
    }

    /**
     * add a circle to the drawing
     *
     * @param x      int
     * @param y      int
     * @param radius double
     * @param color  char
     */
    public void newCircle(int x, int y, double radius, char color) {
        drawing.addShape(new Circle(new Point(x, y), radius, color));
    }

    /**
     * add a rectangle to the drawing
     *
     * @param x      int
     * @param y      int
     * @param width  double
     * @param height double
     * @param color  char
     */
    public void newRectangle(int x, int y, double width, double height, char color) {
        drawing.addShape(new Rectangle(new Point(x, y), width, height, color));
    }

    /**
     * add a square to the drawing
     *
     * @param x     int
     * @param y     int
     * @param side  double
     * @param color char
     */
    public void newSquare(int x, int y, double side, char color) {
        drawing.addShape(new Square(new Point(x, y), side, color));
    }

    /**
     * Return the drawing
     *
     * @return String as a string.
     */
    public String asAscii() {
        String monstring = "";

        for (int i = drawing.getHeight(); i >= 0; i--) {
            for (int j = 0; j < drawing.getWidth(); j++) {
                // @pbt better to use local var in place of 2 calls to
                // getshapeat and 2 contructions of some point
                if (drawing.getShapeAt(new Point(j, i)) != null) {
                    monstring += (" " + (drawing.getShapeAt(new Point(j, i)).getColor()) + " ");
                } else {
                    monstring += (" . ");
                }
            }
            monstring += ("\n");
        }

        return monstring;
    }
}

