package src.map;

import java.awt.Color;

public class Tile {
    public static final Tile AIR = new Tile(false, Color.CYAN, '.');
    public static final Tile BORDER = new Tile(true, Color.BLACK, 'A');
    public static final Tile WALL = new Tile(true, Color.RED, 'B');
    public static final Tile UNRECOGNIZED = new Tile(false, Color.MAGENTA, '?');

    private boolean solid;
    private Color color;
    private char symbol;

    public Tile(boolean isSolid, Color color, char symbol) {
        this.solid = isSolid;
        this.color = color;
        this.symbol = symbol;
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

    @Override
    public String toString() {
        return "" + this.getSymbol();
    }
}