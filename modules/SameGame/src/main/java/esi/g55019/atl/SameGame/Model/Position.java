package esi.g55019.atl.SameGame.Model;

/**
 * this class represent a position.
 * A position has the number of his line, the number of his column
 */
public class Position {
    private int Ligne;
    private int Colonne;

    /**
     * constructor
     * @param ligne int
     * @param colonne int
     */
    public Position(int ligne, int colonne) {
        this.Ligne = ligne;
        this.Colonne = colonne;
    }

    /**
     * getter for colonne
     * @return int
     */
    public int getColonne() {
        return Colonne;
    }

    /**
     * getter for ligne
     * @return int
     */
    public int getLigne() {
        return Ligne;
    }

    @Override
    public String toString() {
        return "(Ligne : " + Ligne + ", Colonne :" + Colonne +")";
    }
}
