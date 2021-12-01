package esi.g55019.atl.SameGame.ViewJavaFx;

import esi.g55019.atl.SameGame.ControllerJavaFx.ControllerJavaFx;
import esi.g55019.atl.SameGame.ModelJavaFx.ModelJavaFx;
import esi.g55019.atl.SameGame.ModelJavaFx.State;
import esi.g55019.atl.SameGame.util.Observer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Menu implements Observer {
    private VBox vBox;
    private TextField ligne;
    private Label ligneMsg;
    private TextField colonne;
    private Label colonneMsg;
    private ComboBox difficulté;
    private Button start;
    private HBox undoRedo;
    private Button undo;
    private Button redo;
    private HBox giveUpRestart;
    private Button giveUp;
    private Button restart;
    private ControllerJavaFx controllerJavaFx;
    private boolean displayErrorMsg = false;
    private ModelJavaFx model;
    private State stateOfModel;

    public Menu(ControllerJavaFx controllerJavaFx, ModelJavaFx modelJavaFx) {
        this.model = modelJavaFx;
        model.addObserver(this);
        this.controllerJavaFx = controllerJavaFx;
        setUpMenu();
    }

    private void setUpMenu(){
        this.vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);

        ligne = new TextField();
        ligne.setPromptText("Nombre de lignes");
        vBox.getChildren().add(ligne);
        ligneMsg = new Label("Min : 5 | Max = 20");
        ligneMsg.setTextFill(Color.RED);


        colonne = new TextField();
        colonne.setPromptText("Nombre de colonnes");
        vBox.getChildren().add(colonne);
        colonneMsg = new Label("Min : 5 | Max = 20");
        colonneMsg.setTextFill(Color.RED);

        textFieldSetUp();

        Label textDifficulté = new Label("Difficulté : ");
        vBox.getChildren().add(textDifficulté);

        difficulté = new ComboBox();
        difficulté.getItems().addAll("Facile","Moyen","Difficile");
        difficulté.getSelectionModel().select(0);
        vBox.getChildren().add(difficulté);

        start = new Button("Start");
        vBox.getChildren().add(start);
        startSetUp();

        undoRedo = new HBox();
        undo = new Button("Undo");
        redo = new Button("Redo");
        undoRedo.getChildren().addAll(undo, redo);

        undoRedo.setAlignment(Pos.CENTER);
        undoRedo.setSpacing(10);
        vBox.getChildren().add(undoRedo);

        giveUpRestart = new HBox();
        giveUp = new Button("Give Up");
        restart = new Button("Restart");
        giveUpRestart.getChildren().addAll(giveUp, restart);
        giveUpRestart.setAlignment(Pos.CENTER);
        giveUpRestart.setSpacing(10);
        vBox.getChildren().add(giveUpRestart);

        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
    }

    private void textFieldSetUp(){
        var changeListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    ligne.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        };
        ligne.textProperty().addListener(changeListener);
        colonne.textProperty().addListener(changeListener);
    }

    private void startSetUp(){
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(stateOfModel == State.ON_THE_MENU){
                    if(ligne.getText().equals("") || colonne.getText().equals("")){

                    }
                    else if (Integer.parseInt(ligne.getText()) < 5 || Integer.parseInt(ligne.getText()) > 20 ||
                            Integer.parseInt(colonne.getText()) < 5 || Integer.parseInt(colonne.getText()) > 20){
                        if(!displayErrorMsg){
                            displayErrorMsg = true;
                            vBox.getChildren().add(1,ligneMsg);
                            vBox.getChildren().add(3,colonneMsg);
                        }

                    }
                    else{
                        int nbLigne = Integer.parseInt(ligne.getText());
                        int nbColonne = Integer.parseInt(colonne.getText());
                        int nbColor;
                        switch (difficulté.getValue().toString()){
                            case "Facile":
                                nbColor = 3;
                                break;
                            case "Moyen":
                                nbColor = 4;
                                break;
                            default:
                                nbColor = 5;
                                break;
                        }
                        model.setState(State.CREATION_BOARD);
                        controllerJavaFx.createBoard(nbLigne,nbColonne,nbColor);
                        undoSetUp();
                        redoSetUp();
                        start.setDisable(true);
                        controllerJavaFx.setBoardJavaFx();
                    }
                }
                else{
                    throw new IllegalStateException("Etat incorrect : Votre état : " + stateOfModel +
                            " Etat attendu : ON_THE_MENU");
                }
            }
        });
    }

    private void undoSetUp(){
        undo.setOnAction(event -> {
            if(controllerJavaFx.getUndoSize() != 0){
                controllerJavaFx.updateViewWithUndo();
            }
            else{
                System.out.println("pas de undo");
            }
        });
    }

    private void redoSetUp(){
        redo.setOnAction(event -> {
            if(controllerJavaFx.getRedoSize() != 0){
                controllerJavaFx.updateViewWithRedo();
            }
            else{
                System.out.println("pas de redo");
            }
        });
    }

    public VBox getvBox() {
        return vBox;
    }

    public TextField getLigne() {
        return ligne;
    }

    public TextField getColonne() {
        return colonne;
    }

    public ComboBox getDifficulté() {
        return difficulté;
    }

    public Button getUndo() {
        return undo;
    }

    public Button getRedo() {
        return redo;
    }

    public Button getGiveUp() {
        return giveUp;
    }

    public Button getRestart() {
        return restart;
    }

    @Override
    public void update(State state) {
        stateOfModel = state;
        if(state == State.CREATION_BOARD){
            undo.setOnAction(event -> {

            });
        }
    }
}
