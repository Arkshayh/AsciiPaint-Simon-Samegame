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

        Scanner clavier = new Scanner(System.in);

        while (!fin){
            System.out.println("Entrer \"add\" pour ajouter un élément \nEntrer \"show\" pour afficher" +
                    " le dessin \nEntrer \"end\" pour arrêter le programme");

            //TODO méthode qui renvoie un string de manière sure + le string doit être add show, end
            rep = clavier.nextLine().toUpperCase();
            switch (rep){
                case "ADD":
                    chooseForm(askForm());
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

    private char askForm(){
        System.out.println("Quelle forme voulez-vous ajouter ? (c = cercle, r = rectangle, s = carré");
        Scanner clavier = new Scanner(System.in);
        char form = clavier.next().toUpperCase().charAt(0);

        if(form != 'C' || form != 'R' || form != 'S'){
            throw new IllegalArgumentException("Erreur d'entrée : " + form);
        }
        return form;
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

        //TODO askDouble à faire
        radius = clavier.nextDouble();

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

        //TODO double
        System.out.println("Entrer la largeur du rectangle :");
        width = clavier.nextDouble();

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

        //TODO Double
        System.out.println("Entrer la longueur d'un côté");
        side = clavier.nextDouble();

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
}
