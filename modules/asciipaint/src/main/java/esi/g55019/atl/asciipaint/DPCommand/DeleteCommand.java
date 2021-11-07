package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;
import esi.g55019.atl.asciipaint.DPComposite.Component;

public class DeleteCommand implements Command {

    private AsciiPaint paint;
    private int deleteIndex;
    private Component oldComponent;


    public DeleteCommand(AsciiPaint paint, int delete, Component oldComponent) {
        this.paint = paint;
        this.deleteIndex = delete;
        this.oldComponent = oldComponent;
    }

    @Override
    public void execute() {
        paint.removeShape(paint.getShapeAt(deleteIndex));
    }

    @Override
    public void unexecute() {
        paint.addCompenent(oldComponent);
    }

}
