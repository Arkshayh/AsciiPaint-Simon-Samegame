package esi.g55019.atl.SameGame.DPCommand;

import esi.g55019.atl.SameGame.Model.Model;
import esi.g55019.atl.SameGame.Model.Position;

public class Factory {
    private Model model;

    public Factory(Model model) {
        this.model = model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

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
