package esi.g55019.atl.SameGame.ViewJavaFx;

import esi.g55019.atl.SameGame.ControllerJavaFx.ControllerJavaFx;
import esi.g55019.atl.SameGame.Model.Board;
import esi.g55019.atl.SameGame.Model.State;
import esi.g55019.atl.SameGame.Model.Model;
import esi.g55019.atl.SameGame.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;

public class ViewJavaFx implements Observer {
    private ControllerJavaFx controller;
    private Model model;
    private HBox hbox;
    private Menu menu;
    private BoardFx boardFx;
    private BorderPane root;
    private Label title;
    private StackPane stackPane;

    public ViewJavaFx(ControllerJavaFx controller, Model model) {
        this.controller = controller;
        this.model = model;
        model.addObserver(this);
    }

    public void start(Stage primaryStage){
        primaryStage.setTitle("Projet SameGame");
        root = new BorderPane();
        root.setPrefSize(1000,750);
        root.setMaxSize(1000,750);

        setUpHbox();
        menu = new Menu(controller);

        root.setTop(hbox);
        root.setRight(menu.getvBox());

        stackPane = new StackPane();

        root.setCenter(stackPane);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setUpHbox(){
        this.hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        title = new Label("SameGame");
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        hbox.getChildren().add(title);
    }

    public void updatingBoard(Board board) {
        this.boardFx = new BoardFx(board, controller);
        GridPane monBoard = boardFx.getBoardPane();
        monBoard.setAlignment(Pos.CENTER);
        monBoard.setPadding(new Insets(10));
        stackPane.getChildren().add(monBoard);
    }

    @Override
    public void update(State state, Board board, int bestScore) {
        switch (state){
            case CREATION_BOARD:
            case UPDATE_BOARD:
                updatingBoard(board);
                updateTitle(board.getScore(), bestScore);
                break;
            case IS_WIN:
                //TODO
                menu.getUndo().setDisable(true);
                menu.getRedo().setDisable(true);
                menu.getGiveUp().setDisable(true);
                menu.getRestart().setDisable(false);
            case IS_LOSE:
                //TODO
                menu.getUndo().setDisable(true);
                menu.getRedo().setDisable(true);
                menu.getGiveUp().setDisable(true);
                menu.getRestart().setDisable(false);

        }
    }

    public void disableUndo(boolean bool){
        menu.getUndo().setDisable(bool);
    }

    public void disableRedo(boolean bool){
        menu.getRedo().setDisable(bool);
    }


    public void updateTitle(int score, int bestScore){
        title.setText("SameGame | Score : " + score + " | Meilleur score : " + bestScore);
    }

    public void restartOn(){
        menu.getRestart().setDisable(false);
    }
}
