package src;

import src.window.Window;
import src.window.Canvas;

import src.map.GameMap;
import src.game.Player;

import src.window.Drawable;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Raycaster {
    private Window window;
    private Canvas gameCanvas;
    private Canvas mapCanvas;

    private GameMap map;
    private Player player;

    private Timer updateTimer;

    public Raycaster() {
        this.map = new GameMap(Const.MAP_WIDTH, Const.MAP_HEIGHT, Const.TILE_WIDTH);
        this.map.loadFromFile(Const.MAP_FILE_NAME);
        this.player = new Player(Const.TILE_WIDTH * 2, Const.TILE_WIDTH * 3, this.map, Const.PLAYER_MOVE_SPEED, Const.PLAYER_TURN_SPEED);

        this.initializeWindow();

        this.mapCanvas.addDrawable(this.map);
        this.mapCanvas.addDrawable(this.player);
        this.gameCanvas.addDrawable(new Drawable() {
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
        });

        this.gameCanvas.addKeyListener(this.player.new InputHandler());

        this.updateTimer = new Timer(Const.UPDATE_RATE, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
    }

    private void update() {
        this.player.update();
    }

    public void initializeWindow() {
        this.window = new Window("Raycaster", Const.SCREEN_WIDTH, 
        Const.SCREEN_HEIGHT, Const.FRAME_RATE);

        this.gameCanvas = new Canvas();
        this.mapCanvas = new Canvas();
        this.window.addCanvas(this.gameCanvas);
        this.window.addCanvas(this.mapCanvas);
    }

    public void start() {
        this.window.start();
        this.updateTimer.start();
    }

    public static void main(String[] args) {
        Raycaster raycaster = new Raycaster();
        raycaster.start();
    }
}