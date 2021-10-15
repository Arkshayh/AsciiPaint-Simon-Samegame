package esi.g55019.atl.simon.Controller;

import esi.g55019.atl.simon.View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Controller extends Application {
    private View view;

    public Controller() {
    }

    @Override
    public void start(Stage primaryStage) {
        this.view = new View(primaryStage);
        view.start();
    }
}
