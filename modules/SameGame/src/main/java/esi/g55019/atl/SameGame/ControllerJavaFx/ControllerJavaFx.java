package esi.g55019.atl.SameGame.ControllerJavaFx;

import esi.g55019.atl.SameGame.DPCommand.ClickBilleCommand;
import esi.g55019.atl.SameGame.DPCommand.Command;
import esi.g55019.atl.SameGame.Model.Position;
import esi.g55019.atl.SameGame.Model.Model;
import esi.g55019.atl.SameGame.ViewJavaFx.ViewJavaFx;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the controller of the javaFx app
 * The controller has the model of the app, the view and 2 list of Command.
 */
public class ControllerJavaFx {
    private Model model;
    private ViewJavaFx view;
    private List<Command> undo = new ArrayList<>();
    private List<Command> redo = new ArrayList<>();

    /**
     * Constructor
     * @param model Model
     */
    public ControllerJavaFx(Model model) {
        this.model = model;
        this.view = new ViewJavaFx(this, model);
    }

    /**
     * method called when the app is launched
     * @param primaryStage
     */
    public void start(Stage primaryStage){
        view.start(primaryStage);
    }

    /**
     * this method is called when the user start or restart a game.
     * it will ask to the model to create a new Board and ask to the view to hide the victory/defeat screen is necessary
     * @param row int
     * @param column int
     * @param level int
     */
    public void askCreateBoard(int row, int column, int level){
        view.hideWinLose();
        model.createBoard(row, column, level);
    }

    /**
     * Called when the user click of a button.
     * This method will create a command and try to execute it. If it's the case the command will be added to the
     * undo list then activate the button undo. It will also clear the redo list and the disable the redo button.
     * @param pos Position
     */
    public void clickOnBoardButton(Position pos){
        Command command = new ClickBilleCommand(model, pos);
        if(command.execute()){
            undo.add(command);
            view.disableUndo(false);
            redo.clear();
            view.disableRedo(true);
        }
    }

    /**
     * called when the user click on the undo button
     * It will hide the victory or the defeat screen if necessary.
     * It will add the command of the undo list to redo list then activate the redo button.
     * The unexecute the last command in the undo list then remove it.
     * If after if the undo list is empty the button undo will be disable.
     */
    public void clickOnUndo(){
        view.hideWinLose();
        redo.add(undo.get(undo.size()-1));
        view.disableRedo(false);
        undo.get(undo.size()-1).unexecute();
        undo.remove(undo.size()-1);
        if(undo.size() == 0){
            view.disableUndo(true);
        }
    }

    /**
     * called when the user click on the redo button.
     * It will execute the last command in the redo list then add the command to the undo list and finally remove the
     * command from the redo list.
     * Then It will activate the undo button and disable the redo button if the redo list is empty
     */
    public void clickOnRedo(){
        redo.get(redo.size()-1).execute();
        undo.add(redo.get(redo.size() - 1));
        view.disableUndo(false);
        redo.remove(redo.size()-1);
        if(redo.size() == 0){
            view.disableRedo(true);
        }
    }

    /**
     * Called when the user click on the giveUp button. Call the giveUp method of the model.
     */
    public void clickOnGiveUp(){
        model.giveUp();
    }

}
