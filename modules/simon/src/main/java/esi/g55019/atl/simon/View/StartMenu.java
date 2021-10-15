package esi.g55019.atl.simon.View;

import esi.g55019.atl.simon.Controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class StartMenu {
    private VBox vbox = new VBox();
    private Label simon = new Label("Simon");
    private Slider slider = new Slider();
    private Label speed = new Label("speed");
    private HBox hbox = new HBox();
    private Label info = new Label("info");
    private CheckBox checkBox = new CheckBox("Silent mode ");
    private Button buttonLongest = new Button("longest");
    private Button buttonStart = new Button("start");
    private Button buttonLast = new Button("last");


    public StartMenu(Controller controller) {
        setUpLabel();
        setUpCheckBox();
        setUpHbox();
        setUpSlider();
        setUpVbox();
    }

    private void setUpVbox(){
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #ffffff; ");
        vbox.setMaxSize(250,250);
        vbox.setSpacing(10);

        vbox.getChildren().add(simon);
        vbox.getChildren().add(slider);
        vbox.getChildren().add(speed);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(info);
        vbox.getChildren().add(checkBox);
    }

    private void setUpLabel(){
        simon.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
    }

    private void setUpCheckBox(){
        CheckBox checkBox = new CheckBox("Silent mode ");
        checkBox.setAlignment(Pos.CENTER);
    }

    private void setUpHbox(){
        hbox.setAlignment(Pos.CENTER);
        hbox.setMaxSize(250, 100);
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);

        hbox.getChildren().add(buttonLongest);
        hbox.getChildren().add(buttonStart);
        hbox.getChildren().add(buttonLast);

    }

    private void setUpSlider(){
        slider.setMin(1);
        slider.setMax(10);
        slider.setMaxWidth(150);
    }

    public VBox getMenu(){
        return vbox;
    }

    public CheckBox getCheckBox(){
        return checkBox;
    }
}
