package asciipaint;

/**
 * @author Cotton Ian, g55019
 * the rectangle class defines a rectangle using a point, its width, height and color.
 * This class extends the ColorShaped abstract class
 */

public class Rectangle extends ColoredShape {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor of a rectangle
     * @param upperLeft Point
     * @param width double
     * @param height double
     * @param color char
     * @throws IllegalArgumentException if the width/height <= 0
     */
    public Rectangle(Point upperLeft, double width, double height, char color) {
        super(color);
        if(height <= 0 || width <= 0){
            throw new IllegalArgumentException("hauteur ou largeur invalide ! Hauteur : "
                    + height + " Largeur : " + width);
        }

        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * changes the position of the rectangle, this is defined by its upperleft attribute,
     * this method will therefore move this point according to the coordinates given in parameter
     * @param dx double
     * @param dy double
     */
    @Override
    public void move(double dx, double dy) {
        this.upperLeft.move(dx, dy);
    }

    /**
     * return a boolean : false if the point given in parameter is not consider inside the rectangle, $
     * true if it's the case
     * @param p Point
     * @return boolean
     */
    @Override
    public boolean isInside(Point p) {
        double xMin = this.upperLeft.getX();
        double xMax = this.upperLeft.getX() + this.width -1;
        double yMax = this.upperLeft.getY();
        double yMin = this.upperLeft.getY() - this.height + 1;

        if(p.getX() > xMax || p.getX() < xMin || p.getY() > yMax || p.getY() < yMin){
            return false;
        }

        return true;
    }

}

