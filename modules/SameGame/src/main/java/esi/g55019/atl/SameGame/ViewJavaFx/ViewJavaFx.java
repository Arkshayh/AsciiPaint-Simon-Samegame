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
import javafx.scene.media.Media;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;

/**
 * This class represent the view of the javaFx app
 * This class is an Observer of the model. It will be notify when the state of the model change/
 */
public class ViewJavaFx implements Observer {
    private ControllerJavaFx controller;
    private Model model;
    private HBox hbox;
    private Menu menu;
    private BoardFx boardFx;
    private BorderPane root;
    private Label title;
    private StackPane stackPane;
    private ImageView victory;
    private ImageView defeat;
    private MusicFx musicPlaylist;


    /**
     * Constructor
     * @param controller ControllerJavaFx
     * @param model Model
     */
    public ViewJavaFx(ControllerJavaFx controller, Model model) {
        this.controller = controller;
        this.model = model;
        model.addObserver(this);
    }

    /**
     * Called when the app is launched
     * @param primaryStage Stage
     */
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
        stackPane.setMaxHeight(500);
        stackPane.setMaxWidth(500);

        File victoryFile = new File(getClass().getResource("/victory.jpg").getFile());
        Image imageVictory = new Image(victoryFile.toURI().toString());
        this.victory = new ImageView(imageVictory);
        victory.setFitWidth(700);
        victory.setFitHeight(412);

        File defeatFile = new File(getClass().getResource("/defeat.jpg").getFile());
        Image imageDefeat = new Image(defeatFile.toURI().toString());
        this.defeat = new ImageView(imageDefeat);
        defeat.setFitWidth(700);
        defeat.setFitHeight(412);

        root.setCenter(stackPane);
        LinearGradient radiant = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#5e5e5e")),
                new Stop(1, Color.web("#000000")));

        root.setBackground(new Background(new BackgroundFill(radiant,CornerRadii.EMPTY, Insets.EMPTY)));

        setUpMusic();
        playMusicPlaying(0);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setUpMusic(){
        musicPlaylist = new MusicFx();
        File file = new File(getClass().getResource("/Music").getFile());
        File[] listOfFile = file.listFiles();
        for (int i = 0; i < listOfFile.length; i++) {
            musicPlaylist.add(new Media(new File(listOfFile[i].getPath()).toURI().toString()), listOfFile[i].getName());
        }
    }

    /**
     * SetUp the HBOX
     */
    private void setUpHbox(){
        this.hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        setUpTitle();
        hbox.getChildren().add(title);
    }

    /**
     * SetUp the title for the Hbox.
     */
    private void setUpTitle(){
        title = new Label("SameGame");
        title.setPadding(new Insets(10));
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        CornerRadii corner = new CornerRadii(10);
        BorderWidths borderWidths = new BorderWidths(5);
        title.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, corner, borderWidths)));
        LinearGradient radiant = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#e8e041")),
                new Stop(1, Color.web("#e66712")));

        title.setBackground(new Background(new BackgroundFill(radiant,corner, new Insets(1))));
    }

    /**
     * Change the current boardFx by a new BoardFx
     * @param board Board
     */
    private void updatingBoard(Board board) {
        try {
            stackPane.getChildren().remove(0);
        }
        catch (Exception e){

        }
        // @pbt new board each time :-(
        this.boardFx = new BoardFx(board, controller);
        GridPane monBoard = boardFx.getBoardPane();
        monBoard.setAlignment(Pos.CENTER);
        monBoard.setPadding(new Insets(10));
        stackPane.getChildren().add(monBoard);
    }

    /**
     * This method is called when the state of the model change
     * @param state State
     * @param board Board
     * @param bestScore int
     */
    @Override
    public void update(State state, Board board, int bestScore, int level, int remaining, int time) {
        switch (state){
            case CREATION_BOARD:
                stopMusic();
                playMusicPlaying(level);
                updatingBoard(board);
                updateTitle(board.getScore(), bestScore, remaining);
                break;
            case UPDATE_BOARD:
                updatingBoard(board);
                updateTitle(board.getScore(), bestScore, remaining);
                break;
            case IS_WIN:
                stopMusic();
                playMusicWin();
                stackPane.getChildren().remove(0);
                stackPane.getChildren().add(victory);
                stackPane.setAlignment(victory, Pos.CENTER);
                menu.getUndo().setDisable(true);
                menu.getRedo().setDisable(true);
                menu.getGiveUp().setDisable(true);
                menu.getRestart().setDisable(false);
                break;
            case IS_LOSE:
                stopMusic();
                playMusicLose();
                stackPane.getChildren().remove(0);
                stackPane.getChildren().add(defeat);
                stackPane.setAlignment(defeat, Pos.CENTER);
                menu.getUndo().setDisable(true);
                menu.getRedo().setDisable(true);
                menu.getGiveUp().setDisable(true);
                menu.getRestart().setDisable(false);
                break;
            case UPDATE_TIMER:
                menu.setTimer(time);
        }
    }

    /**
     * Hide the victoryScreen or the defeatScreen
     */
    public void hideWinLose(){
        try{
            stackPane.getChildren().remove(this.defeat);
        }
        catch (Exception e){

        }
        try {
            stackPane.getChildren().remove(this.victory);
        }
        catch (Exception e){

        }
    }

    /**
     * Disable the undo button if the given boolean
     * @param bool boolean
     */
    public void disableUndo(boolean bool){
        menu.getUndo().setDisable(bool);
    }

    /**
     * Disable the redo button if the given boolean
     * @param bool boolean
     */
    public void disableRedo(boolean bool){
        menu.getRedo().setDisable(bool);
    }

    /**
     * Set up the victory sound and play it.
     */
    private void playMusicWin(){
        musicPlaylist.playMusicLoop("VictoryMusic.mp3");
        musicPlaylist.playSound("victorySound.mp3");
        musicPlaylist.setVolume(menu.getSliderValue());
    }

    /**
     * Set up the defeat sound and play it.
     */
    private void playMusicLose(){
        musicPlaylist.playMusicLoop("defeatMusic.mp3");
        musicPlaylist.playSound("defeatSound.mp3");
        musicPlaylist.setVolume(menu.getSliderValue());
    }

    /**
     * The music depend of the level choose by the user
     * Set up the playing sound and play it.
     */
    private void playMusicPlaying(int level){
        switch (level){
            case 3:
                musicPlaylist.playMusicLoop("whilePlaying.mp3");
                break;
            case 4:
                musicPlaylist.playMusicLoop("whilePlayingMoyen.mp3");
                break;
            case 5:
                musicPlaylist.playMusicLoop("whilePlayingHard.mp3");
                break;
            default:
                musicPlaylist.playMusicLoop("menuMusic.mp3");
                break;
        }
        musicPlaylist.setVolume(menu.getSliderValue());
    }

    /**
     * Stop the playingMusic
     */
    private void stopMusic(){
        musicPlaylist.stop();
    }

    /**
     * change the volume of the sound
     * @param volume double
     */
    public void updateVolume(double volume){
        musicPlaylist.setVolume(volume);
    }

    /**
     * Update the title with the given score / bestscore
     * @param score int
     * @param bestScore int
     */
    private void updateTitle(int score, int bestScore, int remaining){
        title.setText("SameGame | Score : " + score + " | Meilleur score : " + bestScore
                + " | Billes restantes : " + remaining);
    }

}
