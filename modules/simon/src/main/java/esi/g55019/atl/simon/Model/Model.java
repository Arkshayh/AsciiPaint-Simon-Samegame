package esi.g55019.atl.simon.Model;

import esi.g55019.atl.simon.util.Observable;
import esi.g55019.atl.simon.util.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * the model of the game. Model implement Observable and each time that its state change every Observer in its list
 * will be notified of its new state. A model has 4 list : the list of the player, the start list (the color that the
 * player must remember, and the last list (the color of the last game) and finally the longuest list (the bigger list
 * of color that the player must have remembered).
 */
public class Model implements Observable {
    private List<Observer> listObserver;
    private State state;
    private List<Color> listStartColor;
    private List<Color> listLonguest;
    private List<Color> listLast;
    private List<Color> listeActuelleJoueur;
    private int index;
    private Timer timer;

    /**
     * Constructor for a Model, the default state is ON_THE_MENU.
     * Create 4 empty list and set the index to -1
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

    /**
     * check if the player has clicked on the correct button
     * @return
     */
    public boolean checkCorrectColor(){
        return listStartColor.get(index) == listeActuelleJoueur.get(index);
    }

    /**
     * check if the player has clicked on each button of the start list
     * @return
     */
    public boolean aRattraper(){
        return listeActuelleJoueur.size() == listStartColor.size();
    }

    /**
     * return a random color (GREEN RED YELLOW OR BLUE)
     * @return color Color
     */
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

    /**
     * clear the start list and the player list
     */
    public void clearStartListAndPlayerList(){
        listStartColor.clear();
        clearListPlayer();
        index = -1;
    }

    /**
     * clear only the list of the player
     */
    public void clearListPlayer(){
        listeActuelleJoueur.clear();
        index = - 1;
    }

    /**
     * add a color the the list of the player
     * @param color Color
     */
    public void addListeActuelle(Color color){
        listeActuelleJoueur.add(color);
        index++;
    }

    /**
     * add a random color to the start list
     */
    public void addToStartList(){
        listStartColor.add(addColor());
    }

    /**
     * add to the start list a color from another list
     * @param color
     */
    public void addToStartListFromAnotherList(Color color){
        listStartColor.add(color);
    }

    /**
     * create a new Arraylist for the last list and copie the value of the start list to it
     */
    public void updateLastList(){
        List<Color> nouvelleListe = new ArrayList<>();
        for (int i = 0; i < listStartColor.size(); i++) {
            nouvelleListe.add(listStartColor.get(i));
        }
        listLast = nouvelleListe;
    }

    /**
     * create a new Arraylist for the longuest list and copie the value of the start list to it
     */
    public void setListLonguest(){
        List<Color> nouvelleListe = new ArrayList<>();
        for (int i = 0; i < listStartColor.size(); i++) {
            nouvelleListe.add(listStartColor.get(i));
        }
        listLonguest = nouvelleListe;
    }

    /**
     * change the state of the model and notify each Observer in the Observer list
     * @param state State
     */
    public void setState(State state) {
        this.state = state;
        notifyObservers();
    }

    public void setTimer(TimerTask timerTask) {
        this.timer = new Timer();
        timer.schedule(timerTask, 10000);
    }

    /**
     * getter for the timer
     * @return timer Timer
     */
    public Timer getTimer(){
        return timer;
    }

    /**
     * add an observer to the observerlist
     * @param observer The observer to be added.
     */
    @Override
    public void addObserver(Observer observer) {
        listObserver.add(observer);
    }

    /**
     * remove an observer from the observer list
     * @param observer The  observer to be removed.
     */
    @Override
    public void removeObserver(Observer observer) {
        listObserver.remove(observer);
    }

    /**
     * send the state of the model to every observer
     */
    @Override
    public void notifyObservers() {
        for (int i = 0; i < listObserver.size(); i++) {
            listObserver.get(i).update(state);
        }
    }
}
