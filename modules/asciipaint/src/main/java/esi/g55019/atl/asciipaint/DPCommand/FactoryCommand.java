package esi.g55019.atl.asciipaint.DPCommand;

import java.util.Scanner;

public class FactoryCommand {

    private String[] commandeIni;
    private String[] commandeCorrection;
    private Scanner clavier = new Scanner(System.in);
    private int index = 0;


    public FactoryCommand() {
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
                    commandeCorrection = commandeIni;
                    break;
                case "list":
                    break;
                case "group":
                    break;
                case "end":
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
            int x = Integer.parseInt(commandeIni[index]);
            int y = Integer.parseInt(commandeIni[index + 1]);
            index = index + 2;
        }
        catch (Exception e){
            erreurCommande();
        }
    }

    private void checkInt(){
        try {
            int x = Integer.parseInt(commandeIni[index]);
            index++;
        }
        catch (Exception e){
            erreurCommande();
        }
    }

    private void checkColor(){
        try{
            char color = commandeIni[index].charAt(0);
            index++;
        }
        catch (Exception e){
            erreurCommande();
        }
    }

    public String[] getCommandeCorrection(){
        return commandeCorrection;
    }

    public String getTypeCommande(){
        return commandeCorrection[0];
    }

}
