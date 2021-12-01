package esi.g55019.atl.SameGame.ControllerJavaFx;

import esi.g55019.atl.SameGame.ViewJavaFx.BoardJavaFx;
import esi.g55019.atl.SameGame.ModelJavaFx.ModelJavaFx;
import esi.g55019.atl.SameGame.ViewJavaFx.ViewJavaFx;
import javafx.stage.Stage;

public class ControllerJavaFx {
    private ModelJavaFx model;
    private ViewJavaFx view;

    public ControllerJavaFx(ModelJavaFx model) {
        this.model = model;
        this.view = new ViewJavaFx(this, model);
    }

    public void start(Stage primaryStage){
        view.start(primaryStage);
    }

    public void createBoard(int ligne, int colonne, int nbColor){
        view.addingBoard(new BoardJavaFx(ligne, colonne, nbColor, model));
    }

}
