package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;

public class ColorCommand implements Command{
    private AsciiPaint paint;
    private String[] commande;
    private char oldColor;

    public ColorCommand(AsciiPaint paint, String[] commande, char oldColor) {
        this.paint = paint;
        this.commande = commande;
        this.oldColor = oldColor;
    }

    @Override
    public void execute() {
        oldColor = paint.getShapeAt(Integer.parseInt(commande[1])).getColor();
        paint.changeColor(Integer.parseInt(commande[1]), commande[2].charAt(0));
    }

    @Override
    public void unexecute() {
        paint.changeColor(Integer.parseInt(commande[1]), oldColor);
    }

    @Override
    public boolean isReversible() {
        return true;
    }
}
