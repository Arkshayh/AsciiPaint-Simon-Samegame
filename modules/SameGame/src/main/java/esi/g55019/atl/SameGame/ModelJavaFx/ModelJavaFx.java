package esi.g55019.atl.SameGame.ModelJavaFx;

import esi.g55019.atl.SameGame.Model.State;
import esi.g55019.atl.SameGame.util.Observable;
import esi.g55019.atl.SameGame.util.Observer;

import java.util.ArrayList;
import java.util.List;

public class ModelJavaFx implements Observable {
    private State state;
    private List<Observer> listObserver;


    public ModelJavaFx() {
        this.state = State.ON_THE_MENU;
        listObserver = new ArrayList<>();
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
