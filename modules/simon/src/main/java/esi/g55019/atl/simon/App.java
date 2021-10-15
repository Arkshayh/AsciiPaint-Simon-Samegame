package esi.g55019.atl.simon;

import esi.g55019.atl.simon.Controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }
}
