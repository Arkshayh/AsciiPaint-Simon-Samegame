package esi.g55019.atl.SameGame.ViewJavaFx;


import esi.g55019.atl.SameGame.ControllerJavaFx.ControllerJavaFx;
import esi.g55019.atl.SameGame.ModelJavaFx.BoardJavaFx;
import esi.g55019.atl.SameGame.ModelJavaFx.ModelJavaFx;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ViewJavaFx {
    private ControllerJavaFx controller;
    private ModelJavaFx model;
    private HBox hbox;
    private BoardJavaFx board;
    private Menu menu;
    private BorderPane root;

    public ViewJavaFx(ControllerJavaFx controller, ModelJavaFx model) {
        this.controller = controller;
        this.model = model;
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

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setUpHbox(){
        this.hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        Label label = new Label("Jeu : SameGame");
        label.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        hbox.getChildren().add(label);
    }

    public void addingBoard(BoardJavaFx board) {
        this.board = board;
        GridPane monBoard = board.getBoardPane();
        monBoard.setAlignment(Pos.CENTER);
        monBoard.setPadding(new Insets(10));
        root.setCenter(monBoard);
    }
}
