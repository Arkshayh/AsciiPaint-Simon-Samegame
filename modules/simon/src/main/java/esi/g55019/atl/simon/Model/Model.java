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
    private List<Color> listeActuelleJoueur;
    private int index;

    /**
     * Constructor for a Model, the default state is ON_THE_MENU
     */
    public Model(){
        listObserver = new ArrayList<>();
        this.state = State.ON_THE_MENU;
        listStartColor = new ArrayList<>();
        listLonguest = new ArrayList<>();
        listLast = new ArrayList<>();
        listeActuelleJoueur = new ArrayList<>();

        index = -1;
    }

    /**
     * Getter for the different list
     * @return List Color
     */
    public List<Color> getListStartColor() {
        return listStartColor;
    }
    public List<Color> getListLonguest() {
        return listLonguest;
    }
    public List<Color> getListLast() {
        return listLast;
    }
    public List<Color> getListeActuelle(){
        return listeActuelleJoueur;
    }

    public boolean checkCorrectColor(){
        return listStartColor.get(index) == listeActuelleJoueur.get(index);
    }

    public boolean aRattraper(){
        return listeActuelleJoueur.size() == listStartColor.size();
    }

    public Color addColor(){
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

    public void clearStartListAndPlayerList(){
        listStartColor.clear();
        clearListPlayer();
        index = -1;
    }

    public void clearListPlayer(){
        listeActuelleJoueur.clear();
        index = - 1;
    }

    public void addListeActuelle(Color color){
        listeActuelleJoueur.add(color);
        index++;
    }

    public void addToStartList(){
        listStartColor.add(addColor());
    }

    public void addToStartListFromAnotherList(Color color){
        listStartColor.add(color);
    }

    public void updateLastList(){
        List<Color> nouvelleListe = new ArrayList<>();
        for (int i = 0; i < listStartColor.size(); i++) {
            nouvelleListe.add(listStartColor.get(i));
        }
        listLast = nouvelleListe;
    }

    public void setListLonguest(){
        List<Color> nouvelleListe = new ArrayList<>();
        for (int i = 0; i < listStartColor.size(); i++) {
            nouvelleListe.add(listStartColor.get(i));
        }
        listLonguest = nouvelleListe;
    }

    public void setState(State state) {
        this.state = state;
        notifyObservers();
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
