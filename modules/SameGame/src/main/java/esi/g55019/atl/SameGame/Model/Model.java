package esi.g55019.atl.SameGame.Model;

import esi.g55019.atl.SameGame.util.Observable;
import esi.g55019.atl.SameGame.util.Observer;

import java.util.ArrayList;
import java.util.List;

public class Model implements Observable {
    private State state;
    private List<Observer> listObserver;
    private Board board;
    private int bestScore = 0;

    public Model() {
        this.state = State.ON_THE_MENU;
        listObserver = new ArrayList<>();
    }

    public void createBoard(int row, int column, int level){
        board = new Board(row, column, level);
        changeState(State.CREATION_BOARD);
    }

    private void changeState(State state){
        this.state = state;
        notifyObservers(this.board);
    }

    public boolean play(Position pos){
        if(board.isInside(pos)){
            if(board.getVoisinASupprimer(pos).size() != 1){
                board.supprimerColorSetUp(pos);
                board.faireTomberBille();
                board.concatener();
                changeState(State.UPDATE_BOARD);
                checkingFinish();
            }
            return true;
        }
        return false;
    }

    private void checkingFinish(){
        if(board.isFinish()){
            updateBestScore();
            if(board.isWin()){
                changeState(State.IS_WIN);
            }
            else{
                changeState(State.IS_LOSE);
            }
        }
    }

    public void giveUp(){
        board.giveUp();
        changeState(State.UPDATE_BOARD);
        changeState(State.IS_LOSE);
    }

    public boolean isFinish(){
        return board.isFinish();
    }

    public boolean isWin(){
        return board.isWin();
    }

    public int getScore(){
        return board.getScore();
    }

    public Board getBoard() {
        Board copieDefensive = new Board(board.getLigne(), board.getColonne(),board.getPlateau(), board.getScore());
        return copieDefensive;
    }

    public void setBoard(Board board) {
        this.board = board;
        changeState(State.UPDATE_BOARD);
    }

    private void updateBestScore(){
        if(board.getScore() > bestScore){
            bestScore = board.getScore();
        }
    }

    /**
     * add an observer to the observerlist
     * @param observer The observer to be added.
     */
    @Override
    public void addObserver(Observer observer) {
        listObserver.add(observer);
        notifyObservers(this.board);
    }

    /**
     * remove an observer from the observer list
     * @param observer The  observer to be removed.
     */
    @Override
    public void removeObserver(Observer observer) {
        listObserver.remove(observer);
    }

    /**
     * send the state of the model to every observer
     */
    @Override
    public void notifyObservers(Board board) {
        System.out.println(state);
        for (Observer observer : listObserver) {
            observer.update(state, board, bestScore);
        }
    }
}
