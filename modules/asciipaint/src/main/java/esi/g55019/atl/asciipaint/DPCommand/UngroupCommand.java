package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;
import esi.g55019.atl.asciipaint.DPComposite.Component;
import esi.g55019.atl.asciipaint.DPComposite.Composite;

import java.util.List;

public class UngroupCommand implements Command{
    private AsciiPaint paint;
    private int index;
    private int nbUndoUngroupForm;

    public UngroupCommand(AsciiPaint paint, int index, int nbUndoUngroupForm) {
        this.paint = paint;
        this.index = index;
        this.nbUndoUngroupForm = nbUndoUngroupForm;
    }

    @Override
    public void execute() {
        Component groupe = paint.getShapeAt(index);
        List<Component> list = ((Composite) groupe).getChildren();
        for (int i = 0; i < list.size(); i++) {
            paint.addCompenent(list.get(i));
        }
        paint.removeShape(groupe);
    }

    @Override
    public void unexecute() {
        Composite groupe = new Composite();
        for (int i = 0; i < nbUndoUngroupForm; i++) {
            groupe.add(paint.getShapeAt(paint.nbForme()-1));
            paint.removeShape(paint.getShapeAt(paint.nbForme()-1));
        }
        paint.addCompenent(groupe);
    }
}
