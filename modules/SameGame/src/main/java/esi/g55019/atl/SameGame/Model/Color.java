package esi.g55019.atl.SameGame.Model;

public enum Color {
    RED("\u001B[31m","#b02331","#d62f40"),
    GREEN("\u001B[32m","#3dad57","#4bd66b"),
    BLUE("\u001B[34m","#1f4fad","#2a6beb"),
    YELLOW("\u001B[33m","#bfb028","#fce938"),
    PURPLE("\u001B[35m","#b934ed","#863ba3");

    private String Color;
    private String ColorForJavaFx;
    private String ColorForJavaFxHover;

    private Color(String color,String colorForJavaFx ,String colorForJavaFxHover) {
        Color = color;
        this.ColorForJavaFx = colorForJavaFx;
        this.ColorForJavaFxHover = colorForJavaFxHover;
    }

    public String getColor() {
        return Color;
    }

    public String getColorForJavaFx() {
        return ColorForJavaFx;
    }

    public String getColorForJavaFxHover() {
        return ColorForJavaFxHover;
    }
}
