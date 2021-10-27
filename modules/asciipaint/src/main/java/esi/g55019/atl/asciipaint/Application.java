package esi.g55019.atl.asciipaint;

import esi.g55019.atl.asciipaint.DPCommand.*;
import esi.g55019.atl.asciipaint.ShapePackage.Line;

import java.util.List;
import java.util.Scanner;

/**
 * @author Cotton Ian g55019
 * Main class of the mini Asciipaint project
 * No test because the method who can be test are private and work in every cases, start method does not need to
 * be tested because it called the private method who worked
 */
public class Application {
    private AsciiPaint paint;

    /**
     * main methode
     * @param args
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.start();
    }

    /**
     * start the program, this method will manage the input of the user (if he want to end the programm/add shape/show
     * the drawing)
     */
    public void start() {
        String commandeString;
        askDimension();
        FactoryCommand usineCommande = new FactoryCommand(paint);
        Command command;

        //TODO: a débugger pour show
        while (!paint.getEnd()) {
            commandeString = askCommand();
            try{
                usineCommande.setCommandeIni(commandeString);
                command = usineCommande.getCommandeCorrection();
                command.execute();
                usineCommande.setPaint(paint);

            }catch (Exception e){
                System.out.printf("Erreur. ");
            }
        }
    }

    private void group(){
        int forme1 = askInt("Quelle forme voulez-vous ajouter dans le groupe : ");
        while (forme1 > paint.nbForme() || forme1 <= 0){
            System.out.println("Erreur, choissisez une forme valide");
            forme1 = askInt("Quelle forme voulez-vous ajouter dans le groupe : ");
        }
        int forme2 = askInt("Quelle autres formes voulez vous ajouter dans le groupe");
        while(forme2 > paint.nbForme() || forme2 <= 0){
            forme2 = askInt("Quelle autres formes voulez vous ajouter dans le groupe");
        }
        forme1--;
        forme2--;
        paint.newGroup(forme1, forme2);

    }

    /**
     * ask to the user to input the dimension of the drawing
     */
    private void askDimension() {
        int width = askInt("Entrer les dimensions, d'abord la largeur : ");
        int height = askInt("Entrer la hauteur : ");

        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Dimension incorrect ! Celles-ci doivent être strictement plus grandes" +
                    " que 0 \n largeur :" + width + "\n hauteur :" + height);
        }
        this.paint = new AsciiPaint(new Drawing(width, height));
    }


    /**
     * ask to the user an integer (int) he can't input something else
     * @param message string
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



    private void move(){
        System.out.println("Voici la liste des formes : ");

        int num = askInt("Quelle liste voulez vous déplacer ? ");
        int dx = askInt("De combien de X voulez-vous la déplacer ?");
        int dy = askInt("De combien de Y voulez-vous la déplacer ?");

        paint.move(num, dx, dy);
    }

    private String askCommand(){
        System.out.println("Entrer :\n* add : pour ajouter un élément. \n" +
                "* show : pour afficher le dessin.\n" +
                "* list : pour afficher la liste des formes de votre dessin.\n" +
                "* end : pour fermer le programme");
        Scanner clavier = new Scanner(System.in);
        String commandes = clavier.nextLine();
        return commandes;
    }
}
