package src.map;

import java.awt.Color;

public class Tile {
    public static final Tile FLOOR = new Tile(false, Color.LIGHT_GRAY, '.', 0);
    public static final Tile BORDER = new Tile(true, Color.DARK_GRAY, 'A', 10);
    public static final Tile BLUE_WALL = new Tile(true, Color.CYAN, 'B', 10);
    public static final Tile RED_WALL = new Tile(true, Color.RED, 'R', 10);
    public static final Tile UNRECOGNIZED = new Tile(false, Color.MAGENTA, '?', 3);

    private boolean solid;
    private Color color;
    private char symbol;
    private double height;

    public Tile(boolean isSolid, Color color, char symbol, double height) {
        this.solid = isSolid;
        this.color = color;
        this.symbol = symbol;
        this.height = height;
    }

    public boolean checkSolid() {
        return this.solid;
    }

    public Color getColor() {
        return this.color;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public double getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
        return Character.toString(this.symbol);
    }
}