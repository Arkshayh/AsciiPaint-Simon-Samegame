package esi.g55019.atl.asciipaint.DPComposite;

import esi.g55019.atl.asciipaint.Point;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component {
    private List<Component> componentList;

    public Composite() {
        componentList = new ArrayList<>();
    }
    public Composite(Component component) {
        componentList = new ArrayList<>();
        componentList.add(component);
    }

    public int getSize(){
        return componentList.size();
    }

    public void add(Component shape){
        componentList.add(shape);
    }

    public void remove(Component shape){
        componentList.remove(shape);
    }

    public List<Component> getChildren() {
        return componentList;
    }

    @Override
    public void move(double dx, double dy) {
        for (int i = 0; i < componentList.size(); i++) {
            componentList.get(0).move(dx, dy);
        }
    }

    @Override
    public boolean isInside(Point p) {
        for (int i = 0; i < componentList.size(); i++) {
            if(componentList.get(i).isInside(p)){
                return true;
            }
        }
        return false;
    }

    @Override
    public char getColor() {
        return componentList.get(0).getColor();
    }

    //TODO demander au prof comment faire
    /*public void setColor(char color){
        if(componentList.get(0) instanceof Leaf){
            ((Leaf) componentList.get(0)).setColor(color);
        }else{
            ((Composite) componentList.get(0)).setColor(color);
        }
    }*/

    @Override
    public String toString() {
        return "Groupe";
    }
}
