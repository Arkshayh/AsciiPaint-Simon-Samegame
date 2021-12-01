package esi.g55019.atl.SameGame.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int ligne;
    private int colonne;
    private Bille[][] plateau;
    private int score;
    private int nbColor;

    public Board(int ligne, int colonne, int nbColor) {
        if(nbColor < 3 || nbColor > 5){
            throw new IllegalArgumentException("Nbr de billes incorrect");
        }
        this.nbColor = nbColor;
        this.colonne = colonne;
        this.ligne = ligne;
        plateau = new Bille[ligne][colonne];
        initialiseBoard(nbColor);
    }

    public Board(int ligne, int colonne, Bille[][] plateau, int score){
        this.ligne = ligne;
        this.colonne = colonne;
        this.score = score;
        this.plateau = new Bille[ligne][colonne];
        for (int i = 0; i <ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                this.plateau[i][j] = plateau[i][j];
            }
        }
    }

    public int getScore() {
        return score;
    }

    private void initialiseBoard(int nbColor){
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++){
                plateau[i][j] = new Bille(randomColor(nbColor));
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

    public boolean supprimerColorSetUp(Position position){
        boolean [][] tabVérif = new boolean[ligne][colonne];
        List<Position> elementASupprimer = new ArrayList<>();
        if(plateau[position.getLigne()][position.getColonne()] == null){
            System.out.println("Cette bille a déjà été enlevée ! ");
            return false;
        }
        algoRécu(position, plateau[position.getLigne()][position.getColonne()].getColor() ,tabVérif, elementASupprimer);
        if(elementASupprimer.size() > 1){
            for (int i = 0; i < elementASupprimer.size(); i++) {
                plateau[elementASupprimer.get(i).getLigne()][elementASupprimer.get(i).getColonne()] = null;
            }
            score = score + (elementASupprimer.size() * elementASupprimer.size()) - elementASupprimer.size();
            return true;
        }
        else{
            System.out.println("Cette bille ne peut être enlevées car elle n'a pas de voisin");
            return false;
        }
    }

    public List<Position> getVoisinASupprimer(Position position){
        boolean [][] tabVérif = new boolean[ligne][colonne];
        List<Position> elementASupprimer = new ArrayList<>();
        algoRécu(position, plateau[position.getLigne()][position.getColonne()].getColor() ,tabVérif, elementASupprimer);
        return elementASupprimer;
    }

    private void algoRécu(Position courante, Color color, boolean [][] tab, List<Position> aSupprimer){
        tab[courante.getLigne()][courante.getColonne()] = true;
        aSupprimer.add(courante);
        List<Position> voisins = getVoisin(courante);
        for (int i = 0; i < voisins.size(); i++) {
            if(isInside(voisins.get(i))){
                if(!isNull(voisins.get(i))){
                    if(hasTheSameColor(voisins.get(i), color)){
                        if(!tab[voisins.get(i).getLigne()][voisins.get(i).getColonne()]){
                            algoRécu(voisins.get(i), color, tab, aSupprimer);
                        }
                    }
                }
            }
        }
    }

    private boolean isNull(Position pos){
        return plateau[pos.getLigne()][pos.getColonne()] == null;
    }

    public boolean isInside(Position pos){
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

    public void faireTomberBille(){
        for (int j = 0; j < plateau[0].length; j++) {
            for (int i = 0; i < plateau.length; i++) {
                if(plateau[i][j] == null){
                    for (int k = 0; k < i; k++) {
                        if(i-k-1 >=0){
                            plateau[i-k][j] = plateau[i-k-1][j];
                        }
                    }
                    plateau[0][j] = null;
                }
            }
        }
    }

    public void concatener(){
        for (int j = 0; j < plateau[0].length; j++) { //J = colonne 1
            if(plateau[plateau.length-1][j] == null && j+1 < plateau[0].length){
                for (int j2 = j+1; j2 < plateau[0].length; j2++) { //colonne 2
                    if(plateau[plateau.length-1][j2] != null){
                        echangerColonnes(j, j2);
                        break;
                    }
                }
            }
        }
    }

    private void echangerColonnes(int c1, int c2){
        for (int i = 0; i < plateau.length; i++) {
            plateau[i][c1] = plateau[i][c2];
            plateau[i][c2] = null;
        }
    }

    public boolean isFinish(){
        List<Position> voisins;
        Position currentVoisin;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[0].length; j++) {
                if(plateau[i][j] != null){
                    voisins = getVoisin(new Position(i,j));
                    for (int k = 0; k < voisins.size(); k++) {
                        currentVoisin = voisins.get(k);
                        if(isInside(currentVoisin)){
                            if(!isNull(currentVoisin)){
                                if(hasTheSameColor(currentVoisin, plateau[i][j].getColor())){
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isWin(){
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                if(plateau[i][j] != null){
                    return false;
                }
            }
        }
        return true;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public Bille[][] getPlateau() {
        return plateau;
    }
}

