package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;
import esi.g55019.atl.asciipaint.Point;

public class MoveCommand implements Command{
    private AsciiPaint paint;
    private int index;
    private int x;
    private int y;
    private Point unMove;
    private int indexUnmove;

    public MoveCommand(AsciiPaint paint, int index, int x, int y, Point unMove, int indexUnmove ) {
        this.paint = paint;
        this.index = index;
        this.x = x;
        this.y = y;
        this.unMove = unMove;
        this.indexUnmove = indexUnmove;
    }

    @Override
    public void execute() {
        paint.move(index,x,y);
    }

    @Override
    public void unexecute() {
        paint.move(indexUnmove, -(int) unMove.getX(),- (int) unMove.getY());
    }

    @Override
    public boolean isReversible() {
        return true;
    }
}
