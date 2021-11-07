package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;

/**
 * Color command
 * @author Cotton Ian g55019
 */
public class ColorCommand implements Command{
    private AsciiPaint paint;
    private String[] commande;
    private char oldColor;

    /**
     * construcor
     * @param paint AsciiPaint
     * @param commande  String[]
     * @param oldColor char
     */
    public ColorCommand(AsciiPaint paint, String[] commande, char oldColor) {
        this.paint = paint;
        this.commande = commande;
        this.oldColor = oldColor;
    }

    /**
     * change the color of the component (the index is in the command attribute)
     */
    @Override
    public void execute() {
        paint.changeColor(Integer.parseInt(commande[1]), commande[2].charAt(0));
    }

    /**
     * change the color back to the previous one
     */
    @Override
    public void unexecute() {
        paint.changeColor(Integer.parseInt(commande[1]), oldColor);
    }

    @Override
    public boolean isReversible() {
        return true;
    }
}
