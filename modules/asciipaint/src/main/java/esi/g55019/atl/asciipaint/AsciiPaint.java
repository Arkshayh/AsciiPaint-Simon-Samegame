package esi.g55019.atl.asciipaint;

import esi.g55019.atl.asciipaint.DPComposite.Composite;
import esi.g55019.atl.asciipaint.DPComposite.Leaf;
import esi.g55019.atl.asciipaint.ShapePackage.Circle;
import esi.g55019.atl.asciipaint.ShapePackage.Line;
import esi.g55019.atl.asciipaint.ShapePackage.Rectangle;
import esi.g55019.atl.asciipaint.ShapePackage.Square;

/**
 * @author Cotton Ian g55019
 * This class has the methods allowing to add shapes to the drawings
 * as well as to return the drawing as a string
 * (No test for this class bc there is nothing to test)
 */

public class AsciiPaint {

    private Drawing drawing;
    private boolean end;

    /**
     * Constructor when you choose the dimension of the drawing
     * @param drawing
     */
    public AsciiPaint(Drawing drawing) {
        this.drawing = drawing;
        this.end = false;
    }

    /**
     * default constructor, the dimension of the drawing will be 50x50
     */
    public AsciiPaint() {
        drawing = new Drawing();
    }

    /**
     * add a circle to the drawing
     * @param x      int
     * @param y      int
     * @param radius double
     * @param color  char
     */
    public void newCircle(int x, int y, double radius, char color) {
        drawing.addShape(new Leaf(new Circle(new Point(x, y), radius, color)));
    }

    /**
     * add a rectangle to the drawing
     * @param x      int
     * @param y      int
     * @param width  double
     * @param height double
     * @param color  char
     */
    public void newRectangle(int x, int y, double width, double height, char color) {
        drawing.addShape(new Leaf(new Rectangle(new Point(x, y), width, height, color)));
    }

    /**
     * add a square to the drawing
     * @param x     int
     * @param y     int
     * @param side  double
     * @param color char
     */
    public void newSquare(int x, int y, double side, char color) {
        drawing.addShape(new Leaf(new Square(new Point(x, y), side, color)));
    }

    public void newLine(int x1, int y1, int x2, int y2, char color){
        drawing.addShape(new Leaf(new Line(new Point(x1,y1), new Point(x2,y2), color)));
    }

    public void newGroup(int shape1, int shape2){
        Composite groupe = new Composite();
        groupe.add(new Leaf(drawing.getShapeInList(shape1)));
        groupe.add(new Leaf(drawing.getShapeInList(shape2)));
        drawing.addShape(groupe);
    }

    /**
     * Return the drawing
     * @return String as a string.
     */
    public String asAscii() {
        String monstring = "";

        for (int i = drawing.getHeight(); i >= 0; i--) {
            for (int j = 0; j < drawing.getWidth(); j++) {
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

    public void showList(){
        drawing.showList();
    }

    public void move(int numberOfTheShape, int dx, int dy){
        drawing.move(numberOfTheShape, dx, dy);
    }

    public int nbForme(){
        return drawing.getSize();
    }

    public void end(){
        this.end = true;
    }

    public boolean getEnd(){
        return end;
    }
}

