package esi.g55019.atl.asciipaint.DPCommand;

import esi.g55019.atl.asciipaint.AsciiPaint;

public class AddCommand implements Command{

    private AsciiPaint paint;
    private String[] commands;

    public AddCommand(AsciiPaint paint,String[] commands) {
        this.paint = paint;
        this.commands = commands;
    }

    /**
     * execute the add Command
     * it will add a
     */
    @Override
    public void execute() {
        System.out.println("Ajout d'un " + commands[1]);
        switch(commands[1]){
            case "circle":
                paint.newCircle(Integer.parseInt(commands[2]),Integer.parseInt(commands[3]),Integer.parseInt(commands[4])
                        ,commands[commands.length-1].charAt(0));
                break;
            case "square":
                paint.newSquare(Integer.parseInt(commands[2]),Integer.parseInt(commands[3]),Integer.parseInt(commands[4])
                        ,commands[commands.length-1].charAt(0));
                break;
            case "line":
                paint.newLine(Integer.parseInt(commands[2]),Integer.parseInt(commands[3]),Integer.parseInt(commands[4])
                        ,Integer.parseInt(commands[5]),commands[commands.length-1].charAt(0));
                break;
            default:
                paint.newRectangle(Integer.parseInt(commands[2]),Integer.parseInt(commands[3]),Integer.parseInt(commands[4])
                        ,Integer.parseInt(commands[5]),commands[commands.length-1].charAt(0));
                break;
        }
    }

    /**
     * remove the last shape added
     */
    @Override
    public void unexecute() {
        paint.removeShape(paint.getShapeAt(paint.nbForme() -1));
    }

    @Override
    public boolean isReversible() {
        return true;
    }
}
