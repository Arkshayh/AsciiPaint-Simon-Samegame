package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;

public class EndCommand implements Command {
    private AsciiPaint paint;

    public EndCommand(AsciiPaint paint) {
        this.paint = paint;
    }

    @Override
    public void execute() {
        paint.end();
    }
}
