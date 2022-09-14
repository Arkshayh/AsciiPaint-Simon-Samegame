package esi.g55019.atl.asciipaint.DPCommand;

/**
 * this interface give the size of the 4 add command
 * @author Ian Cotton g55019
 */
// @pbt class name CamelCase
public enum typeOfCommande {
    ADD_CIRCLE(6),
    ADD_RECTANGLE(7),
    ADD_SQUARE(6),
    ADD_LINE(7)
    ;

    private int longueur;

    typeOfCommande(int longueur) {
        this.longueur = longueur;
    }

    public int getLongueur() {
        return longueur;
    }
}
