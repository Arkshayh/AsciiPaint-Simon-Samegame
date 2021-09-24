package asciipaint;

import java.util.ArrayList;
import java.util.List;

public class Drawing {

    private List<Shape> shapes;
    private int height;
    private int width;

    public Drawing() {
    }

    public Drawing(int height, int width) {
        this.height = height;
        this.width = width;
        this.shapes = new ArrayList<>();
    }

    public void addShape(Shape shape){
        shapes.add(shape);
    }

    public Shape getShapeAt(Point p){
        for (int i = 0; i < shapes.size(); i++) {
            if(shapes.get(i).isInside(p) == true){
                return shapes.get(i);
            }
        }
        return null;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


}
