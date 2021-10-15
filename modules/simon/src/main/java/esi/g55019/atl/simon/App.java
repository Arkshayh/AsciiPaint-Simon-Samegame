package esi.g55019.atl.simon;

import esi.g55019.atl.simon.Controller.Controller;
import esi.g55019.atl.simon.Model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        var model  = new Model();
        var controller = new Controller(model);
        controller.start(primaryStage);
    }
}
