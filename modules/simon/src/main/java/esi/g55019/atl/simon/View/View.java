package esi.g55019.atl.simon.View;

import esi.g55019.atl.simon.Controller.Controller;
import esi.g55019.atl.simon.Model.Model;
import esi.g55019.atl.simon.Model.State;
import esi.g55019.atl.simon.util.Observer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.List;

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


    public View(Controller controller, Model model) {
        this.controller = controller;
        this.model = model;
        startMenu  = new StartMenu(controller);
        model.addObserver(this);
    }

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

    private void setUpChannelandPause(){
        channel = synth.getChannels()[0];
        pause = new PauseTransition(Duration.seconds(1));
    }

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



    private void onClickGreen(){
        boutonVert.setStyle("-fx-background-color: #19cf37; ");
        if(!startMenu.getCheckBox().isSelected()){
            playSound(channel, pause);
        }
        setTimeout(1000, "-fx-background-color: #007d15; ", boutonVert);
    }

    private void onClickRed(){
        boutonRouge.setStyle("-fx-background-color: #b50b21; ");
        if(!startMenu.getCheckBox().isSelected()){
            playSound(channel, pause);
        }
        setTimeout(1000, "-fx-background-color: #7a0010; ",boutonRouge);
    }

    private void onClickYellow(){
        boutonJaune.setStyle("-fx-background-color: #f2de00; ");
        if(!startMenu.getCheckBox().isSelected()){
            playSound(channel, pause);
        }
        setTimeout(1000,"-fx-background-color: #c7b600; ", boutonJaune);
    }
    private void onClickBlue(){
        boutonBleu.setStyle("-fx-background-color: #0091ff; ");
        if(!startMenu.getCheckBox().isSelected()){
            playSound(channel, pause);
        }
        setTimeout(1000,"-fx-background-color: #004da6; ",boutonBleu);
    }



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

    private void setUpSynth() throws MidiUnavailableException {
        synth = MidiSystem.getSynthesizer();
        synth.open();
    }

    private void playSound(MidiChannel channel, PauseTransition pause){
        channel.noteOn(69, 80);
        pause.setOnFinished(event -> channel.noteOff(69));
        pause.play();
    }

    private void playSequenceColor(List<Color> listeColor){
        final int[] i = {0};
        var timeline = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                switch (listeColor.get(i[0]).toString()){
                    case "0x008000ff":
                        onClickGreen();
                        break;
                    case "0xff0000ff":
                        onClickRed();
                        break;
                    case "0xffff00ff":
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
    }

    @Override
    public void update(State state) {
        this.stateOfModel = state;
    }
}
