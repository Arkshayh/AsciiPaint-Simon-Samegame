package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;

public class ColorCommand implements Command{
    private AsciiPaint paint;
    private String[] commande;

    public ColorCommand(AsciiPaint paint, String[] commande) {
        this.paint = paint;
        this.commande = commande;
    }

    @Override
    public void execute() {
        paint.changeColor(Integer.parseInt(commande[1]), commande[2].charAt(0));
    }
}
