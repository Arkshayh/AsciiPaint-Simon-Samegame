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
     * 9) Ajouter popup win en vert avec musique victoire si gagné sinon lose pop up perdu lose avec rire veigar
     * 10) voir TODO
     */
    @Override
    public void start(Stage primaryStage){
        ModelJavaFx model  = new ModelJavaFx();
        ControllerJavaFx controller = new ControllerJavaFx(model);
        controller.start(primaryStage);
    }
}
