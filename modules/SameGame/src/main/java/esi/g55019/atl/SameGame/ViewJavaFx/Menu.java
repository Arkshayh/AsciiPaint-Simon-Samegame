package esi.g55019.atl.SameGame.ViewJavaFx;

import esi.g55019.atl.SameGame.ControllerJavaFx.ControllerJavaFx;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class contains every element of the menu of the javaFx app
 */
public class Menu {
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
    private Slider slider;
    private Label volume;

    /**
     * Constructor
     * @param controllerJavaFx ControllerJavaFx
     */
    public Menu(ControllerJavaFx controllerJavaFx) {
        this.controllerJavaFx = controllerJavaFx;
        setUpMenu();
    }

    /**
     * This method will add element to the menu and setUp them
     */
    private void setUpMenu(){
        this.vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);

        ligne = new TextField();
        ligne.setPromptText("Nombre de lignes");
        vBox.getChildren().add(ligne);
        ligneMsg = new Label("Min : 5 | Max = 20");
        ligneMsg.setTextFill(Color.RED);
        ligneMsg.setFont(Font.font("FONT_FAMILLY",FontWeight.BOLD,13));
        colonne = new TextField();
        colonne.setPromptText("Nombre de colonnes");
        vBox.getChildren().add(colonne);
        colonneMsg = new Label("Min : 5 | Max = 20");
        colonneMsg.setTextFill(Color.RED);
        colonneMsg.setFont(Font.font("FONT_FAMILLY",FontWeight.BOLD,13));

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

        volume = new Label("Volume : ");
        volume.setAlignment(Pos.CENTER);
        vBox.getChildren().add(volume);

        slider = new Slider();
        slider.setMax(1);
        slider.setMin(0);
        slider.setMaxSize(150,10);
        sliderSetUp();
        vBox.getChildren().add(slider);

        disableButtons();
    }

    /**
     * setUp the slider
     */
    private void sliderSetUp(){
        slider.setValue(0.5);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> controllerJavaFx.sliderOnClick(slider.getValue()));
    }

    /**
     * Set up the textField
     */
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
        ligne.setFocusTraversable(false);
        colonne.textProperty().addListener(changeListener);
        colonne.setFocusTraversable(false);
    }

    /**
     * Set up the button start
     */
    private void startSetUp(){
        start.setOnAction(actionEvent -> {
            if(checkValue()){
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
                controllerJavaFx.askCreateBoard(nbLigne,nbColonne,nbColor);
                undoSetUp();
                redoSetUp();
                giveUpSetUp();
                restartSetUp();
                start.setDisable(true);
            }
        });
    }

    /**
     * SetUp the button undo
     */
    private void undoSetUp(){
        undo.setOnAction(event -> {
            controllerJavaFx.clickOnUndo();
        });
    }

    /**
     * setUp the button redo
     */
    private void redoSetUp(){
        redo.setOnAction(event -> {
            controllerJavaFx.clickOnRedo();
        });
    }

    /**
     * setUp the button giveUp
     */
    private void giveUpSetUp(){
        giveUp.setDisable(false);
        giveUp.setOnAction(event -> {
            controllerJavaFx.clickOnGiveUp();
        });
    }

    /**
     * SetUp the button restart
     */
    private void restartSetUp(){
        restart.setDisable(true);
        restart.setOnAction(event ->{
                    if (checkValue()){
                        giveUp.setDisable(false);
                        restart.setDisable(true);
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
                        controllerJavaFx.askCreateBoard(nbLigne, nbColonne, nbColor);
                    }
        });
    }

    /**
     * Check if the value input by the user in the 2 textfield (ligne and column) are correct.
     * To be correct their value must be between 5 and 20. If they are not, a message is display under the textfield.
     */
    private boolean checkValue(){
        if(ligne.getText().equals("") || colonne.getText().equals("")){
            return false;
        }
        else if (Integer.parseInt(ligne.getText()) < 5 || Integer.parseInt(ligne.getText()) > 20 ||
                Integer.parseInt(colonne.getText()) < 5 || Integer.parseInt(colonne.getText()) > 20){
            if(!displayErrorMsg){
                displayErrorMsg = true;
                vBox.getChildren().add(1,ligneMsg);
                vBox.getChildren().add(3,colonneMsg);
            }
            return false;
        }
        return true;
    }

    /**
     * Disable the redo/undo/restart/giveUp button
     */
    private void disableButtons(){
        redo.setDisable(true);
        undo.setDisable(true);
        restart.setDisable(true);
        giveUp.setDisable(true);
    }

    /**
     * getter VBox
     * @return VBox
     */
    public VBox getvBox() {
        return vBox;
    }

    /**
     * getter undo button
     * @return Button
     */
    public Button getUndo() {
        return undo;
    }

    /**
     * getter redo button
     * @return Button
     */
    public Button getRedo() {
        return redo;
    }

    /**
     * getter giveUp button
     * @return Button
     */
    public Button getGiveUp() {
        return giveUp;
    }

    /**
     * getter Restart button
     * @return Button
     */
    public Button getRestart() {
        return restart;
    }

    public double getSliderValue(){
        return slider.getValue();
    }

}
