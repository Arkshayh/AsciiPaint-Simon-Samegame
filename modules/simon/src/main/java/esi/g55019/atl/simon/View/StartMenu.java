package esi.g55019.atl.simon.View;

import esi.g55019.atl.simon.Controller.Controller;
import esi.g55019.atl.simon.Model.Model;
import esi.g55019.atl.simon.Model.State;
import esi.g55019.atl.simon.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class StartMenu implements Observer {
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
    private Controller controller;
    private State stateOfModel;

    //TODO : button longuest tester
    //TODO : button last tester
    //TODO : slider
    //TODO : Son
    //TODO : javadoc
    public StartMenu(Controller controller, Model model) {
        this.controller = controller;
        model.addObserver(this);
        setUpLabel();
        setUpCheckBox();
        setUpHbox();
        setUpSlider();
        setUpVbox();
        setUpButtonStart();
        setUpButtonLast();
        setUpButtonLonguest();
    }

    private void setUpButtonStart(){
        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(stateOfModel == State.ON_THE_MENU){
                    controller.startOnClick();
                }
            }
        });
    }

    private void setUpButtonLast(){
        buttonLast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(stateOfModel == State.ON_THE_MENU){
                    controller.lastOnClick();
                }
            }
        });
    }

    private void setUpButtonLonguest(){
        buttonLongest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(stateOfModel == State.ON_THE_MENU){
                    controller.longuestOnClick();
                }
            }
        });
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

    @Override
    public void update(State state) {
        this.stateOfModel = state;
    }
}
