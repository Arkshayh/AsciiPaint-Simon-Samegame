package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;
import esi.g55019.atl.asciipaint.DPComposite.Composite;

import java.util.Arrays;


public class FactoryCommand {

    private AsciiPaint paint;
    private String[] commandeIni;
    private String[] commandeCorrection;
    private int[] commandeCorrectionForGroup;
    private int index = 0;


    public FactoryCommand(AsciiPaint paint) {
        this.paint = paint;
    }

    public void setCommandeIni(String commande){
        commandeIni = commande.split(" ");
        createCommande();
    }

    private void createCommande(){
        index = 0;
        try {
            switch(commandeIni[0]){
                case "add":
                    index++;
                    checkAdd();
                    break;
                case "show":
                case "list":
                case "end":
                    commandeCorrection = commandeIni;
                    break;
                case "color":
                    index++;
                    checkColorCommand();
                    break;
                case "group":
                    checkGroup();
                    break;
                case "ungroup":
                    checkUngroup();
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
            commandeCorrection = commandeIni;
        }
    }

    private void checkUngroup(){
        if(commandeIni.length != 2){
            erreurCommande("Commande incorrect pour un ungroup");
        }
        else{
            int [] tab = new int[1];
            try {
                tab[0] = Integer.parseInt(commandeIni[1]) -1;
                if(tab[0] < 0 || tab[0] > paint.nbForme() -1){
                    erreurCommande("Votre groupe n'existe pas");
                }
                if(!isAGroup(tab[0])){
                    erreurCommande("Vous voulez dégroupe une forme et non un groupe");
                }
                commandeCorrection = commandeIni;
                commandeCorrectionForGroup = tab;
            }
            catch (Exception e){
                erreurCommande("Erreur commande ungroup");
            }

        }
    }

    private boolean isAGroup(int emplacement){
        if(paint.getShapeAt(emplacement) instanceof Composite){
            return true;
        }
        return false;
    }

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

                    //Ce if/ else if vérifie si les nombres rentrés sont bien dans la liste
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

    private void erreurCommande(String erreurMsg){
        System.out.println(erreurMsg);
        throw new IllegalArgumentException();
    }

    private boolean isInside(int indexForme){
        if(indexForme < 0 || indexForme > paint.nbForme()-1){
            return false;
        }
        return true;
    }

    private void checkAdd(){
        try {
            switch (commandeIni[index]){
                case "circle":
                    if(commandeIni.length != typeOfCommande.ADD_CIRCLE.getLongueur()){
                        erreurCommande("Longueur de la commande pour ajouter un cercle trop petite");
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
                        erreurCommande("Longueur de la commande pour ajouter un carré trop petite");
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
                        erreurCommande("Longueur de la commande pour ajouter une ligne trop petite");
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
                        erreurCommande("Longueur de la commande pour ajouter un rectangle trop petite");
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

    private void checkInt(){
        try {
            Integer.parseInt(commandeIni[index]);
            index++;
        }
        catch (Exception e){
            erreurCommande("");
        }
    }

    public void setPaint(AsciiPaint paint){
        this.paint = paint;
    }

    private void checkColor(){
        try{
            commandeIni[index].charAt(0);
            index++;
        }
        catch (Exception e){
            erreurCommande("");
        }
    }

    public Command getCommandeCorrection(){
        Command command;
        switch (commandeCorrection[0]) {
            case "add":
                command = new AddCommand(paint, commandeCorrection);
                break;
            case "show":
                command = new ShowCommand(paint);
                break;
            case "list":
                command = new ListCommand(paint);
                break;
            case "color":
                command = new ColorCommand(paint, commandeCorrection);
                break;
            case "group":
                command = new GroupCommand(paint, commandeCorrectionForGroup);
                break;
            case "ungroup":
                command = new UngroupCommand(paint, commandeCorrectionForGroup);
                break;
            default:
                command = new EndCommand(paint);
                break;
        }
        return command;
    }

}
