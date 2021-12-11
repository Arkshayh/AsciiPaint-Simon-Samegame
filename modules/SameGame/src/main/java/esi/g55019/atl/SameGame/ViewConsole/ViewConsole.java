package esi.g55019.atl.SameGame.ViewConsole;

import esi.g55019.atl.SameGame.Controller.Controller;
import esi.g55019.atl.SameGame.Model.Model;
import java.util.Scanner;

/**
 * Represent the view of the console app
 */
public class ViewConsole {
    private Model model;
    private Controller controller;

    /**
     * Constructor
     * @param model Model
     * @param controller Controller
     */
    public ViewConsole(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
    }

    /**
     * ask to the user to input the number of color (3-5)
     * @return int
     */
    public int askColor(){
        int color = askInt("Entrer le nombre de couleurs (entre 3 et 5 compris) : ");
        while(color> 5 || color < 3){
            color = askInt("Nombre de couleur incorrectes, réessayer : ");
        }
        return color;
    }

    /**
     * Ask to the user to input the number of row/column 5-20
     * @param msg String
     * @return int
     */
    public int askLigneOrColonne(String msg){
        int nb = askInt("Entrer le nombres de " + msg + " (entre 5 et 50 compris) : ");
        while (nb > 20 || nb < 5){
            nb = askInt("Nombres de " + msg + " incorrectes, réesayer : ");
        }
        return nb;
    }

    /**
     * ask to the user a number
     * @param message String
     * @return int
     */
    private int askInt(String message) {
        System.out.println(message);
        Scanner clavier = new Scanner(System.in);

        while (!clavier.hasNextInt()) {
            System.out.println("Erreur, veuillez réessayer :");
            clavier.next();
        }
        return clavier.nextInt();
    }

    /**
     * Display the board
     */
    public void displayBoard(){
        System.out.println(model.getBoard());
    }

    /**
     * display the score
     * @param msg String
     */
    public void displayScore(String msg){
        System.out.println(msg + model.getScore());
    }

    /**
     * display the list of commands
     */
    public void displayListCommand(){
        System.out.println("Entrer une commande : \n" +
                "* Une position pour enlèver la bille à cet endroit.\n" +
                "* Redo : pour reéxecuter la commande précédente.\n" +
                "* Undo : pour annuler la commande précédente.\n" +
                "* GiveUp : pour abandonner la partie et fermer le programme.\n" +
                "* Restart : pour recommancer une partie.\n");
    }

    /**
     * display a replay message
     */
    public void replayMsg(){
        System.out.println("Voulez-vous rejouer ? (Y/N)");
    }
}
