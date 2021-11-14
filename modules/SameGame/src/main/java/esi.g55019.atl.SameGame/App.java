package esi.g55019.atl.SameGame;


import esi.g55019.atl.SameGame.Model.Board;
import esi.g55019.atl.SameGame.Model.Position;

import java.util.Scanner;

public class App {
    private Board board;

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    private void start(){
        boolean fin = false;
        int nbColor = askInt("Entrer le nombre de couleurs");
        while(nbColor > 5 || nbColor < 3){
            nbColor = askInt("Erreur, entrer le nombre de couleurs entre 3 et 5 :");
        }
        this.board = new Board(nbColor);
        while (!fin){
            board.afficherPlateau();
            board.supprimerColorSetUp(askPosition());
            board.faireTomberBille();
        }

    }

    private int askInt(String message) {
        System.out.println(message);
        Scanner clavier = new Scanner(System.in);

        while (!clavier.hasNextInt()) {
            System.out.println("Erreur, veuillez réessayer :");
            clavier.next();
        }
        return clavier.nextInt();
    }

    private Position askPosition(){
        Position pos = new Position(askInt("Entrer la ligne de la bille : "), askInt("Entrer la colonne de la bille :"));
        while (!board.isInside(pos)){
            System.out.println("Position incorrecte : ");
            pos.setLigne(askInt("Entrer la ligne de la bille : "));
            pos.setColonne(askInt("Entrer la colonne de la bille :"));
        }
        return pos;
    }
}


/**
 * gridpane n * m  et un bouton dans chaque, si on a des objets dans son model on va créer
 */