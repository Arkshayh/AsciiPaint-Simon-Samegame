package asciipaint;

public interface Shape {

    void move(double dx, double dy);

    boolean isInside(Point p);

    char getColor();

}
