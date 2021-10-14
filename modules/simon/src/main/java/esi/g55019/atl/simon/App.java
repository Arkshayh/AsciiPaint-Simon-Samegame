package esi.g55019.atl.simon;

import esi.g55019.atl.simon.View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new View(primaryStage);
        view.start();
    }
}
