package esi.g55019.atl.simon.Controller;

import esi.g55019.atl.simon.Model.Model;
import esi.g55019.atl.simon.View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Controller extends Application {
    private Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this, model);
    }

    @Override
    public void start(Stage primaryStage) {
        view.start(primaryStage);
    }
}
