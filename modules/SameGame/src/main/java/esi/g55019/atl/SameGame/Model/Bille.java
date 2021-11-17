package esi.g55019.atl.SameGame.Model;

public class Bille {
    private Color color;
    private Position position;

    public Bille(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
