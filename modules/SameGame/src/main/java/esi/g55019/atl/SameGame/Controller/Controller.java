package esi.g55019.atl.SameGame.Controller;

import esi.g55019.atl.SameGame.Model.Model;
import esi.g55019.atl.SameGame.ViewConsole.ViewConsole;

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
        play(nbLigne, nbColonne);
    }

    private void play(int nbLigne, int nbColonne){
        boolean end = false;
        while (!end){
            viewConsole.displayScore("Score : ");
            viewConsole.displayBoard();
            model.play(viewConsole.askPosition(nbLigne-1, nbColonne-1));
            if(model.isFinish()){
                end = true;
                viewConsole.displayBoard();
                viewConsole.displayScore("\nFin de la partie ! Votre score : ");
            }
        }
    }
}
