package esi.g55019.atl.asciipaint.DPCommand;

public enum typeOfCommande {
    ADD_CIRCLE(6),
    ADD_RECTANGLE(7),
    ADD_SQUARE(6),
    ADD_LINE(7)
    ;

    private int longueur;

    private typeOfCommande(int longueur) {
        this.longueur = longueur;
    }

    public int getLongueur() {
        return longueur;
    }
}
