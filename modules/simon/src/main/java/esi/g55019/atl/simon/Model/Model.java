package esi.g55019.atl.simon.Model;

import esi.g55019.atl.simon.util.Observable;
import esi.g55019.atl.simon.util.Observer;

import java.util.ArrayList;
import java.util.List;

public class Model implements Observable {
    private List<Observer> listObserver;

    /**
     * Constructor
     */
    public Model(){
        listObserver = new ArrayList<>();
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
            listObserver.get(i).update();
        }
    }
}
