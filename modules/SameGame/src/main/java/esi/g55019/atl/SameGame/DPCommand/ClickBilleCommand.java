package esi.g55019.atl.SameGame.DPCommand;

import esi.g55019.atl.SameGame.Model.Model;
import esi.g55019.atl.SameGame.Model.Position;

public class ClickBilleCommand implements Command{
    private Model model;
    private Position position;

    public ClickBilleCommand(Model model, Position position) {
        this.model = model;
        this.position = position;
    }

    @Override
    public void execute() {
        model.play(position);
    }

    @Override
    public void unexecute() {

    }

    @Override
    public boolean isReversible() {
        return true;
    }
}
