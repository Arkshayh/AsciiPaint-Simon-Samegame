package esi.g55019.atl.SameGame.ControllerJavaFx;

import esi.g55019.atl.SameGame.DPCommand.ClickBilleCommand;
import esi.g55019.atl.SameGame.DPCommand.Command;
import esi.g55019.atl.SameGame.Model.Position;
import esi.g55019.atl.SameGame.Model.Model;
import esi.g55019.atl.SameGame.ViewJavaFx.ViewJavaFx;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ControllerJavaFx {
    private Model model;
    private ViewJavaFx view;
    private List<Command> undo = new ArrayList<>();
    private List<Command> redo = new ArrayList<>();

    public ControllerJavaFx(Model model) {
        this.model = model;
        this.view = new ViewJavaFx(this, model);
    }

    public void start(Stage primaryStage){
        view.start(primaryStage);
    }

    public void askCreateBoard(int row, int column, int level){
        model.createBoard(row, column, level);
    }

    public void clickOnBoardButton(Position pos){
        Command command = new ClickBilleCommand(model, pos);
        if(command.execute()){
            undo.add(command);
            view.disableUndo(false);
            redo.clear();
            view.disableRedo(true);
        }
    }

    public void clickOnUndo(){
        redo.add(undo.get(undo.size()-1));
        view.disableRedo(false);
        undo.get(undo.size()-1).unexecute();
        undo.remove(undo.size()-1);
        if(undo.size() == 0){
            view.disableUndo(true);
        }
    }

    public void clickOnRedo(){
        redo.get(redo.size()-1).execute();
        undo.add(redo.get(redo.size() - 1));
        view.disableUndo(false);
        redo.remove(redo.size()-1);
        if(redo.size() == 0){
            view.disableRedo(true);
        }
    }

}
