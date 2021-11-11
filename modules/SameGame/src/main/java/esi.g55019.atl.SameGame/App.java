package esi.g55019.atl.SameGame;


import esi.g55019.atl.SameGame.Model.Board;
import esi.g55019.atl.SameGame.Model.Position;

public class App {
    public static void main(String[] args) {
        Board board = new Board(3);
        board.afficherPlateau();
        System.out.printf("---------------");
        board.supprimerColorSetUp(new Position(0,0));
        board.afficherPlateau();
    }
}

/**
 * gridpane n * m  et un bouton dans chaque, si on a des objets dans son model on va créer
 */