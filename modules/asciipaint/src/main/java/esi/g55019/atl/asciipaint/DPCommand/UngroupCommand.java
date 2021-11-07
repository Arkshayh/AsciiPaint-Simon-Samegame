package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;
import esi.g55019.atl.asciipaint.DPComposite.Component;
import esi.g55019.atl.asciipaint.DPComposite.Composite;

import java.util.List;

/**
 * this class represent the ungroup command and implement the command interface
 */
public class UngroupCommand implements Command{
    private AsciiPaint paint;
    private int index;
    private int nbUndoUngroupForm;

    /**
     * Constructor
     * @param paint Asciipaint
     * @param index int
     * @param nbUndoUngroupForm int
     */
    public UngroupCommand(AsciiPaint paint, int index, int nbUndoUngroupForm) {
        this.paint = paint;
        this.index = index;
        this.nbUndoUngroupForm = nbUndoUngroupForm;
    }

    /**
     * Ungroup the group
     */
    @Override
    public void execute() {
        Component groupe = paint.getShapeAt(index);
        List<Component> list = ((Composite) groupe).getChildren();
        for (int i = 0; i < list.size(); i++) {
            paint.addCompenent(list.get(i));
        }
        paint.removeShape(groupe);
    }

    /**
     * create back the previous group
     */
    @Override
    public void unexecute() {
        Composite groupe = new Composite();
        for (int i = 0; i < nbUndoUngroupForm; i++) {
            groupe.add(paint.getShapeAt(paint.nbForme()-1));
            paint.removeShape(paint.getShapeAt(paint.nbForme()-1));
        }
        paint.addCompenent(groupe);
    }

    @Override
    public boolean isReversible() {
        return true;
    }
}
