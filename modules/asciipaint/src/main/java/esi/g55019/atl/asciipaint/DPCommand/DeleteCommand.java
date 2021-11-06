package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;

public class DeleteCommand implements Command {

    private AsciiPaint paint;
    private int deleteIndex;

    public DeleteCommand(AsciiPaint paint, int delete) {
        this.paint = paint;
        this.deleteIndex = delete;
    }

    @Override
    public void execute() {
        paint.removeShape(paint.getShapeAt(deleteIndex));
    }
}
