package esi.g55019.atl.SameGame.Model;


public class Model {
    private Board board;

    public Model() {

    }

    public void initialiseBoard(int nbLigne, int nbColonne, int nbColor){
        this.board = new Board(nbLigne,nbColonne,nbColor);
    }

    public void play(Position pos){
        board.supprimerColorSetUp(pos);
        board.faireTomberBille();
        board.concatener();
    }

    public void displayBoard(){
        board.afficherPlateau();
    }

    public boolean isFinish(){
        return board.isFinish();
    }

    public int getScore(){
        return board.getScore();
    }
}
