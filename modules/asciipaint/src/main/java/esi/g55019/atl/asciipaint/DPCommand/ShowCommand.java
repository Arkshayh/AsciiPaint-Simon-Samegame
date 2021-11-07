package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;

/**
 * Command that display the drawing
 * @author Cotton Ian g55019
 */
public class ShowCommand implements Command{
    private AsciiPaint paint;

    /**
     * Constructor
     * @param paint
     */
    public ShowCommand(AsciiPaint paint) {
            this.paint = paint;
    }

    /**
     * Display the drawing
     */
    @Override
    public void execute() {
        System.out.println(paint.asAscii());
    }

    @Override
    public void unexecute() {

    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
