package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;
import esi.g55019.atl.asciipaint.Point;

/**
 * Command to move a component
 * @author Cotton Ian g55019
 */
public class MoveCommand implements Command{
    private AsciiPaint paint;
    private int index;
    private int x;
    private int y;
    private Point unMove;
    private int indexUnmove;

    /**
     * Constructor
     * @param paint Asciipaint
     * @param index int
     * @param x int
     * @param y int
     * @param unMove Point
     * @param indexUnmove int
     */
    public MoveCommand(AsciiPaint paint, int index, int x, int y, Point unMove, int indexUnmove ) {
        this.paint = paint;
        this.index = index;
        this.x = x;
        this.y = y;
        this.unMove = unMove;
        this.indexUnmove = indexUnmove;
    }

    /**
     * move the component at the index parameter by the value x and y
     */
    @Override
    public void execute() {
        paint.move(index,x,y);
    }

    /**
     * move the compenent at the indexUnlove parameter by the value -x -y obtained by the point attributes
     */
    @Override
    public void unexecute() {
        paint.move(indexUnmove, -(int) unMove.getX(),- (int) unMove.getY());
    }

    @Override
    public boolean isReversible() {
        return true;
    }
}
