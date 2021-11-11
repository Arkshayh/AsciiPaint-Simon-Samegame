package esi.g55019.atl.SameGame.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int ligne;
    private int colonne;
    private Bille[][] plateau;

    public Board(int ligne, int colonne, int nbColor) {
        if(nbColor < 3 || nbColor > 5){
            throw new IllegalArgumentException("Nbr de billes incorrect");
        }
        this.colonne = colonne;
        this.ligne = ligne;
        plateau = new Bille[ligne][colonne];
        initialiseBoard(nbColor);
    }

    public Board(int nbColor) {
        this.colonne = 16;
        this.ligne = 12;
        plateau = new Bille[ligne][colonne];
        initialiseBoard(nbColor);
    }

    private void initialiseBoard(int nbColor){
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++){
                plateau[i][j] = new Bille(randomColor(nbColor), new Position(i,j));
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
                if(plateau[i][j] == null){
                    System.out.printf("0 ");
                }
                else{
                    System.out.printf(plateau[i][j].getColor().getColor() + "0 " + "\u001B[0m");
                }
            }
            System.out.println();
        }
    }

    public void supprimerColorSetUp(Position position){
        boolean [][] tabVérif = new boolean[ligne][colonne];
        List<Position> elementASupprimer = new ArrayList<>();
        algoRécu(position, plateau[position.getColonne()][position.getLigne()].getColor() ,tabVérif, elementASupprimer);
        for (int i = 0; i < elementASupprimer.size(); i++) {
            plateau[elementASupprimer.get(i).getLigne()][elementASupprimer.get(i).getColonne()] = null;
        }
    }

    private void algoRécu(Position courante, Color color, boolean [][] tab, List<Position> aSupprimer){
        tab[courante.getLigne()][courante.getColonne()] = true;
        aSupprimer.add(courante);
        List<Position> voisins = getVoisin(courante);
        for (int i = 0; i < voisins.size(); i++) {
            if(isInside(voisins.get(i))){
                if(hasTheSameColor(voisins.get(i), color)){
                    if(!tab[voisins.get(i).getLigne()][voisins.get(i).getColonne()]){
                        aSupprimer.add(voisins.get(i));
                        algoRécu(voisins.get(i), color, tab, aSupprimer);
                    }
                }
            }
        }
    }

    private boolean isInside(Position pos){
        if(pos.getLigne() >= 0 && pos.getLigne() < ligne){
            if(pos.getColonne() >= 0 && pos.getColonne() < colonne){
                return true;
            }
        }
        return false;
    }

    private boolean hasTheSameColor(Position position, Color color){
        return plateau[position.getLigne()][position.getColonne()].getColor() == color;
    }


    private List<Position> getVoisin(Position pos){
        List<Position> voisins = List.of(
                new Position(pos.getLigne() + 1, pos.getColonne()),
                new Position(pos.getLigne() - 1, pos.getColonne()),
                new Position(pos.getLigne(), pos.getColonne() -1),
                new Position(pos.getLigne(), pos.getColonne() + 1)
        );
        return voisins;
    }

}

