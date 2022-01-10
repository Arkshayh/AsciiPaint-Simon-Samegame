package esi.g55019.atl.SameGame.DPCommand;

import esi.g55019.atl.SameGame.Model.Board;
import esi.g55019.atl.SameGame.Model.Model;
import esi.g55019.atl.SameGame.Model.Position;

/**
 * This class implement Command. And represent a Command when the user want to
 * remove a Bille.
 */
public class ClickBilleCommand implements Command{
    private Model model;
    private Board oldBoard;
    private Position position;
    // @pbt save the score

    /**
     * Constructor
     * @param model Model
     * @param position Position
     */
    public ClickBilleCommand(Model model, Position position) {
        this.model = model;
        this.position = position;
    }

    /**
     * return true if the command can correctly be executed.
     * it will call the method play of the model.
     * @return boolean
     */
    @Override
    public boolean execute() {
        this.oldBoard = model.getBoard();
        if(model.play(position)){
            return true;
        }
        return false;
    }

    /**
     * Will change the board of the model with the oldBoard of the model
     */
    @Override
    public void unexecute() {
        this.model.setBoard(oldBoard);
    }

    /**
     * return true if this command has an unexecute method
     * @return boolean
     */
    @Override
    public boolean isReversible() {
        return true;
    }
}
