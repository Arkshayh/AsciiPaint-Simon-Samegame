package esi.g55019.atl.SameGame.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represent the board of the game.
 * The board has at least 5 row or column and maximum 20 row or column.
 * A board has also a number of color (between 3 and 5) and a score !
 * The board is a 2D array of Bille.
 */
public class Board {
    private int row;
    private int column;
    private Bille[][] plateau;
    private int score;
    private int nbColor;

    /**
     * Constructor
     * @param row int
     * @param column int
     * @param nbColor int
     */
    public Board(int row, int column, int nbColor) {
        if(row < 5 || row > 20 || column <5 || column > 20 || nbColor < 3 || nbColor > 5){
            throw new IllegalArgumentException("Erreur : nombre de lignes/colonnes/bille incorrect" );
        }
        this.nbColor = nbColor;
        this.column = column;
        this.row = row;
        plateau = new Bille[row][column];
        initialiseBoard(nbColor);
    }

    /**
     * Second constructor to make a defensive copie of another Board
     * @param row int
     * @param column int
     * @param plateau Bille[][]
     * @param score int
     */
    public Board(int row, int column, Bille[][] plateau, int score){
        this.row = row;
        this.column = column;
        this.score = score;
        this.plateau = new Bille[row][column];
        for (int i = 0; i <row; i++) {
            for (int j = 0; j < column; j++) {
                this.plateau[i][j] = plateau[i][j];
            }
        }
    }

    /**
     * Constructor for test
     */
    public Board(){
        this.row = 5;
        this.column = 5;
        this.score = 0;
        this.plateau = new Bille[5][5];
        for (int i = 0; i < plateau[0].length; i++) {
            plateau[0][i] = new Bille(Color.BLUE);
        }
        for (int i = 0; i < plateau[0].length; i++) {
            plateau[1][i] = new Bille(Color.RED);
        }
        for (int i = 0; i < plateau[0].length; i++) {
            plateau[2][i] = new Bille(Color.GREEN);
        }
        for (int i = 0; i < plateau[0].length; i++) {
            plateau[3][i] = new Bille(Color.YELLOW);
        }
        plateau[4][0] = new Bille(Color.BLUE);
        plateau[4][1] = new Bille(Color.RED);
        plateau[4][2] = new Bille(Color.GREEN);
        plateau[4][3] = new Bille(Color.YELLOW);
        plateau[4][4] = new Bille(Color.PURPLE);

        for (int i = 0; i < plateau.length; i++) {
            plateau[i][3] = new Bille(Color.YELLOW);
        }

    }

    /**
     * This method will add a Bille with a random color in the board
     * @param nbColor int
     */
    private void initialiseBoard(int nbColor){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++){
                plateau[i][j] = new Bille(randomColor(nbColor));
            }
        }
    }

    /**
     * return a random color
     * @param nBcolor int
     * @return Color
     */
    private Color randomColor(int nBcolor){
        Random rand = new Random();
        int nombreAleatoire = rand.nextInt(nBcolor);
        return Color.values()[nombreAleatoire];
    }

    /**
     * This method receive a position and removes all adjacent balls of the same color of the Board.
     * The method return a boolean true if the Bille has at least one adjacent ball of the same color  otherwise false.
     * @param position Position
     * @return boolean
     */
    public boolean  supprimerColorSetUp(Position position){
        boolean [][] tabVerif = new boolean[row][column];
        List<Position> elementASupprimer = new ArrayList<>();
        if(plateau[position.getLigne()][position.getColonne()] == null){
            // @pbt no sysout in model
            System.out.println("Cette bille a déjà été enlevée ! ");
            return false;
        }
        // @pbt english
        // @pbt long lines
        algoRécu(position, plateau[position.getLigne()][position.getColonne()].getColor() ,tabVerif, elementASupprimer);
        if(elementASupprimer.size() > 1){
            for (int i = 0; i < elementASupprimer.size(); i++) {
                plateau[elementASupprimer.get(i).getLigne()][elementASupprimer.get(i).getColonne()] = null;
            }
            score = score
                    + (elementASupprimer.size() * elementASupprimer.size())
                    - elementASupprimer.size();
            return true;
        }
        else{
            System.out.println("Cette bille ne peut être enlevées car elle n'a pas de voisin");
            return false;
        }
    }

    /**
     * return a list of Position of the each ajacent Bille of the same color  of the given position + itself
     * So if they are 3 neighbor of the same color -> list.size = 4
     * @param position Position
     * @return List of Position
     */
    public List<Position> getVoisinASupprimer(Position position){
        boolean [][] tabVérif = new boolean[row][column];
        List<Position> elementASupprimer = new ArrayList<>();
        algoRécu(position, plateau[position.getLigne()][position.getColonne()].getColor() ,tabVérif, elementASupprimer);
        return elementASupprimer;
    }

    /**
     * add to the given list, each Bille of the same color adjacent.
     * This method need the color, the current position and an array of boolean.
     * @param courante Position
     * @param color Color
     * @param tab boolean[][]
     * @param aSupprimer List of Position
     */
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

    /**
     * return a boolean true if the given position is null in the plateau attribute otherwise false
     * @param pos Position
     * @return boolean
     */
    // @pbt useless
    private boolean isNull(Position pos){
        return plateau[pos.getLigne()][pos.getColonne()] == null;
    }

    /**
     * return a boolean true if the given position is in the Board otherwise false
     * @param pos
     * @return
     */
    public boolean isInside(Position pos){
        if(pos.getLigne() >= 0 && pos.getLigne() < row){
            if(pos.getColonne() >= 0 && pos.getColonne() < column){
                return true;
            }
        }
        return false;
    }

    /**
     * Return a boolean, true if the Bille at the given position has the same color of the color given
     * @param position Position
     * @param color Color
     * @return boolean
     */
    private boolean hasTheSameColor(Position position, Color color){
        return plateau[position.getLigne()][position.getColonne()].getColor() == color;
    }

    /**
     * return a list position of each neighbor of the given position. The list can contain some position who are OOB.
     * @param pos Position
     * @return List of Position
     */
    private List<Position> getVoisin(Position pos){
        List<Position> voisins = List.of(
                new Position(pos.getLigne() + 1, pos.getColonne()),
                new Position(pos.getLigne() - 1, pos.getColonne()),
                new Position(pos.getLigne(), pos.getColonne() -1),
                new Position(pos.getLigne(), pos.getColonne() + 1)
        );
        return voisins;
    }

    /**
     * Change the position of the Bille in the plateau.
     * Null locations are moved to the top of the array and other items are moved to the bottom
     */
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

    /**
     * each column who constain only null are moved to the right of the plateau the other are move to the left
     */
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

    /**
     * exchange the position of 2 column of the plateau. This method need the index of the 2 column
     * @param c1 int
     * @param c2 int
     */
    private void echangerColonnes(int c1, int c2){
        for (int i = 0; i < plateau.length; i++) {
            plateau[i][c1] = plateau[i][c2];
            plateau[i][c2] = null;
        }
    }

    /**
     * return true if the board is empty or  if all the remaining balls no longer have neighbors of the same color
     * @return boolean
     */
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

    /**
     * return true if the board only contain null
     * @return boolean
     */
    public boolean isWin(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if(plateau[i][j] != null){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * fill the plateau with null
     */
    public void giveUp(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                plateau[i][j] = null;
            }
        }
    }

    /**
     * Calculate the number of Billes remaining on the board.
     * @return int
     */
    public int nbOfBillesRemaining(){
        int nbIni = row*column;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if(plateau[i][j] == null){
                    nbIni--;
                }
            }
        }
        return nbIni;
    }

    /**
     * getter for row
     * @return int
     */
    public int getRow() {
        return row;
    }

    /**
     * getter for column
     * @return int
     */
    public int getColumn() {
        return column;
    }

    /**
     * getter for plateau
     * @return Bille[][]
     */
    public Bille[][] getPlateau() {
        return plateau;
    }

    /**
     * getter for the score
     * @return int
     */
    public int getScore() {
        return score;
    }

    /**
     * to String method
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder()  ;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if(plateau[i][j] == null){
                    string.append("0 ");
                }
                else{
                    string.append(plateau[i][j].getColor().getColor()).append("0 ").append("\u001B[0m");
                }
            }
            string.append("\n");
        }
        return string.toString();
    }

    /**
     * Equals method for test, this method only compare the plateau
     * @param other Bille[][]
     * @return boolean
     */
    public boolean equalsForTest(Bille[][] other){
        if(other.length != this.plateau.length || other[0].length != this.plateau[0].length){
            return false;
        }
        for (int i = 0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[0].length; j++) {
                if(other[i][j] == null && this.plateau[i][j] != null){
                    return false;
                }
                if(other[i][j] != null && this.plateau[i][j] == null){
                    return false;
                }
                if (other[i][j] == null && this.plateau[i][j] ==null){

                }
                else if(other[i][j].getColor().getColor() != this.plateau[i][j].getColor().getColor()){
                    return false;
                }
            }
        }
        return true;
    }
}

