package esi.g55019.atl.SameGame.ControllerJavaFx;

import esi.g55019.atl.SameGame.Model.Board;
import esi.g55019.atl.SameGame.ViewJavaFx.BoardJavaFx;
import esi.g55019.atl.SameGame.ModelJavaFx.ModelJavaFx;
import esi.g55019.atl.SameGame.ViewJavaFx.ViewJavaFx;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class ControllerJavaFx {
    private ModelJavaFx model;
    private ViewJavaFx view;
    private List<Board> undo;
    private List<Board> redo;
    private BoardJavaFx boardJavaFx;

    public ControllerJavaFx(ModelJavaFx model) {
        this.model = model;
        this.view = new ViewJavaFx(this, model);
        undo = new ArrayList<>();
        redo = new ArrayList<>();
    }

    public void start(Stage primaryStage){
        view.start(primaryStage);
    }

    public void createBoard(int ligne, int colonne, int nbColor){
        view.addingBoard(new BoardJavaFx(ligne, colonne, nbColor, model, this));

    }

    public void addUndo(Board board){
        undo.add(board);
    }

    public void updateViewWithUndo(){
        redo.add(new Board(boardJavaFx.getBoard().getLigne(),boardJavaFx.getBoard().getColonne(),
                boardJavaFx.getBoard().getPlateau(),boardJavaFx.getBoard().getScore()));
        Board oldBoard = undo.get(undo.size()-1);
        view.setBoardJavaFx(oldBoard);
        undo.remove(undo.get(undo.size()-1));
        updateScore();
    }

    public void updateViewWithRedo(){
        undo.add(new Board(boardJavaFx.getBoard().getLigne(),boardJavaFx.getBoard().getColonne(),
                boardJavaFx.getBoard().getPlateau(),boardJavaFx.getBoard().getScore()));
        Board oldBoard = redo.get(redo.size()-1);
        view.setBoardJavaFx(oldBoard);
        redo.remove(redo.get(redo.size()-1));
        updateScore();
    }

    public void clearRedo(){
        redo.clear();
    }

    public int getUndoSize(){
        return undo.size();
    }

    public int getRedoSize(){
        return redo.size();
    }

    public void setBoardJavaFx() {
        this.boardJavaFx = view.getBoard();
        view.updateTitle(boardJavaFx.getScore(), boardJavaFx.getBestScore());
    }

    public void giveUp(){
        view.setBoardJavaFx(new Board(boardJavaFx.getBoard().getLigne(),boardJavaFx.getBoard().getColonne()));
        view.disableButtonsMenu();
    }

    public void finish(){
        view.disableButtonsMenu();
        view.restartOn();
    }

    public void updateScore(){
        view.updateTitle(boardJavaFx.getScore(), boardJavaFx.getBestScore());
    }

    public void restart(int ligne, int colonne, int difficulté){
        boardJavaFx.setLigne(ligne);
        boardJavaFx.setColonne(colonne);
        boardJavaFx.setBoard(new Board(ligne, colonne, difficulté));
        view.setBoardJavaFx(boardJavaFx.getBoard());
        view.updateTitle(boardJavaFx.getScore(), boardJavaFx.getBestScore());
    }
}
