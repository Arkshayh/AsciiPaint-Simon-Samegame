package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;

/**
 * Command to end the application
 * @author Cotton Ian g55019
 */
public class EndCommand implements Command {
    private AsciiPaint paint;

    /**
     * constructor
     * @param paint
     */
    public EndCommand(AsciiPaint paint) {
        this.paint = paint;
    }

    /**
     * end the app
     */
    @Override
    public void execute() {
        paint.end();
    }


    @Override
    public void unexecute() {

    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
