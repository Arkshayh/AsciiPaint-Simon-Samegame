package asciipaint;

import java.util.Scanner;

public class Application {
    private AsciiPaint paint;

    //TODO faire tous les tests
    public static void main(String[] args) {
        Application app = new Application();
        app.start();
    }

    public void start(){
        boolean fin = false;
        String rep;

        askDimension();

        while (!fin){
            rep = askAddShowEnd("Entrer \"add\" pour ajouter un élément \nEntrer \"show\" pour afficher" +
                    " le dessin \nEntrer \"end\" pour arrêter le programme");
            switch (rep){
                case "ADD":
                    chooseForm(askForm("Quelle forme voulez-vous ajouter ? (c = cercle, r = rectangle, s = carré)"));
                    break;
                case "SHOW":
                    show();
                    break;
                default:
                    fin = true;
                    break;
            }
        }
    }

    private void askDimension(){

        int width = askInt("Entrer les dimensions, d'abord la largeur : ");
        int height = askInt("Entrer la hauteur : ");

        if(width < 1 || height < 1){
            throw new IllegalArgumentException("Dimension incorrect ! Celles-ci doivent être strictement plus grandes" +
                    " que 0 \n largeur :" + width + "\n hauteur :" + height);
        }

        this.paint = new AsciiPaint(new Drawing(width,height));
    }

    private void chooseForm(char c){
        switch (c){
            case 'C':
                addCircle();
                break;
            case 'R':
                addRectangle();
                break;
            case 'S':
                addSquare();
        }
    }

    private void addCircle(){
        int x,y;
        double radius;
        char couleur;

        Scanner clavier = new Scanner(System.in);

        System.out.println("Ajout d'un cercle :");
        x = askInt("Entrer les coordonnées X de son centre : ");

        y = askInt("Entrer les coordonnées Y de son centre : ");

        radius = askDouble("Entrer la taille du rayon du cercle");

        if(radius < 1){
            throw new IllegalArgumentException("Le rayon doit être >= 1 \n Rayon entré :" +radius);
        }

        System.out.println("Entrer sa couleur : ");
        couleur = clavier.next().charAt(0);

        paint.newCircle(x,y,radius,couleur);
    }

    private void addRectangle(){
        int x,y;
        double width, height;
        char color;

        System.out.println("Ajout rectangle : ");
        Scanner clavier = new Scanner(System.in);

        x = askInt("Entrer les coordonnées X de son point (en haut à gauche) : ");

        y = askInt("Entrer les coordonnées Y de son point (en haut à gauche) : ");

        width = askDouble("Entrer la largeur du rectangle :");

        System.out.println("Entrer la hauteur du rectangle :");
        height = clavier.nextDouble();

        if(width < 1 || height < 1){
            throw new IllegalArgumentException("Largeur/hauteur > 0 !! " + width +","+ height);
        }

        System.out.println("Entrer sa couleur : ");
        color = clavier.next().charAt(0);

        paint.newRectangle(x,y,width,height,color);
    }

    private void addSquare(){
        int x,y;
        double side;
        char color;

        Scanner clavier = new Scanner(System.in);
        x = askInt("Entrer les coordonnées X de son point (en haut à gauche) : ");

        y = askInt("Entrer les coordonnées Y de son point (en haut à gauche) : ");

        side = askDouble("Entrer la longueur d'un côté");

        if(side < 1){
            throw new IllegalArgumentException("Side doit être positif :" + side);
        }

        System.out.println("Entrer sa couleur : ");
        color = clavier.next().charAt(0);

        paint.newSquare(x,y,side,color);
    }

    private void show(){
        System.out.println(paint.asAscii());
    }

    private int askInt(String message){
        System.out.println(message);
        int a;
        Scanner clavier = new Scanner(System.in);

        while (!clavier.hasNextInt()){
            System.out.println("Erreur, veuillez réessayer :");
            clavier.next();
        }
        return clavier.nextInt();
    }

    private double askDouble(String message){
        System.out.println(message);
        double a;
        Scanner clavier = new Scanner(System.in);

        while (!clavier.hasNextDouble()){
            System.out.println("Erreur, veuillez réessayer :");
            clavier.next();
        }
        return clavier.nextDouble();
    }

    private static String askAddShowEnd(String message){
        System.out.println(message);
        Scanner clavier = new Scanner(System.in);
        String msg = clavier.nextLine();
        msg = msg.toUpperCase();

        while (msg.equals("ADD") == false && msg.equals("SHOW") == false && msg.equals("END") == false){
            System.out.println("Erreur, réessayer : ");
            msg = clavier.nextLine();
            msg = msg.toUpperCase();
        }
        return msg;

    }

    private char askForm(String message){
        System.out.println(message);
        Scanner clavier = new Scanner(System.in);
        String chara = clavier.nextLine();
        chara = chara.toUpperCase();
        while (chara.equals("C") == false && chara.equals("R") == false && chara.equals("S") == false){
            System.out.println("Erreur, réessayer : ");
            chara = clavier.nextLine();
            chara = chara.toUpperCase();
        }

        return chara.charAt(0);
    }
}
