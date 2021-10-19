package esi.g55019.atl.simon.Controller;

import esi.g55019.atl.simon.Model.Color;
import esi.g55019.atl.simon.Model.Model;
import esi.g55019.atl.simon.Model.State;
import esi.g55019.atl.simon.View.View;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * The controller of the game, the controller will be created in the main class and will create the view
 * and need a model.
 * Every click on a button will be send to the controller who will decide which action do depending on the situation
 * @author Ian Cotton | g55019
 */
public class Controller extends Application {
    private Model model;
    private View view;

    /**
     * Constructor, create a view
     * @param model Model
     */
    public Controller(Model model) {
        this.model = model;
        this.view = new View(this, model);
    }

    /**
     * this function is call when the player click on the start button.
     * the function will change the state of the model to AFFICHE_START and add a color to the start
     * list then display her for the player
     */
    public void startOnClick(){
        model.setState(State.AFFICHAGE_START);
        model.addToStartList();
        playDisplayStartSequence();
    }

    /**
     * this function is call when the player click on the last button.
     * if there is no value in the last list a popup is display and nothing else append
     * if there is at least one value in the list the function will change the state to AFFICHE_START
     * of the model then copie the value in the last list and paste them in the start list and after
     * display her to the player
     */
    public void lastOnClick(){
        if(model.getListLast().size() == 0){
            displayAlert("Pas de dernière liste");
        }
        else{
            model.setState(State.AFFICHAGE_START);
            for (int i = 0; i < model.getListLast().size(); i++) {
                model.addToStartListFromAnotherList(model.getListLast().get(i));
            }
            playDisplayStartSequence();
        }
    }

    /**
     * Display a popup with the message given
     * @param message String
     */
    private void displayAlert(String message){
        Alert alertlast = new Alert(Alert.AlertType.ERROR);
        alertlast.setTitle(message);
        alertlast.setContentText(message);
        alertlast.showAndWait();
    }

    /**
     * Display a popup who say PERDU
     */
    private void displayLose(){
        Alert alertlast = new Alert(Alert.AlertType.INFORMATION);
        alertlast.setTitle("Perdu");
        alertlast.setContentText("Perdu");
        alertlast.showAndWait();
    }

    /**
     * this function is call when the player click on the longuest button.
     * if there is no value in the longuest list a popup is display and nothing else append
     * if there is at least one value in the list the function will change the state to AFFICHE_START
     * of the model then copie the value in the longuest list and paste them in the start list and after
     * display her to the player
     */
    public void longuestOnClick(){
        if(model.getListLonguest().size() == 0){
            displayAlert("Pas de longuest liste");
        }
        else{
            model.setState(State.AFFICHAGE_START);
            for (int i = 0; i < model.getListLonguest().size(); i++) {
                model.addToStartListFromAnotherList(model.getListLonguest().get(i));
            }
            playDisplayStartSequence();
        }
    }

    /**
     * called when the player clicked on a color button, it will add the color of the button to the list of the player
     * then called the fullcheck function
     * @param color Color
     */
    public void colorButtonOnClick(Color color){
        addOnListeActuelle(color);
        fullCheck();
    }

    /**
     * check if the player has clicked on the right button, if it's the case it will check called aRattraper function
     * if the return is true the list of the plauer his clear the state become AFFICHAGE_START, a new color is add to
     * the start list and then each color of the list is display for the player
     * If the player has clicked on the wrong button a popup appear then the list of the player is clear and the last
     * list is updated, if the start list is bigger than the longuest list, the update list became the start list then
     * the start list is clear. In the end, the state become ON_THE_MENU
     */
    private void fullCheck(){
        if(model.checkCorrectColor()){
            if(model.aRattraper()){
                model.clearListPlayer();
                model.setState(State.AFFICHAGE_START);
                model.addToStartList();
                playDisplayStartSequence();
            }
        }
        else{
            displayLose();
            model.setState(State.ON_THE_MENU);
            model.updateLastList();
            if(model.getListStartColor().size() > model.getListLonguest().size()){
                model.setListLonguest();
            }
            model.clearStartListAndPlayerList();
        }
    }

    /**
     * called the method playSequenceCOlor of the view and give to it the start list
     */
    private void playDisplayStartSequence(){
        view.playSequenceColor(model.getListStartColor());
    }

    /**
     * add a color to the list of the player
     * @param color
     */
    public void addOnListeActuelle(Color color){
        model.addListeActuelle(color);
    }

    /**
     * the method to start the game
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        view.start(primaryStage);
        model.notifyObservers();
    }
}
