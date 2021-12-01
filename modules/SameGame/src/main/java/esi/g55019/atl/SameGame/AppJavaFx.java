package esi.g55019.atl.SameGame;


import esi.g55019.atl.SameGame.ControllerJavaFx.ControllerJavaFx;
import esi.g55019.atl.SameGame.ModelJavaFx.ModelJavaFx;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppJavaFx extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * 4) hover
     * 6) undo / redo
     * 7) give up
     * 8) restart
     * 9) Ajouter popup win si gagné sinon lose
     * 9) score + meilleur score
     * 10) voir TODO
     */
    @Override
    public void start(Stage primaryStage){
        ModelJavaFx model  = new ModelJavaFx();
        ControllerJavaFx controller = new ControllerJavaFx(model);
        controller.start(primaryStage);
    }
}
