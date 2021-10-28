package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;

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
                case "group":
                    checkGroup();
                    break;
                default:
                    erreurCommande();
                    break;
            }
        }
        catch (Exception e){
            erreurCommande();
        }
    }

    private void checkGroup(){
        if(commandeIni.length < 3){
            erreurCommande();
        }
        else{
            int [] tab = new int[commandeIni.length-1];
            try{
                for (int i = 1; i < commandeIni.length; i++) {
                    tab[i-1] = Integer.parseInt(commandeIni[i]) -1;
                }
                if(!hasDoublon(tab)){
                    //TODO: faire un check si les num rentrés sont bien dans la liste (donc > 0 et < listeshape.length)
                    Arrays.sort(tab);
                    commandeCorrection = commandeIni;
                    commandeCorrectionForGroup = tab;
                }
                else{
                    erreurCommande();
                }
            }catch (Exception e){
                erreurCommande();
            }
        }
    }
    
    private boolean hasDoublon(int [] tab){
        for (int i = 0; i < tab.length; i++) {
            for (int j = i+1; j < tab.length; j++) {
                if(tab[i] == tab[j]){
                    System.out.print("Vous voulez ajouter 2 fois la même forme dans le groupe : ");
                    return true;
                }
            }
        }
        return false;
    }

    private void erreurCommande(){
        throw new IllegalArgumentException("Erreur commande");
    }

    private void checkAdd(){
        try {
            switch (commandeIni[index]){
                case "circle":
                    if(commandeIni.length != typeOfCommande.ADD_CIRCLE.getLongueur()){
                        erreurCommande();
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
                        erreurCommande();
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
                        erreurCommande();
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
                        erreurCommande();
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
                    erreurCommande();
                    break;
            }
        }
        catch (Exception e){
            erreurCommande();
        }
    }

    private void checkPoint(){
        try{
            Integer.parseInt(commandeIni[index]);
            Integer.parseInt(commandeIni[index + 1]);
            index = index + 2;
        }
        catch (Exception e){
            erreurCommande();
        }
    }

    private void checkInt(){
        try {
            Integer.parseInt(commandeIni[index]);
            index++;
        }
        catch (Exception e){
            erreurCommande();
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
            erreurCommande();
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
            case "group":
                command = new GroupCommand(paint, commandeCorrectionForGroup);
                break;
            default:
                command = new EndCommand(paint);
                break;
        }
        return command;
    }

}
