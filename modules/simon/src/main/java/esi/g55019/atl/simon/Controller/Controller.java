package esi.g55019.atl.simon.Controller;

import esi.g55019.atl.simon.Model.Color;
import esi.g55019.atl.simon.Model.Model;
import esi.g55019.atl.simon.Model.State;
import esi.g55019.atl.simon.View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Controller extends Application {
    private Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this, model);
    }

    public void startOnClick(){
        model.setState(State.AFFICHAGE_START);
        model.addToStartList();
        playDisplayStartSequence();
    }

    public void colorButtonOnClick(Color color){
        addOnListeActuelle(color);
        fullCheck();
    }

    private void fullCheck(){
        //Si bon bouton choisi
        if(model.checkCorrectColor()){
            if(model.aRattraper()){
                model.clearListPlayer();
                model.setState(State.AFFICHAGE_START);
                model.addToStartList();
                playDisplayStartSequence();
            }
        }
        else{
            System.out.println("PERDU");
            model.setState(State.ON_THE_MENU);
            System.out.println("Controller fullcheck, UPDATE DE LASTLIST");
            model.updateLastList();
            if(model.getListStartColor().size() > model.getListLonguest().size()){
                System.out.println("Controller fullcheck, UPDATE DE LONGUESTLIST");
                model.setListLonguest();
            }
            model.clearStartListAndPlayerList();
        }
    }

    private void playDisplayStartSequence(){
        view.playSequenceColor(model.getListStartColor());
    }

    public void addOnListeActuelle(Color color){
        model.addListeActuelle(color);
    }


    @Override
    public void start(Stage primaryStage) {
        view.start(primaryStage);
        model.notifyObservers();
    }
}
