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
        Board oldBoard = undo.get(undo.size()-1);
        redo.add(boardJavaFx.getBoard());
        undo.remove(undo.get(undo.size()-1));
        view.setBoardJavaFx(oldBoard);
    }

    public void updateViewWithRedo(){
        Board oldBoard = redo.get(redo.size()-1);
        undo.add(new Board(oldBoard.getLigne(), oldBoard.getColonne(),oldBoard.getPlateau(), oldBoard.getScore()));
        redo.remove(redo.get(redo.size()-1));
        view.setBoardJavaFx(oldBoard);
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
    }
}
