package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;

/**
 * Command for list
 * @author Cotton Ian | g55019
 */
public class ListCommand implements Command{
    private AsciiPaint paint;

    /**
     * constructor
     * @param asciiPaint AsciiPaint
     */
    public ListCommand(AsciiPaint asciiPaint) {
        paint = asciiPaint;
    }

    /**
     * show the list of component in the drawing
     */
    @Override
    public void execute() {
        paint.showList();
    }

    @Override
    public void unexecute() {

    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
