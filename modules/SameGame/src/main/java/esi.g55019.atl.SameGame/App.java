package esi.g55019.atl.SameGame;


import esi.g55019.atl.SameGame.Model.Board;
import java.util.Scanner;

public class App {
    private Board board;

    //TODO: checker fin
    public static void main(String[] args) {

        App app = new App();
        app.start();
    }

    private void start(){

        int nbColor = askInt("Entrer le nombre de couleurs");
        while(nbColor > 5 || nbColor < 3){
            nbColor = askInt("Erreur, entrer le nombre de couleurs entre 3 et 5 :");
        }
        int nbLigne = askInt("Entrer le nombre de lignes (entre 5 et 50 compris) : ");
        while (nbLigne <5 || nbLigne > 50){
            nbLigne = askInt("Erreur ! \n Entrer le nombre de lignes (entre 5 et 50 compris) : ");
        }
        int nbColonne = askInt("Entrer le nombre de colonnes (entre 5 et 50 compris) : ");
        while (nbColonne <5 || nbColonne > 50){
            nbColonne = askInt("Erreur ! \n Entrer le nombre de colonnes (entre 5 et 50 compris) : ");
        }
        this.board = new Board(nbLigne,nbColonne,nbColor);
        this.board.play();
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

}


/**
 * gridpane n * m  et un bouton dans chaque, si on a des objets dans son model on va créer
 */