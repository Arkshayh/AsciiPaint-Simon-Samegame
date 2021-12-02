package esi.g55019.atl.SameGame.ViewJavaFx;

import esi.g55019.atl.SameGame.ControllerJavaFx.ControllerJavaFx;
import esi.g55019.atl.SameGame.Model.Bille;
import esi.g55019.atl.SameGame.Model.Board;
import esi.g55019.atl.SameGame.Model.Position;
import esi.g55019.atl.SameGame.ModelJavaFx.ModelJavaFx;
import esi.g55019.atl.SameGame.ModelJavaFx.State;
import esi.g55019.atl.SameGame.util.Observer;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BoardJavaFx implements Observer {
    private Board board;
    private Button[][] buttonBoard;
    private GridPane boardPane;
    private int ligne;
    private int colonne;
    private ModelJavaFx model;
    private State stateOfModel;
    private ControllerJavaFx controller;
    private int bestScore;

    public BoardJavaFx(int ligne, int colonne, int nbColor, ModelJavaFx modelJavaFx, ControllerJavaFx controller) {
        this.controller = controller;
        this.model = modelJavaFx;
        model.addObserver(this);
        this.ligne = ligne;
        this.colonne = colonne;
        board = new Board(ligne,colonne, nbColor);
        boardPane = new GridPane();
        bestScore = 0;
        if(stateOfModel == State.CREATION_BOARD){
            setUpBoardPane();
            model.setState(State.PLAYER_CHOOSE);
        }
        else{
            throw new IllegalStateException("Etat incorrect : Votre état : " + stateOfModel +
                    " Etat attendu : CREATION_BOARD");
        }
    }

    private void setUpBoardPane(){
        buttonBoard = new Button[ligne][colonne];
        Bille[][] plateauDeBille = board.getPlateau();
        for (int i = 0; i < plateauDeBille.length; i++) {
            for (int j = 0; j < plateauDeBille[0].length; j++) {
                Button button = new Button("  ");
                button.setId(i + " " + j);
                if(plateauDeBille[i][j] != null){
                    button.setBackground(new Background(new BackgroundFill(
                            Color.web(plateauDeBille[i][j].getColor().getColorForJavaFx()),
                            CornerRadii.EMPTY, Insets.EMPTY)));

                    button.setBorder(new Border(new BorderStroke(Color.rgb(104, 104, 105, 0.15),
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));

                    button.setOnAction(event -> {
                        String[] pos =  button.getId().split(" ");

                        if(stateOfModel == State.PLAYER_CHOOSE && board.getVoisinASupprimer(
                                new Position(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]))).size() != 1){

                            controller.addUndo(new Board(board.getLigne(), board.getColonne(),board.getPlateau(), board.getScore()));
                            controller.clearRedo();
                            model.setState(State.AFFICHAGE_BOARD);
                            onClickModify(new Position(Integer.parseInt(pos[0]), Integer.parseInt(pos[1])));
                        }
                    });
                }
                else{
                    button.setBackground(new Background(new BackgroundFill(Color.web("#000000"
                    ), CornerRadii.EMPTY, Insets.EMPTY)));

                    button.setBorder(new Border(new BorderStroke(Color.rgb(0, 0, 0, 0.15),
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                }
                buttonBoard[i][j] = button;
                boardPane.add(buttonBoard[i][j], j, i);
            }
        }
    }

    private void onClickModify(Position pos){
        if(stateOfModel == State.AFFICHAGE_BOARD){
            if(board.supprimerColorSetUp(pos)){
                board.faireTomberBille();
                board.concatener();
                if(board.getScore() > bestScore){
                    bestScore = board.getScore();
                }
                controller.updateScore();
                setUpBoardPane();
                model.setState(State.CHECKING_END);
            }
            else{
                model.setState(State.PLAYER_CHOOSE);
            }
        }
        else{
            throw new IllegalStateException("Etat incorrect : Votre état : " + stateOfModel +
                    " Etat attendu : AFFICHAGE_BOARD");
        }
    }

    private boolean isEnded(){
        return board.isFinish();
    }

    private boolean isWin(){
        return board.isWin();
    }

    public GridPane getBoardPane() {
        return boardPane;
    }

    @Override
    public void update(State state) {
        stateOfModel = state;
        if(state == State.CHECKING_END){
            if(isEnded()){
                controller.finish();
                System.out.println("Fin du jeu");
                model.setState(State.IS_ENDED);
            }
            else{
                model.setState(State.PLAYER_CHOOSE);
            }
        }
    }

    public void setBoard(Board board) {
        this.board = board;
        setUpBoardPane();
    }

    public Board getBoard() {
        return new Board(board.getLigne(), board.getColonne(),board.getPlateau(), board.getScore());
    }

    public int getScore(){
        return board.getScore();
    }

    public int getBestScore(){
        return bestScore;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }
}

