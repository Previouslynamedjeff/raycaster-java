package src;

import src.window.Window;
import src.window.Canvas;
import src.window.Drawable;
import java.awt.Graphics;
import java.awt.Color;

class Main {
    public static void main(String[] args) {
        Window window = new Window("Raycaster", Const.SCREEN_WIDTH, 
                Const.SCREEN_HEIGHT, Const.FRAMES_PER_SECOND);
        
        Canvas gameCanvas = new Canvas();
        Canvas mapCanvas = new Canvas();
        window.addCanvas(gameCanvas);
        window.addCanvas(mapCanvas);
        
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

        gameCanvas.addDrawable(gradient);
        mapCanvas.addDrawable(gradient);
        
        window.start();
    }
}