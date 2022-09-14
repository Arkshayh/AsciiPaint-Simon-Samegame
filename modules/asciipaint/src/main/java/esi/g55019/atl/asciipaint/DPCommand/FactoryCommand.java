package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;
import esi.g55019.atl.asciipaint.DPComposite.Component;
import esi.g55019.atl.asciipaint.DPComposite.Composite;
import esi.g55019.atl.asciipaint.Point;

import java.util.Arrays;

// @pbt implementation of factory is shaky


/**
 * The factory will checked if the user enter a correct command if it's not the case it will return an error
 * If it's the case it will return a Command based of what the user inputed
 * @author Cotton Ian g55019
 */
public class FactoryCommand {
    // @pbt why factory must have paint ?
    private AsciiPaint paint;
    private String[] commandeIni;
    private String[] commandeCorrection;
    private int[] commandeCorrectionForGroup;
    private int index = 0;
    private int indexForDeleteOrUngroupOrMove;
    private boolean undo = false;
    private char colorUndo;
    private int nbFormeUndoUngroup;
    private Component oldComponentForUndoDelete;
    private Point forUndoMove;

    /**
     * Constructor
     * @param paint AsciiPaint
     */
    public FactoryCommand(AsciiPaint paint) {
        this.paint = paint;
    }

    /**
     * set the commandIni attribute by transforming it in a tab of String, then will called createComman()
     * @param commande String
     */
    public void setCommandeIni(String commande){
        commandeIni = commande.split(" ");
        createCommande();
    }

    /**
     * Will create a command
     * called the erreurCommand if the input of the user is incorrect
     */
    private void createCommande(){
        index = 0;
        try {
            switch(commandeIni[0]){
                case "add":
                    index++;
                    checkAdd();
                    break;
                case "move":
                    index++;
                    checkMove();
                case "show":
                case "list":
                case "end":
                    commandeCorrection = commandeIni;
                    break;
                case "color":
                    index++;
                    checkColorCommand();
                    break;
                case "delete":
                    index++;
                    checkDelete();
                    break;
                case "group":
                    checkGroup();
                    break;
                case "ungroup":
                    checkUngroup();
                    break;
                case "undo":
                    setUndo();
                    break;
                case "redo":
                    break;
                default:
                    erreurCommande("Commande inconnue");
                    break;
            }
        }
        catch (Exception e){
            erreurCommande("");
        }
    }

    /**
     * check if the move command enter by the user is correct otherwise erreurCommane is called
     */
    private void checkMove(){
        checkInt();
        if(isInside(Integer.parseInt(commandeIni[index-1]) -1)){
            checkPoint();

            indexForDeleteOrUngroupOrMove = Integer.parseInt(commandeIni[1]);
            forUndoMove = new Point(Integer.parseInt(commandeIni[2]), Integer.parseInt(commandeIni[3]));
            commandeCorrection = commandeIni;
        }
        else{
            erreurCommande("Erreur move");
        }
    }

    /**
     * change the undo attribute
     */
    public void setUndo() {
        if(!undo){
            undo = true;
        }
        else {
            undo = false;
        }
    }

    /**
     * getter for undo
     * @return boolean
     */
    public boolean isUndo() {
        return undo;
    }

    /**
     * check if the color command of the user is correct otherwise erreurCommane is called
     */
    private void checkColorCommand(){
        if(commandeIni.length != 3){
            erreurCommande("Commande incorrect pour color");
        }
        checkInt();
        int nb = Integer.parseInt(commandeIni[index -1]) -1;
        if(!isInside(nb)){
            erreurCommande("Erreur dans la commande color");
        }
        else{
            commandeIni[1] = Integer.toString(nb);
            checkColor();
            colorUndo = paint.getShapeAt(nb).getColor();
            commandeCorrection = commandeIni;
        }
    }

    /**
     * check if the delete command of the user is correct otherwise erreurCommane is called
     */
    private void checkDelete(){
        if(commandeIni.length != 2){
            erreurCommande("Commande incorrect pour un delete");
        }
        else{
            checkInt();
            if(isInside(Integer.parseInt(commandeIni[index -1]) -1)){
                indexForDeleteOrUngroupOrMove = Integer.parseInt(commandeIni[index -1]) -1;
                oldComponentForUndoDelete = paint.getShapeAt(Integer.parseInt(commandeIni[index -1]) -1);
                commandeCorrection = commandeIni;
            }
            else{
                erreurCommande("Vous voulez supprimer une forme ou un groupe inexistant");
            }
        }
    }

    /**
     * check if the ungroup command of the user is correct otherwise erreurCommane is called
     */
    private void checkUngroup(){
        if(commandeIni.length != 2){
            erreurCommande("Commande incorrect pour un ungroup");
        }
        else{
            try {
                int nb = Integer.parseInt(commandeIni[1]) -1;
                if(nb < 0 || nb > paint.nbForme() -1){
                    erreurCommande("Votre groupe n'existe pas");
                }
                if(!isAGroup(nb)){
                    erreurCommande("Vous voulez dégroupe une forme et non un groupe");
                }
                nbFormeUndoUngroup = ((Composite) paint.getShapeAt(nb)).getSize();
                indexForDeleteOrUngroupOrMove =nb;
                commandeCorrection = commandeIni;
            }
            catch (Exception e){
                erreurCommande("Erreur commande ungroup");
            }
        }
    }

    /**
     * check if the Component at the given position is a Composite
     * @param emplacement int
     * @return  boolean
     */
    private boolean isAGroup(int emplacement){
        if(paint.getShapeAt(emplacement) instanceof Composite){
            return true;
        }
        return false;
    }

    /**
     * check if the group command of the user is correct otherwise erreurCommane is called
     */
    private void checkGroup(){
        if(commandeIni.length < 3){
            erreurCommande("Taille de la commande rentrée trop petite");
        }
        else{
            int [] tab = new int[commandeIni.length-1];
            try{
                for (int i = 1; i < commandeIni.length; i++) {
                    tab[i-1] = Integer.parseInt(commandeIni[i]) -1;
                }
                if(!hasDoublon(tab)){
                    Arrays.sort(tab);

                    if(!isInside(tab[0])){
                        erreurCommande("Le premier élément que vous voulez grouper n'est pas dans la liste");
                    }
                    else if(!isInside(tab[tab.length-1])){
                        erreurCommande("Le dernier élément que vous voulez grouper n'est pas dans la liste");
                    }

                    commandeCorrection = commandeIni;
                    commandeCorrectionForGroup = tab;
                }
                else{
                    erreurCommande("Vous voulez ajouter un même élément plusieurs fois dans le même groupe");
                }
            }catch (Exception e){
                erreurCommande("");
            }
        }
    }

    /**
     * check if the tab has duplicate in it
     * @param tab int[]
     * @return boolean
     */
    private boolean hasDoublon(int [] tab){
        for (int i = 0; i < tab.length; i++) {
            for (int j = i+1; j < tab.length; j++) {
                if(tab[i] == tab[j]){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Display the message in parameter and throw an exception
     * @param erreurMsg String
     * @throws IllegalArgumentException
     */
    private void erreurCommande(String erreurMsg){
        System.out.println(erreurMsg);
        throw new IllegalArgumentException();
    }

    /**
     * check if the index of a component given is in the drawing
     * @param indexForme int
     * @return boolean
     */
    private boolean isInside(int indexForme){
        if(indexForme < 0 || indexForme > paint.nbForme()-1){
            return false;
        }
        return true;
    }

    /**
     * check if the add command of the user is correct otherwise erreurCommane is called
     */
    private void checkAdd(){
        try {
            switch (commandeIni[index]){
                case "circle":
                    if(commandeIni.length != typeOfCommande.ADD_CIRCLE.getLongueur()){
                        erreurCommande("Longueur de la commande pour ajouter un cercle incorrecte");
                    }
                    else {
                        index++;
                        checkPoint();
                        checkInt();
                        checkColor();
                        commandeCorrection = commandeIni;
                    }
                    break;
                case "square":
                    if(commandeIni.length != typeOfCommande.ADD_SQUARE.getLongueur()){
                        erreurCommande("Longueur de la commande pour ajouter un carré incorrecte");
                    }
                    else{
                        index++;
                        checkPoint();
                        checkInt();
                        checkColor();
                        commandeCorrection = commandeIni;
                    }
                    break;
                case "line":
                    if(commandeIni.length != typeOfCommande.ADD_LINE.getLongueur()){
                        erreurCommande("Longueur de la commande pour ajouter une ligne incorrecte");
                    }
                    else{
                        index++;
                        checkPoint();
                        checkPoint();
                        checkColor();
                        commandeCorrection = commandeIni;
                    }
                    break;
                case "rectangle":
                    if(commandeIni.length != typeOfCommande.ADD_RECTANGLE.getLongueur()){
                        erreurCommande("Longueur de la commande pour ajouter un rectangle incorrecte");
                    }
                    else{
                        index++;
                        checkPoint();
                        checkPoint();
                        checkColor();
                        commandeCorrection = commandeIni;
                    }
                    break;
                default:
                    erreurCommande("Vous voulez ajouter une forme inconnu :(");
                    break;
            }
        }
        catch (Exception e){
            erreurCommande("");
        }
    }

    /**
     * check if the value at the commandeIni[index] and commandeIni[index + 1] are an integer otherwise erreurCommane is called
     */
    private void checkPoint(){
        try{
            Integer.parseInt(commandeIni[index]);
            Integer.parseInt(commandeIni[index + 1]);
            index = index + 2;
        }
        catch (Exception e){
            erreurCommande("");
        }
    }

    /**
     * check if the value at the commandeIni[index] are an integer otherwise erreurCommane is called
     */
    private void checkInt(){
        try {
            Integer.parseInt(commandeIni[index]);
            index++;
        }
        catch (Exception e){
            erreurCommande("");
        }
    }

    /**
     * update paint
     * @param paint AsciiPaint
     */
    public void setPaint(AsciiPaint paint){
        this.paint = paint;
    }

    /**
     * check if the value at the commandIni[index] is a char otherwise erreurCommane is called
     */
    private void checkColor(){
        try{
            commandeIni[index].charAt(0);
            index++;
        }
        catch (Exception e){
            erreurCommande("");
        }
    }

    /**
     * return a command based of the input of the user
     * @return Command
     */
    public Command getCommandeCorrection(){
        Command command;
        switch (commandeCorrection[0]) {
            case "add":
                command = new AddCommand(paint, commandeCorrection);
                break;
            case "move":
                command = new MoveCommand(paint, Integer.parseInt(commandeCorrection[1]),
                        Integer.parseInt(commandeCorrection[2]), Integer.parseInt(commandeCorrection[3]), forUndoMove,
                        indexForDeleteOrUngroupOrMove);
                break;
            case "show":
                command = new ShowCommand(paint);
                break;
            case "list":
                command = new ListCommand(paint);
                break;
            case "color":
                command = new ColorCommand(paint, commandeCorrection, colorUndo);
                break;
            case "group":
                command = new GroupCommand(paint, commandeCorrectionForGroup);
                break;
            case "ungroup":
                command = new UngroupCommand(paint, indexForDeleteOrUngroupOrMove, nbFormeUndoUngroup);
                break;
            case "delete":
                command = new DeleteCommand(paint, indexForDeleteOrUngroupOrMove, oldComponentForUndoDelete);
                break;
            default:
                command = new EndCommand(paint);
                break;
        }
        return command;
    }

}
