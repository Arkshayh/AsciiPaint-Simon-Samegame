package esi.g55019.atl.SameGame.Model;

public enum Color {
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    BLUE("\u001B[34m"),
    YELLOW("\u001B[33m"),
    PURPLE("\u001B[35m");

    private String Color;

    private Color(String color) {
        Color = color;
    }

    public String getColor() {
        return Color;
    }
}
