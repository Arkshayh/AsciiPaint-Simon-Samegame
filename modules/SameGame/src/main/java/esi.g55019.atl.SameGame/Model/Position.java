package esi.g55019.atl.SameGame.Model;

public class Position {
    private int Ligne;
    private int Colonne;

    public Position(int ligne, int colonne) {
        this.Ligne = ligne;
        this.Colonne = colonne;
    }

    public int getColonne() {
        return Colonne;
    }

    public void setColonne(int colonne) {
        this.Colonne = colonne;
    }

    public int getLigne() {
        return Ligne;
    }

    public void setLigne(int ligne) {
        this.Ligne = ligne;
    }

    @Override
    public String toString() {
        return "(Ligne : " + Ligne + ", Colonne :" + Colonne +")";
    }
}
