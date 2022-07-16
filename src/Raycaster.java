package src;

import src.window.Window;
import src.window.Canvas;

import src.map.GameMap;

import src.window.Drawable;
import java.awt.Graphics;
import java.awt.Color;

class Raycaster {
    private Window window;
    private Canvas gameCanvas;
    private Canvas mapCanvas;

    public Raycaster() {
        this.initializeWindow();

        GameMap map = new GameMap(Const.MAP_WIDTH, Const.MAP_HEIGHT, 32);
        map.loadFromFile(Const.MAP_FILE_NAME);
        System.out.println(map);

        Drawable gradient = new Drawable() {
            @Override
            public void draw(Graphics graphics) {
                for (int y = 0; y < 256; y++) {
                    for (int x = 0; x < 256; x++) {
                        int r = 255 - (x + y) / 2;
                        int g = 0;
                        int b = (x + y) / 2;
                        Color col = new Color(r, g, b);
                        graphics.setColor(col);
                        graphics.fillRect(127 + x, 127 + y, 1, 1);
                    }
                }
            }
            
        };

        this.mapCanvas.addDrawable(map);
        this.gameCanvas.addDrawable(gradient);
    }

    public  void initializeWindow() {
        this.window = new Window("Raycaster", Const.SCREEN_WIDTH, 
        Const.SCREEN_HEIGHT, Const.FRAMES_PER_SECOND);

        this.gameCanvas = new Canvas();
        this.mapCanvas = new Canvas();
        this.window.addCanvas(this.gameCanvas);
        this.window.addCanvas(this.mapCanvas);
    }

    public void start() {
        this.window.start();
    }

    public static void main(String[] args) {
        Raycaster raycaster = new Raycaster();
        raycaster.start();
    }
}