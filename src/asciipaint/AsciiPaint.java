package asciipaint;

public class AsciiPaint {

    private Drawing drawing;

    public AsciiPaint(Drawing drawing) {
        this.drawing = drawing;
    }

    public AsciiPaint() {
    }

    public void newCircle(int x, int y, double radius, char color){
        drawing.addShape(new Circle(new Point(x,y),radius,color));
    }

    public void newRectangle(int x, int y, double width, double height, char color){
        drawing.addShape(new Rectangle(new Point(x,y), width, height, color));
    }

    public void newSquare(int x, int y, double side, char color){
        drawing.addShape(new Square(new Point(x,y),side,color));
    }

    public String asAscii(){
        String monstring = "";

        for (int i = drawing.getHeight(); i >= 0; i--) {
            for (int j = 0; j < drawing.getWidth(); j++) {
                if(drawing.getShapeAt(new Point(j,i)) != null){
                    monstring += (" "+ (drawing.getShapeAt(new Point(j,i)).getColor()) + " ");
                }
                else{
                    monstring += (" . ");
                }
            }
            monstring += ("\n");
        }

        return monstring;
    }
}

