package esi.g55019.atl.simon;

import esi.g55019.atl.simon.Controller.Controller;
import esi.g55019.atl.simon.Model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main method of the Simon game
 * @author Ian Cotton g55019
 */
public class App extends Application {
    public static void main(String[] args){
        launch(args);
    }

    /**
     * the method called when the main function is executed, it will create a model and a controller and called its
     * start function
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        Model model  = new Model();
        Controller controller = new Controller(model);
        controller.start(primaryStage);
    }
}
