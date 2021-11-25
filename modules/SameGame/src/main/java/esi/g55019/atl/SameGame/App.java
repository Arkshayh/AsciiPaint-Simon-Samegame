package esi.g55019.atl.SameGame;


import esi.g55019.atl.SameGame.Controller.Controller;
import esi.g55019.atl.SameGame.Model.Model;

public class App {

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        controller.start();
    }
}