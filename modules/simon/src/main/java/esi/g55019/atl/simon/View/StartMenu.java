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

/**
 * This class contains the items from the start menu:
 * 2 label, a slider, 3 buttons which are in an Hbox (start, longuest and last)
 * and a checkbox to choose to play with or without sound.
 * These item will be set up in this class.
 * @author Ian cotton | g55019
 */
public class StartMenu implements Observer {
    private VBox vbox = new VBox();
    private Label simon = new Label("Simon");
    private Slider slider = new Slider();
    private Label speed = new Label("rapide <- speed ->   lent");
    private HBox hbox = new HBox();
    private Label info = new Label("info");
    private CheckBox checkBox = new CheckBox("Silent mode ");
    private Button buttonLongest = new Button("longest");
    private Button buttonStart = new Button("start");
    private Button buttonLast = new Button("last");
    private Controller controller;
    private State stateOfModel;

    /**
     * Constructor, we need the controller to setUp the buttons and the model to add this object as an observer.
     * The constructor will setup each item of the start menu
     * @param controller
     * @param model
     */
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

    /**
     * add an action on the button start, the state of the game must be ON_THE_MENU
     */
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

    /**
     * add an action on the button last, the state of the game must be ON_THE_MENU
     */
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

    /**
     * add an action on the button longuest, the state of the game must be ON_THE_MENU
     */
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

    /**
     * Set up the position, style, size and the spacing of the Vbox and add the others item to it
     */
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

    /**
     * set the font of the label
     */
    private void setUpLabel(){
        simon.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
    }

    /**
     * name the checkbox and set its position
     */
    private void setUpCheckBox(){
        CheckBox checkBox = new CheckBox("Silent mode ");
        checkBox.setAlignment(Pos.CENTER);
    }

    /**
     * Set the alignement of the hbox, its size, padding, spacing and add the 3 buttons to it
     */
    private void setUpHbox(){
        hbox.setAlignment(Pos.CENTER);
        hbox.setMaxSize(250, 100);
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);

        hbox.getChildren().add(buttonLongest);
        hbox.getChildren().add(buttonStart);
        hbox.getChildren().add(buttonLast);

    }

    /**
     * set up the min and max value of the slider and its width
     */
    private void setUpSlider(){
        slider.setMin(1);
        slider.setMax(10);
        slider.setMaxWidth(150);
    }

    /**
     * return the value of the slider (converted to int) multiply by 100
     * @return int
     */
    public int getSliderValue(){
        return ((int)slider.getValue())*100;
    }

    /**
     * return the vbox (getter)
     * @return Vbox vbox
     */
    public VBox getMenu(){
        return vbox;
    }

    /**
     * getter for the checkbox
     * @return Checkbox checkBox
     */
    public CheckBox getCheckBox(){
        return checkBox;
    }

    /**
     * implement the method of the observer interface
     * @param state State
     */
    @Override
    public void update(State state) {
        this.stateOfModel = state;
    }
}
