package esi.g55019.atl.SameGame.ViewConsole;

import esi.g55019.atl.SameGame.Controller.Controller;
import esi.g55019.atl.SameGame.Model.Model;
import esi.g55019.atl.SameGame.Model.Position;

import java.util.Scanner;

public class ViewConsole {
    private Model model;
    private Controller controller;

    public ViewConsole(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
    }

    public int askColor(){
        int color = askInt("Entrer le nombre de couleurs (entre 3 et 5 compris) : ");
        while(color> 5 || color < 3){
            color = askInt("Nombre de couleur incorrectes, réessayer : ");
        }
        return color;
    }

    public int askLigneOrColonne(String msg){
        int nb = askInt("Entrer le nombres de " + msg + " (entre 5 et 50 compris) : ");
        while (nb > 50 || nb < 5){
            nb = askInt("Nombres de " + msg + " incorrectes, réesayer : ");
        }
        return nb;
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

    public Position askPosition(int ligneMax, int colonneMax){
        System.out.print("Entrer une position, ");
        Position pos = new Position(askInt("d'abord le numéro de la ligne : "), askInt("Le numéro de la colonne : "));
        while (pos.getLigne() > ligneMax || pos.getLigne() <0 || pos.getColonne() > colonneMax || pos.getColonne() <0){
            System.out.println("Cette position n'est pas dans le plateau, réessayer : ");
            pos = new Position(askInt("d'abord le numéro de la ligne : "), askInt("Le numéro de la colonne : "));
        }
        return pos;
    }

    public void displayBoard(){
        model.displayBoard();
    }

    public void displayScore(String msg){
        System.out.println(msg + model.getScore());
    }
}
