package esi.g55019.atl.asciipaint.DPComposite;

import esi.g55019.atl.asciipaint.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * A composite represent a group of shape (Leaf). It can also be considered as a list of shape (Leaf)
 * @author Ian Cotton | g55019
 */
public class Composite extends Component {
    private List<Component> componentList;

    /**
     * Constructor
     */
    public Composite() {
        componentList = new ArrayList<>();
    }

    /**
     * constructor 2
     * @param component Component
     */
    public Composite(Component component) {
        componentList = new ArrayList<>();
        componentList.add(component);
    }

    /**
     * return the number of Component in the componentList (the number of Component of the group)
     * @return int
     */
    public int getSize(){
        return componentList.size();
    }

    /**
     * add a Component to the group (the list)
     * @param shape
     */
    public void add(Component shape){
        componentList.add(shape);
    }

    /**
     * return the list of the component of the group
     * @return List<Component>
     */
    public List<Component> getChildren() {
        return componentList;
    }

    /**
     * Move each Component of the group
     * @param dx double
     * @param dy double
     */
    @Override
    public void move(double dx, double dy) {
        for (int i = 0; i < componentList.size(); i++) {
            componentList.get(0).move(dx, dy);
        }
    }

    /**
     * return true if the given point is inside a Component of the group otherwise false
     * @param p Point
     * @return
     */
    @Override
    public boolean isInside(Point p) {
        for (int i = 0; i < componentList.size(); i++) {
            if(componentList.get(i).isInside(p)){
                return true;
            }
        }
        return false;
    }

    /**
     * return the color of the group (the color of the first Component)
     * @return char
     */
    @Override
    public char getColor() {
        return componentList.get(0).getColor();
    }

    /**
     * Change the color of each Componenet of the group
     * @param color char
     */
    @Override
    public void changeColor(char color) {
        for (int i = 0; i < getSize(); i++) {
            componentList.get(i).changeColor(color);
        }
    }

    @Override
    public String toString() {
        return "Groupe";
    }
}
