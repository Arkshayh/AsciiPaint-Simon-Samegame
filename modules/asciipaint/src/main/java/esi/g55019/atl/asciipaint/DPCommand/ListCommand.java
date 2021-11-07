package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;

public class ListCommand implements Command{
    private AsciiPaint paint;

    public ListCommand(AsciiPaint asciiPaint) {
        paint = asciiPaint;
    }

    @Override
    public void execute() {
        paint.showList();
    }

    @Override
    public void unexecute() {
        System.out.println("Impossible d'annuler la commande list");
    }
}
