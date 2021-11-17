package esi.g55019.atl.SameGame.Model;


public class Model {
    private Board board;

    public Model() {

    }

    public void initialiseBoard(int nbLigne, int nbColonne, int nbColor){
        this.board = new Board(nbLigne,nbColonne,nbColor);
    }

    public void play(Position pos){
        if(board.isInside(pos)){
            board.supprimerColorSetUp(pos);
            board.faireTomberBille();
            board.concatener();
        }
        else{
            System.out.println("position incorrecte ! ");
        }
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
