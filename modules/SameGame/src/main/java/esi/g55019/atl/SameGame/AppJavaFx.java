package esi.g55019.atl.SameGame;


import esi.g55019.atl.SameGame.ControllerJavaFx.ControllerJavaFx;
import esi.g55019.atl.SameGame.ModelJavaFx.ModelJavaFx;
import esi.g55019.atl.SameGame.ViewJavaFx.ViewJavaFx;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppJavaFx extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primarystage){
        ViewJavaFx viewJavaFx = new ViewJavaFx();
        viewJavaFx.start(primarystage);

        //ModelJavaFx model  = new ModelJavaFx();
        //ControllerJavaFx controller = new ControllerJavaFx(model);
        //controller.start(primaryStage);
    }
}
