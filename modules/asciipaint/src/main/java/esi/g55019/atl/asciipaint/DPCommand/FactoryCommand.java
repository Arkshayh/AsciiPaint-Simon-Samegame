package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;


public class FactoryCommand {

    private AsciiPaint paint;
    private String[] commandeIni;
    private String[] commandeCorrection;
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
            default:
                command = new EndCommand(paint);
                break;
        }
        return command;
    }

}
