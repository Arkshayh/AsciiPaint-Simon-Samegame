package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;

public class GroupCommand implements Command{
    private AsciiPaint paint;
    int[] commandes;

    public GroupCommand(AsciiPaint asciiPaint, int[] commandes) {
        this.commandes = commandes;
        paint = asciiPaint;
    }

    @Override
    public void execute() {
        paint.newGroup(commandes);
    }
}
