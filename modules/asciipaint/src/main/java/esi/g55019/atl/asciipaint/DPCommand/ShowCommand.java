package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;

public class ShowCommand implements Command{
    private AsciiPaint paint;

    public ShowCommand(AsciiPaint paint) {
            this.paint = paint;
    }

    @Override
    public void execute() {
        System.out.println(paint.asAscii());
    }
}
