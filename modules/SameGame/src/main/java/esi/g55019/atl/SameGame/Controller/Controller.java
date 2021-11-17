package esi.g55019.atl.SameGame.Controller;

import esi.g55019.atl.SameGame.DPCommand.Command;
import esi.g55019.atl.SameGame.DPCommand.Factory;
import esi.g55019.atl.SameGame.Model.Model;
import esi.g55019.atl.SameGame.ViewConsole.ViewConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private ViewConsole viewConsole;
    private Model model;

    public Controller(Model model) {
        this.model = model;
        this.viewConsole = new ViewConsole(model, this);
    }

    public void start(){
        int nbColor = viewConsole.askColor();
        int nbLigne = viewConsole.askLigneOrColonne("lignes");
        int nbColonne = viewConsole.askLigneOrColonne("colonnes");
        model.initialiseBoard(nbLigne, nbColonne,nbColor);

        while (play()){
            nbColor = viewConsole.askColor();
            nbLigne = viewConsole.askLigneOrColonne("lignes");
            nbColonne = viewConsole.askLigneOrColonne("colonnes");
            model.initialiseBoard(nbLigne, nbColonne,nbColor);
        }
    }

    private boolean play(){
        Scanner clavier = new Scanner(System.in);
        boolean end = false;
        boolean replay = false;
        List<Command> listeDeCommandeAUndo = new ArrayList<>();
        List<Command> listeDeCommandeARedo = new ArrayList<>();
        Command factoryCommand;
        Factory factory = new Factory(model);

        while (!end){
            viewConsole.displayListCommand();
            viewConsole.displayScore("Score : ");
            viewConsole.displayBoard();
            System.out.print("\nVotre commande : ");
            String maCommande = clavier.nextLine();
            maCommande = maCommande.toUpperCase();

            switch (maCommande){
                case "UNDO": //TODO à tester
                    if(listeDeCommandeAUndo.size() > 0){
                        listeDeCommandeAUndo.get(listeDeCommandeAUndo.size()-1).unexecute();
                        listeDeCommandeARedo.add(listeDeCommandeAUndo.get(listeDeCommandeAUndo.size()-1));
                        listeDeCommandeAUndo.remove(listeDeCommandeAUndo.size()-1);
                    }
                    else{
                        System.out.println("Plus de undo possible");
                    }
                    break;
                case "REDO": //TODO à tester
                    if(listeDeCommandeARedo.size() > 0){
                        listeDeCommandeARedo.get(listeDeCommandeARedo.size()-1).execute();
                        listeDeCommandeARedo.remove(listeDeCommandeARedo.size()-1);
                    }
                    else {
                        System.out.printf("Plus de redo possible");
                    }
                    break;
                case "GIVEUP":
                    return false;
                case "RESTART":
                    System.out.println("\nCréation d'une nouvelle partie : \n");
                    return true;
                default:
                   try {
                       factoryCommand = factory.giveAndGetCommand(maCommande);
                       factoryCommand.execute();
                       listeDeCommandeAUndo.add(factoryCommand);
                       listeDeCommandeARedo.clear();

                   }
                   catch (IllegalArgumentException e){
                       System.out.println("Erreur ! ");
                   }
            }
            factory.setModel(model);

            if(model.isFinish()){
                viewConsole.displayBoard();
                if(model.isWin()){
                    System.out.println("Vous avez gagné ! ");
                }
                else{
                    System.out.println("Vous avez perdu !");
                }
                viewConsole.displayScore("\nFin de la partie ! Votre score : ");
                viewConsole.replayMsg();
                return doReplay();
            }
        }
        return replay;
    }

    private boolean doReplay(){
        Scanner clavier = new Scanner(System.in);
        String replayAnswer = clavier.nextLine();
        while (!replayAnswer.equals("Y") && !replayAnswer.equals("N")){
            viewConsole.replayMsg();
            replayAnswer = clavier.nextLine();
        }
        if(replayAnswer.equals("Y")){
            return true;
        }
        return false;
    }
}
