package esi.g55019.atl.SameGame;

import esi.g55019.atl.SameGame.ControllerJavaFx.ControllerJavaFx;
import esi.g55019.atl.SameGame.Model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * the main class of the JavaFx application
 */
public class AppJavaFx extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Model model  = new Model();
        ControllerJavaFx controller = new ControllerJavaFx(model);
        controller.start(primaryStage);
    }
}
