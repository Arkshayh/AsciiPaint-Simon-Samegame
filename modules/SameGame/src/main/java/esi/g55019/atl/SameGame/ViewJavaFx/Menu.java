package esi.g55019.atl.SameGame.ViewJavaFx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Menu {
    private VBox vBox;
    private TextField ligne;
    private TextField colonne;
    private ComboBox difficulté;
    private Button start;
    private HBox undoRedo;
    private Button undo;
    private Button redo;
    private HBox giveUpRestart;
    private Button giveUp;
    private Button restart;

    public Menu() {
        setUpMenu();
    }

    private void setUpMenu(){
        this.vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);

        ligne = new TextField();
        ligne.setPromptText("Nombre de lignes");
        vBox.getChildren().add(ligne);

        colonne = new TextField();
        colonne.setPromptText("Nombre de colonnes");
        vBox.getChildren().add(colonne);

        Label textDifficulté = new Label("Difficulté : ");
        vBox.getChildren().add(textDifficulté);

        difficulté = new ComboBox();
        difficulté.getItems().addAll("Facile","Moyen","Difficile");
        difficulté.getSelectionModel().select(0);
        vBox.getChildren().add(difficulté);

        start = new Button("Start");
        vBox.getChildren().add(start);

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

    private void ligneSetUp(){

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
}
