package esi.g55019.atl.SameGame;


import esi.g55019.atl.SameGame.Controller.Controller;
import esi.g55019.atl.SameGame.Model.Model;

public class App {

    //TODO : DP pas de Composite
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        controller.start();
    }
}


/**
 * gridpane n * m  et un bouton dans chaque, si on a des objets dans son model on va créer
 */