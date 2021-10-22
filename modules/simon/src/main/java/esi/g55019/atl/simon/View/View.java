package esi.g55019.atl.simon.View;

import esi.g55019.atl.simon.Controller.Controller;
import esi.g55019.atl.simon.Model.Color;
import esi.g55019.atl.simon.Model.Model;
import esi.g55019.atl.simon.Model.State;
import esi.g55019.atl.simon.util.Observer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * the view of the game, the view is an observer and has as attributes every thing that is visible for the player.
 * The player will called some method of the controller when the player will click on the buttons
 */
public class View implements Observer {
    private Button boutonVert = new Button();
    private Button boutonRouge = new Button();
    private Button boutonJaune = new Button();
    private Button boutonBleu = new Button();
    private Synthesizer synth;
    private MidiChannel channel;
    private PauseTransition pause;
    private StartMenu startMenu;
    private Controller controller;
    private Model model;
    private State stateOfModel;

    /**
     * Contrucotr of the view, create the start menu. Add itself to the observer list of the model
     * @param controller Controller
     * @param model Model
     */
    public View(Controller controller, Model model) {
        this.controller = controller;
        this.model = model;
        startMenu  = new StartMenu(controller, model);
        model.addObserver(this);
    }

    /**
     * the method called by the controller to display the entire game,
     * it will set up some attributes and add the 4 color button the the gridplane
     * @param primaryStage
     */
    public void start(Stage primaryStage){
        primaryStage.setTitle("Projet Simon");
        GridPane root = new GridPane();

        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(500,500);
        stackPane.getChildren().add(root);

        stackPane.getChildren().add(startMenu.getMenu());

        try{
            setUpSynth();
        }catch (Exception e){
            System.err.println(e);;
        }
        setUpChannelandPause();
        setUpColorButton();

        root.add(boutonVert,0,0);
        root.add(boutonRouge,1,0);
        root.add(boutonJaune,0,1);
        root.add(boutonBleu,1,1);

        Scene scene = new Scene(stackPane,600,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * set yp the channel and the pause (for the sound when the player clicked on the button)
     */
    private void setUpChannelandPause(){
        channel = synth.getChannels()[0];
        pause = new PauseTransition(Duration.seconds(1));
    }

    /**
     * set up each color button, their action when you clicked on them, their size and style (if the stateofmodel is
     * not PLAYER_CHOOSE nothing will happen when you will clicked on one of the button)
     */
    private void setUpColorButton(){

        boutonVert.setPrefSize(800,500);
        boutonVert.setStyle("-fx-background-color: #007d15; ");
        boutonVert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(stateOfModel == State.PLAYER_CHOOSE){
                    onClickGreen();
                }
            }
        });

        boutonRouge.setPrefSize(800,500);
        boutonRouge.setStyle("-fx-background-color: #7a0010; ");
        boutonRouge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(stateOfModel == State.PLAYER_CHOOSE){
                    onClickRed();
                }
            }
        });

        boutonJaune.setPrefSize(800,500);
        boutonJaune.setStyle("-fx-background-color: #c7b600; ");
        boutonJaune.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(stateOfModel == State.PLAYER_CHOOSE){
                    onClickYellow();
                }
            }
        });

        boutonBleu.setPrefSize(800,500);
        boutonBleu.setStyle("-fx-background-color: #004da6; ");
        boutonBleu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(stateOfModel == State.PLAYER_CHOOSE){
                    onClickBlue();
                }
            }
        });
    }


    /**
     * change the color of the button green if the state is PLAYER_CHOOSE the color of the button will change
     * if the checkbox silentbox is not cheked a sound will be produce
     * after one second change the color of the button
     */
    private void onClickGreen(){
        boutonVert.setStyle("-fx-background-color: #25a73b; ");
        if(stateOfModel == State.PLAYER_CHOOSE){
            controller.colorButtonOnClick(Color.GREEN);
        }
        if(!startMenu.getCheckBox().isSelected()){
            playSound(channel, pause,69);
        }
        setTimeout((startMenu.getSliderValue() - startMenu.getSliderValue()/5), "-fx-background-color: #007d15; ", boutonVert);
    }

    /**
     * change the color of the button red if the state is PLAYER_CHOOSE the color of the button will change
     * if the checkbox silentbox is not cheked a sound will be produce
     * after one second change the color of the button
     */
    private void onClickRed(){
        boutonRouge.setStyle("-fx-background-color: #b50b21; ");
        if(stateOfModel == State.PLAYER_CHOOSE){
            System.out.println("click rouge");
            controller.colorButtonOnClick(Color.RED);
        }
        if(!startMenu.getCheckBox().isSelected()){
            playSound(channel, pause,71);
        }
        setTimeout((startMenu.getSliderValue() - startMenu.getSliderValue()/5), "-fx-background-color: #7a0010; ",boutonRouge);
    }

    /**
     * change the color of the button yellow if the state is PLAYER_CHOOSE the color of the button will change
     * if the checkbox silentbox is not cheked a sound will be produce
     * after one second change the color of the button
     */
    private void onClickYellow(){
        boutonJaune.setStyle("-fx-background-color: #f2de00; ");
        if(stateOfModel == State.PLAYER_CHOOSE){
            System.out.println("click jaune");
            controller.colorButtonOnClick(Color.YELLOW);
        }
        if(!startMenu.getCheckBox().isSelected()){
            playSound(channel, pause,72);
        }
        setTimeout((startMenu.getSliderValue() - startMenu.getSliderValue()/5),"-fx-background-color: #c7b600; ", boutonJaune);
    }

    /**
     * change the color of the button blue if the state is PLAYER_CHOOSE the color of the button will change
     * if the checkbox silentbox is not cheked a sound will be produce
     * after one second change the color of the button
     */
    private void onClickBlue(){
        boutonBleu.setStyle("-fx-background-color: #0091ff; ");
        if(stateOfModel == State.PLAYER_CHOOSE){
            controller.colorButtonOnClick(Color.BLUE);
        }
        if(!startMenu.getCheckBox().isSelected()){
            playSound(channel, pause,74);
        }
        setTimeout((startMenu.getSliderValue() - startMenu.getSliderValue()/5),"-fx-background-color: #004da6; ",boutonBleu);
    }


    /**
     * create a new thread that will be asleep for the current delay then will change the color of the given button by
     * the color given
     * @param delay int
     * @param color Color
     * @param bouton Button
     */
    private void setTimeout(int delay, String color, Button bouton){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                bouton.setStyle(color);
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    /**
     * set up the synthetizor
     * @throws MidiUnavailableException
     */
    private void setUpSynth() throws MidiUnavailableException {
        synth = MidiSystem.getSynthesizer();
        synth.open();
    }

    /**
     * play a sound
     * @param channel MidiChannel
     * @param pause PauseTransition
     */
    private void playSound(MidiChannel channel, PauseTransition pause, int noteSound){
        channel.noteOn(noteSound, 80);
        pause.setOnFinished(event -> channel.noteOff(69));
        pause.play();
    }

    /**
     * for each valeu on the given list, it will called the onclick function of the button of the same color
     * @param listeColor List<Color>
     * @throws IllegalStateException if the state if not AFFICHAGE_START
     */
    public void playSequenceColor(List<Color> listeColor){
        if(stateOfModel != State.AFFICHAGE_START){
            throw new IllegalStateException("State errorn you can only display the color on STATE_AFFICHAGE :" +
                    stateOfModel);
        }
        final int[] i = {0};
        var timeline = new Timeline(new KeyFrame(Duration.millis(startMenu.getSliderValue()), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                switch (listeColor.get(i[0])){
                    case GREEN:
                        onClickGreen();
                        break;
                    case RED:
                        onClickRed();
                        break;
                    case YELLOW:
                        onClickYellow();
                        break;
                    default:
                        onClickBlue();
                }
                i[0]++;
            }
        }));
        timeline.setCycleCount(listeColor.size());
        timeline.play();
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model.setState(State.TIMER);
                model.setState(State.PLAYER_CHOOSE);
            }
        });
    }

    /**
     * Display a popup with the message given
     * @param message String
     */
    public void displayAlert(String message){
        Alert alertlast = new Alert(Alert.AlertType.ERROR);
        alertlast.setTitle(message);
        alertlast.setContentText(message);
        alertlast.showAndWait();
    }

    /**
     * Display a popup who say PERDU
     */
    public void displayLose(){
        Alert alertlast = new Alert(Alert.AlertType.INFORMATION);
        alertlast.setTitle("Perdu");
        alertlast.setContentText("Perdu");
        alertlast.showAndWait();
    }

    /**
     * Display a popup when time out
     */
    public void displayLoseTime(){
        Alert alertlast = new Alert(Alert.AlertType.INFORMATION);
        alertlast.setTitle("Temps écoulé");
        alertlast.setContentText("Temps écouél");
        alertlast.showAndWait();
    }

    //TODO: est ce qu'il faut la définir dans le model ?

    /**
     * create a timerTask for the timer in the model, this task display a message : no more time
     * then displayLose() is called and at the end lose() is called
     * @return TimerTask timerTask
     */
    private TimerTask goTimer(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        displayLoseTime();
                        displayLose();
                        controller.lose();
                    }
                });
            }
        };
        return timerTask;
    }

    /**
     * this method is called when the state of the model change, it will modify the stateofmodel attribute by the
     * given state
     * @param state State
     */
    @Override
    public void update(State state) {
        this.stateOfModel = state;
        System.out.println("update view : " + this.stateOfModel);
        if(stateOfModel == State.TIMER){
            model.setTimer(goTimer());
        }
    }
}
