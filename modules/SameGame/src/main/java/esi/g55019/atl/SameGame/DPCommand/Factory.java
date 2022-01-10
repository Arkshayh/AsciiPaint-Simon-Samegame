package esi.g55019.atl.SameGame.DPCommand;

import esi.g55019.atl.SameGame.Model.Model;
import esi.g55019.atl.SameGame.Model.Position;

/**
 * This class represent a factory and will check if the string given of the
 * user can be use to create a command.
 */
public class Factory {
    private Model model;

    /**
     * Constructor
     * @param model Model
     */
    public Factory(Model model) {
        this.model = model;
    }

    /**
     * Setter
     * @param model Model
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * Return a command if the string given can be converted
     * @param commande String
     * @return Command
     */
    public Command giveAndGetCommand(String commande){
        String [] tabCommand = commande.split(" ");
        if(tabCommand.length != 2){
            throw new IllegalArgumentException("Commande position incorrecte");
        }
        int ligne = Integer.parseInt(tabCommand[0]);
        int colonne = Integer.parseInt(tabCommand[1]);

        return new ClickBilleCommand(model, new Position(ligne,colonne));
    }
}
