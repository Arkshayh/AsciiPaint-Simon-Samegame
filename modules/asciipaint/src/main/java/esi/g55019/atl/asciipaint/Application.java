package esi.g55019.atl.asciipaint;

import esi.g55019.atl.asciipaint.ShapePackage.Line;

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

    //TODO: groupe, le groupe crée va à la fin de la liste, et lorsqu'il est supprimé les formes ajouter à la fin de la
    //      liste

    /**
     * start the program, this method will manage the input of the user (if he want to end the programm/add shape/show
     * the drawing)
     */
    public void start() {
        boolean fin = false;
        String rep;

        askDimension();

        while (!fin) {
            rep = askAddShowEnd("Entrer \"add\" pour ajouter un élément \nEntrer \"list\" pour afficher la liste " +
                    "des formes disponible \nEntrer \"move\" pour déplacer une forme \nEntrer \"show\" pour afficher" +
                    " le dessin \nEntrer \"end\" pour arrêter le programme");
            switch (rep) {
                case "ADD":
                    chooseForm(askForm("Quelle forme voulez-vous ajouter ? (c = cercle, r = rectangle, s = carré, " +
                            "l = ligne)"));
                    break;
                case "SHOW":
                    show();
                    break;
                case "LIST":
                    showList();
                    break;
                case "MOVE":
                    move();
                    break;
                default:
                    fin = true;
                    break;
            }
        }
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
     * will add a circle/square/rectangle/line to the drawing
     * @param c char | the char will be choose by the user using the method askform
     */
    private void chooseForm(char c) {
        switch (c) {
            case 'C':
                addCircle();
                break;
            case 'R':
                addRectangle();
                break;
            case 'S':
                addSquare();
                break;
            case 'L':
                addLine();
        }
    }

    /**
     * add a line in the paint, the coordonate of 2 point will be asked and the color too;
     */
    private void addLine(){
        int x1, y1, x2, y2;
        char color;

        Scanner clavier = new Scanner(System.in);
        System.out.println("Ajout d'une ligne : ");
        x1 = askInt("Entrer les coordonnées X de son 1er point : ");
        y1 = askInt("Entrer les coordonnées Y de son 1er point : ");
        x2 = askInt("Entrer les coordonnées X de son 2ème point : ");
        y2 = askInt("Entrer les coordonnées Y de son 2ème point : ");

        System.out.println("Entrer sa couleur : ");
        color = clavier.next().charAt(0);
        paint.newLine(x1,y1,x2,y2,color);
    }

    /**
     * add a circle to the drawing, the user must define the attributes of the circle
     */
    private void addCircle() {
        int x, y;
        double radius;
        char couleur;

        Scanner clavier = new Scanner(System.in);
        System.out.println("Ajout d'un cercle :");
        x = askInt("Entrer les coordonnées X de son centre : ");
        y = askInt("Entrer les coordonnées Y de son centre : ");
        radius = askDouble("Entrer la taille du rayon du cercle");

        if (radius < 1) {
            throw new IllegalArgumentException("Le rayon doit être >= 1 \n Rayon entré :" + radius);
        }
        System.out.println("Entrer sa couleur : ");
        couleur = clavier.next().charAt(0);
        paint.newCircle(x, y, radius, couleur);
    }

    /**
     * add a rectangle to the drawing, the user must define the attributes of the rectangle
     */
    private void addRectangle() {
        int x, y;
        double width, height;
        char color;

        System.out.println("Ajout rectangle : ");
        Scanner clavier = new Scanner(System.in);
        x = askInt("Entrer les coordonnées X de son point (en haut à gauche) : ");
        y = askInt("Entrer les coordonnées Y de son point (en haut à gauche) : ");
        width = askDouble("Entrer la largeur du rectangle :");
        System.out.println("Entrer la hauteur du rectangle :");
        height = clavier.nextDouble();

        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Largeur/hauteur > 0 !! " + width + "," + height);
        }
        System.out.println("Entrer sa couleur : ");
        color = clavier.next().charAt(0);
        paint.newRectangle(x, y, width, height, color);
    }

    /**
     * add a square to the drawing, the user must define the attributes of the square
     */
    private void addSquare() {
        int x, y;
        double side;
        char color;

        Scanner clavier = new Scanner(System.in);
        x = askInt("Entrer les coordonnées X de son point (en haut à gauche) : ");
        y = askInt("Entrer les coordonnées Y de son point (en haut à gauche) : ");
        side = askDouble("Entrer la longueur d'un côté");
        if (side < 1) {
            throw new IllegalArgumentException("Side doit être positif :" + side);
        }
        System.out.println("Entrer sa couleur : ");
        color = clavier.next().charAt(0);
        paint.newSquare(x, y, side, color);
    }

    /**
     * print the drawing in the console
     */
    private void show() {
        System.out.println(paint.asAscii());
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

    /**
     * ask to the user a number (double) he can't input something else
     * @param message string
     * @return double
     */
    private double askDouble(String message) {
        System.out.println(message);
        Scanner clavier = new Scanner(System.in);

        while (!clavier.hasNextDouble()) {
            System.out.println("Erreur, veuillez réessayer :");
            clavier.next();
        }
        return clavier.nextDouble();
    }

    /**
     * the user choose if he want to add a shape/show the drawing or end the programm. He must input ADD SHOW OR END
     * his choose will be return
     * @param message String
     * @return String
     */
    private String askAddShowEnd(String message) {
        System.out.println(message);
        Scanner clavier = new Scanner(System.in);
        String msg = clavier.nextLine();
        msg = msg.toUpperCase();

        while (!msg.equals("ADD") && !msg.equals("SHOW") && !msg.equals("END") && !msg.equals("LIST")
                && !msg.equals("MOVE")) {
            System.out.println("Erreur, réessayer : ");
            msg = clavier.nextLine();
            msg = msg.toUpperCase();
        }
        return msg;

    }

    /**
     * The user choose a form to add, he must input C for a circle, R for a rectangle and S for a square
     * @param message string
     * @return char
     */
    private char askForm(String message) {
        System.out.println(message);
        Scanner clavier = new Scanner(System.in);
        String chara = clavier.nextLine();
        chara = chara.toUpperCase();
        while (!chara.equals("C") && !chara.equals("R") && !chara.equals("S") && !chara.equals("L")) {
            System.out.println("Erreur, réessayer : ");
            chara = clavier.nextLine();
            chara = chara.toUpperCase();
        }
        return chara.charAt(0);
    }

    private void showList(){
        paint.showList();
    }

    private void move(){
        System.out.println("Voici la liste des formes : ");
        showList();
        int num = askInt("Quelle liste voulez vous déplacer ? ");
        int dx = askInt("De combien de X voulez-vous la déplacer ?");
        int dy = askInt("De combien de Y voulez-vous la déplacer ?");

        paint.move(num, dx, dy);
    }
}
