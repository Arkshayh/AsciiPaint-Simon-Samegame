package esi.g55019.atl.SameGame.Model;

/**
 * Enumeration of Color
 * Each color of the enumeration has 3 attribute
 * - color to display text of the color in the console
 * - colorForJavaFx to add this color to a button in the JavaFx app
 * - colorForjavaFxHover to add this color to a button in the javaFx app when the mouse if the user enter to the button
 */
public enum Color {
    RED("\u001B[31m","#b02331","#d62f40"),
    GREEN("\u001B[32m","#3dad57","#4bd66b"),
    BLUE("\u001B[34m","#1f4fad","#2a6beb"),
    YELLOW("\u001B[33m","#bfb028","#fce938"),
    PURPLE("\u001B[35m","#b934ed","#863ba3");

    private String Color;
    private String ColorForJavaFx;
    private String ColorForJavaFxHover;

    /**
     * private constructor
     * @param color String
     * @param colorForJavaFx String
     * @param colorForJavaFxHover String
     */
    private Color(String color,String colorForJavaFx ,String colorForJavaFxHover) {
        Color = color;
        this.ColorForJavaFx = colorForJavaFx;
        this.ColorForJavaFxHover = colorForJavaFxHover;
    }

    /**
     * getter for color
     * @return String
     */
    public String getColor() {
        return Color;
    }

    /**
     * getter for colorforjavafx
     * @return String
     */
    public String getColorForJavaFx() {
        return ColorForJavaFx;
    }

    /**
     * getter for colorforjavafxhover
     * @return String
     */
    public String getColorForJavaFxHover() {
        return ColorForJavaFxHover;
    }
}
