package esi.g55019.atl.simon.Model;

import esi.g55019.atl.simon.util.Observable;
import esi.g55019.atl.simon.util.Observer;

import java.util.ArrayList;
import java.util.List;

public class Model implements Observable {
    private List<Observer> listObserver;
    private State state;
    private List<Color> listStartColor;
    private List<Color> listLonguest;
    private List<Color> listLast;

    /**
     * Constructor
     */
    public Model(){
        listObserver = new ArrayList<>();
        state = State.ON_THE_MENU;
        listStartColor = new ArrayList<>();
        listLonguest = new ArrayList<>();
        listLast = new ArrayList<>();
        notifyObservers();
    }

    public List<Color> getStartColor() {
        return listStartColor;
    }

    public List<Color> getListLonguest() {
        return listLonguest;
    }

    public List<Color> getListLast() {
        return listLast;
    }

    private Color addColor(){
        int random = 1 + (int)(Math.random() * ((4 - 1) + 1));
        switch (random){
            case 1:
                return Color.GREEN;
            case 2:
                return Color.RED;
            case 3:
                return Color.YELLOW;
            default:
                return Color.BLUE;
        }
    }

    public void addToStartList(){
        listStartColor.add(addColor());
    }

    public void updateLastList(List<Color>  liste){
        List<Color> nouvelleListe = new ArrayList<>();
        for (int i = 0; i < liste.size(); i++) {
            nouvelleListe.add(liste.get(i));
        }
        listLast = nouvelleListe;
    }

    public void listLonguest(List<Color>  liste){
        List<Color> nouvelleListe = new ArrayList<>();
        for (int i = 0; i < liste.size(); i++) {
            nouvelleListe.add(liste.get(i));
        }
        listLonguest = nouvelleListe;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    @Override
    public void addObserver(Observer observer) {
        listObserver.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listObserver.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < listObserver.size(); i++) {
            listObserver.get(i).update(state);
        }
    }
}
