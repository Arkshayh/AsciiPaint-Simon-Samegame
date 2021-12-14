package esi.g55019.atl.SameGame.Model;

import esi.g55019.atl.SameGame.util.Observable;
import esi.g55019.atl.SameGame.util.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the model of the app and the javafx app.
 * This class is Observable. It has a State and each Observer is notify of the state of the model
 */
public class Model implements Observable {
    private State state;
    private List<Observer> listObserver;
    private Board board;
    private int bestScore = 0;
    private int currentLevel;

    /**
     * Constructor
     */
    public Model() {
        this.state = State.ON_THE_MENU;
        listObserver = new ArrayList<>();
    }

    /**
     * This method create a Board. It need the number of row/column and the level (the number of color)
     * @param row int
     * @param column int
     * @param level int
     */
    public void createBoard(int row, int column, int level){
        currentLevel = level;
        board = new Board(row, column, level);
        changeState(State.CREATION_BOARD);
    }

    /**
     * This method is called for change the state of the model. It will notify the Observer of the model
     * @param state
     */
    private void changeState(State state){
        this.state = state;
        notifyObservers(this.board);
    }

    /**
     * Return a boolean true if the given position is in the board
     * If true : if it has at least 1 neighbor of the same color, it will remove the Bille and its neighbor(s)
     * then called faireTomberBille() and concatener(). Then it will change
     * the State of the model and check if the game is finish.
     * return false if the position is not in the board.
     * @param pos
     * @return
     */
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

    /**
     * check if the game is finish. If it is then the change will be change to IS_WIN or IS_LOSE otherwise nothing is do
     */
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

    /**
     * call the giveUp method of the board
     * and change the the state of the game to IS_LOSE
     */
    public void giveUp(){
        board.giveUp();
        changeState(State.UPDATE_BOARD);
        changeState(State.IS_LOSE);
    }

    /**
     * return true if the game if finish otherwise false
     * @return boolean
     */
    public boolean isFinish(){
        return board.isFinish();
    }

    /**
     * return true if the user has win the game
     * otherwise false
     * @return boolean
     */
    public boolean isWin(){
        return board.isWin();
    }

    /**
     * getter for the score
     * @return int
     */
    public int getScore(){
        return board.getScore();
    }

    /**
     * getter for the board. Return a defensive copy of it
     * @return Board
     */
    public Board getBoard() {
        Board copieDefensive = new Board(board.getRow(), board.getColumn(),board.getPlateau(), board.getScore());
        return copieDefensive;
    }

    /**
     * Setter for the board
     * @param board Board
     */
    public void setBoard(Board board) {
        this.board = board;
        changeState(State.UPDATE_BOARD);
    }

    /**
     * Set the bestscore to score if the bestscore < score
     */
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
        int remaining;
        if(this.board == null){
            remaining =0;
        }
        else{
            remaining = board.nbOfBillesRemaining();
        }
        for (Observer observer : listObserver) {
            observer.update(state, board, bestScore, currentLevel, remaining);
        }
    }
}
