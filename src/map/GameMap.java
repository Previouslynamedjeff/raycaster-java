package src.map;

import java.util.Arrays;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

import src.window.Drawable;
import java.awt.Graphics;
import java.awt.Color;

import src.math.Vector;

public class GameMap implements Drawable {
    private Tile[][] tiles;

    private int tileWidth;

    public GameMap(int tileWidth) {
        this.tiles = new Tile[0][0];

        this.tileWidth = tileWidth;
    }

    public void loadFromFile(String fileName) {
        File file = new File(fileName);

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (IOException ex) {
            System.out.println("Error: Map file not found. [" + fileName + "]");
            return;
        }

        int width = scanner.nextInt();
        int height = scanner.nextInt();
        this.tiles = new Tile[height][width];

        scanner.nextLine();

        for (int y = 0; y < this.getHeight() && scanner.hasNextLine(); y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < this.getWidth() && x < line.length(); x++) {
                char symbol = line.charAt(x);

                if (symbol == Tile.BORDER.getSymbol()) {
                    this.tiles[y][x] = Tile.BORDER;
                } else if (symbol == Tile.WALL.getSymbol()) {
                    this.tiles[y][x] = Tile.WALL;
                } else if (symbol == Tile.AIR.getSymbol()) {
                    this.tiles[y][x] = Tile.AIR;
                } else {
                    this.tiles[y][x] = Tile.UNRECOGNIZED;
                }
            }
        }

        scanner.close();
    }

    @Override
    public void draw(Graphics graphics) {
        for (int y = 0; y < this.getHeight(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                Tile tile = this.tiles[y][x];
                Color color = tile.getColor();
                graphics.setColor(color);
                graphics.fillRect(x*this.tileWidth, y*this.tileWidth, this.tileWidth, this.tileWidth);
            }
        }
    }

    public int getWidth() {
        return this.tiles[0].length;
    }

    public int getHeight() {
        return this.tiles.length;
    }

    @Override
    public String toString() {
        String info = "";

        for (Tile[] tileRow: this.tiles) {
            for (Tile tile: tileRow) {
                info += tile;
            }
            info += "\n";
        }
        return info;
    }

    public boolean checkSolid(double x, double y) {
        int mapX = (int) (x / this.tileWidth);
        int mapY = (int) (y / this.tileWidth);

        if (mapX < 0 || mapX >= this.getWidth() || mapY < 0 || mapY >= this.getHeight()) {
            return true;
        }

        Tile tile = this.tiles[mapY][mapX];

        return tile.checkSolid();
    }

    public boolean checkSolid(Vector pos) {
        return this.checkSolid(pos.getX(), pos.getY());
    }
}
