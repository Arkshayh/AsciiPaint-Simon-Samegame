package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;
import esi.g55019.atl.asciipaint.DPComposite.Component;
import esi.g55019.atl.asciipaint.DPComposite.Composite;

import java.util.List;

public class UngroupCommand implements Command{
    private AsciiPaint paint;
    private int index;

    public UngroupCommand(AsciiPaint paint, int index) {
        this.paint = paint;
        this.index = index;
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
}
