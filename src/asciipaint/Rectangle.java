package asciipaint;

public class Rectangle extends ColoredShape {

    private Point upperLeft;
    private double width;
    private double height;

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

    @Override
    public void move(double dx, double dy) {
        this.upperLeft.move(dx, dy);
    }

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

