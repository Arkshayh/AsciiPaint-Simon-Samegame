package esi.g55019.atl.SameGame.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int colonne;
    private int ligne;
    private Bille[][] tab;

    public Board(int colonne, int ligne, int nbColor) {
        if(nbColor < 3 || nbColor > 5){
            throw new IllegalArgumentException("Nbr de billes incorrect");
        }
        this.colonne = colonne;
        this.ligne = ligne;
        tab = new Bille[ligne][colonne];
        initialiseBoard(nbColor);
    }

    public Board(int nbColor) {
        this.colonne = 16;
        this.ligne = 12;
        tab = new Bille[ligne][colonne];
        initialiseBoard(nbColor);
    }

    public Board(int nbColor, boolean yo){
        this.colonne = 16;
        this.ligne = 12;
        tab = new Bille[ligne][colonne];
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++){
                tab[i][j] = new Bille(Color.RED, new Position(i,j));
            }
        }
        tab[0][0] = new Bille(Color.BLUE, new Position(0,0));
        tab[0][1] = new Bille(Color.BLUE, new Position(0,1));
        tab[1][1] = new Bille(Color.BLUE, new Position(1,1));
        tab[1][2] = new Bille(Color.BLUE, new Position(1,2));
        tab[2][0] = new Bille(Color.BLUE, new Position(2,0));
        tab[2][1] = new Bille(Color.BLUE, new Position(2,1));
        tab[2][2] = new Bille(Color.BLUE, new Position(2,2));
        tab[2][3] = new Bille(Color.BLUE, new Position(2,3));
    }

    private void initialiseBoard(int nbColor){
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++){
                tab[i][j] = new Bille(randomColor(nbColor), new Position(i,j));
            }
        }
    }

    private Color randomColor(int nBcolor){
        Random rand = new Random();
        int nombreAleatoire = rand.nextInt(nBcolor);
        return Color.values()[nombreAleatoire];
    }

    public void afficherPlateau(){
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                if(tab[i][j] == null){
                    System.out.printf("0 ");
                }
                else{
                    System.out.printf(tab[i][j].getColor().getColor() + "0 " + "\u001B[0m");
                }
            }
            System.out.println();
        }
    }

    public void supprimerColorSetUp(Position position){
        boolean [][] tabVérif = new boolean[ligne][colonne];
        System.out.println("Couleur : " + tab[position.getColonne()][position.getLigne()].getColor());
        algoRécu(position, tab[position.getColonne()][position.getLigne()].getColor() ,tabVérif);
    }

    private void algoRécu(Position courante, Color color, boolean [][] tabVérif){
        List<Position> voisin = getVoisin(courante);
        tab[courante.getLigne()][courante.getColonne()] = null;
        afficherPlateau();
        System.out.println("------------------------");
        for (int i = 0; i < voisin.size(); i++) {
            // var v = voisin.get(i)
            if(!tabVérif[courante.getLigne()][courante.getColonne()]){
                if(isInside(voisin.get(i))){
                    if(!isNull(voisin.get(i))){
                        if(isDeMêmeCouleur(voisin.get(i), color)){
                            tabVérif[courante.getLigne()][courante.getColonne()] = true;
                            algoRécu(voisin.get(i), color, tabVérif);
                        }
                    }
                }
            }
        }
    }

    private boolean isDeMêmeCouleur(Position pos, Color color){
        return tab[pos.getLigne()][pos.getColonne()].getColor() == color;
    }

    public boolean isInside(Position pos){
        if(pos.getLigne() >= 0 && pos.getLigne() < ligne){
            if(pos.getColonne() >= 0 && pos.getColonne() < colonne){
                return true;
            }
        }
        return false;
    }

    private boolean isNull(Position pos){
        return tab[pos.getColonne()][pos.getLigne()] == null;
    }

    private List<Position> getVoisin(Position pos){
        List<Position> voisin = new ArrayList<>();
        List<String> list = List.of(
                "Haut",
                "Bas",
                "Gauche",
                "Droite"
        );

        voisin.add(new Position(pos.getLigne() -1, pos.getColonne()));
        voisin.add(new Position(pos.getLigne() + 1, pos.getColonne()));
        voisin.add(new Position(pos.getLigne(), pos.getColonne() -1));
        voisin.add(new Position(pos.getLigne(), pos.getColonne() + 1));

        for (int i = 0; i < voisin.size(); i++) {
            System.out.println(list.get(i));
            System.out.println(voisin.get(i));
        }
        return voisin;
    }

}
