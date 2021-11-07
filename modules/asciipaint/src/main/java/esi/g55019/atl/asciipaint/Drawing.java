package esi.g55019.atl.asciipaint;

import esi.g55019.atl.asciipaint.DPComposite.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ian Cotton g55019
 * This class contains all the shapes created as well as the dimensions that the drawing will have
 */

public class Drawing {

    private List<Component> shapes;
    private int height;
    private int width;

    /**
     * default constructor
     */
    public Drawing() {
        this.height = 50;
        this.width = 50;
        this.shapes = new ArrayList<>();
    }

    /**
     * constructor
     * @param height int
     * @param width  int
     */
    public Drawing(int height, int width) {
        this.height = height;
        this.width = width;
        this.shapes = new ArrayList<>();
    }

    /**
     * add to the drawing the given shape
     * @param shape Shape
     */
    public void addShape(Component shape) {
        shapes.add(shape);
    }

    public void removeShape(Component shape){
        shapes.remove(shape);
    }

    /**
     * Returns the first form containing the point given in parameter,
     * if no form contains this point then this method returns null
     * @param p Point
     * @return Shape or null
     */
    public Shape getShapeAt(Point p) {
        for (Shape shape : shapes) {
            if (shape.isInside(p)) {
                return shape;
            }
        }
        return null;
    }

    /**
     * getter height
     * @return int
     */
    public int getHeight() {
        return height;
    }

    /**
     * getter width
     * @return int
     */
    public int getWidth() {
        return width;
    }

    /**
     * display each component of the drawing
     */
    public void showList(){
        if(shapes.size() == 0){
            System.out.println("Pas de forme");
            return;
        }
        for (int i = 0; i <shapes.size(); i++) {
            System.out.println((i+1) + " : " + shapes.get(i));
        }
    }

    /**
     * move the component at the position given
     * @param numberOfTheShape  int
     * @param dx    int
     * @param dy    int
     */
    public void move(int numberOfTheShape, int dx, int dy){
        int shapeToMove = numberOfTheShape - 1;
        if(shapeToMove <0 || shapeToMove >= shapes.size()){
            System.out.println("La forme choisie n'existe pas");
        }
        else{
            shapes.get(shapeToMove).move(dx, dy);
        }
    }

    /**
     * return the number of shape of the drawing
     * @return int
     */
    public int getSize(){
        return shapes.size();
    }

    /**
     * return the component at the given index
     * @param index
     * @return
     */
    public Component getShapeInList(int index){
        return shapes.get(index);
    }

    /**
     * change the color of the component at the given index
     * @param index int
     * @param color char
     */
    public void changeColor(int index, char color){
        shapes.get(index).changeColor(color);
    }
}
