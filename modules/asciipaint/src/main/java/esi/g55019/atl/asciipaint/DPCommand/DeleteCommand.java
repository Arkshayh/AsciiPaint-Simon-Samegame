package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;
import esi.g55019.atl.asciipaint.DPComposite.Component;

/**
 * Delete Command
 * @author Cotton Ian g55019
 */
public class DeleteCommand implements Command {

    private AsciiPaint paint;
    private int deleteIndex;
    private Component oldComponent;

    /**
     * constructor
     * @param paint
     * @param delete
     * @param oldComponent
     */
    public DeleteCommand(AsciiPaint paint, int delete, Component oldComponent) {
        this.paint = paint;
        this.deleteIndex = delete;
        // @pbt why not ? this.oldComponent = paint.getShapeAt(delete);
        this.oldComponent = oldComponent;
    }

    /**
     * Delete the shape at the given index (attribute index)
     */
    @Override
    public void execute() {
        paint.removeShape(paint.getShapeAt(deleteIndex));
    }

    /**
     * add to the drawing the last Component deleted
     */
    @Override
    public void unexecute() {
        paint.addCompenent(oldComponent);
    }

    @Override
    public boolean isReversible() {
        return true;
    }

}
