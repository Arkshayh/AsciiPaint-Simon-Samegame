package esi.g55019.atl.SameGame.Model;

// @pbt package name lowercase

/**
 * @author Cotton Ian g55019
 * this class represent a Bille
 * A Bille has a color
 */
public class Bille {
    private Color color;

    /**
     * Constructor
     * @param color
     */
    public Bille(Color color) {
        this.color = color;
    }

    /**
     * getter
     * @return color
     */
    public Color getColor() {
        return color;
    }

}
