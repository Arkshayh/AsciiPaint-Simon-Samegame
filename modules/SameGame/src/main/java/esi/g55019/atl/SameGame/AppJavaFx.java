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
     * 1) DP Observer
     * 2) isEndend -> Message fin
     * 3) concatener
     * 4) hover
     * 5) pour start limiter les valeur de 5 à 20 + messages si valeurs incorrectes
     * 6) undo / redo
     * 7) give up
     * 8) restart
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
