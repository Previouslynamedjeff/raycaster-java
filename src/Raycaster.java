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
        this.map = new GameMap(Const.TILE_WIDTH);
        this.map.loadFromFile(Const.MAP_FILE_NAME);
        this.player = new Player(Const.TILE_WIDTH * 2, Const.TILE_WIDTH * 4, this.map, 
                Const.PLAYER_MOVE_SPEED, Const.PLAYER_TURN_SPEED, Const.PLAYER_VIEW_WIDTH, 
                Const.PLAYER_VIEW_HEIGHT, Const.NUM_RAYS, Const.PLAYER_HEIGHT);

        this.initializeWindow();

        this.mapCanvas.addDrawable(this.map);
        this.mapCanvas.addDrawable(this.player);
        this.gameCanvas.addDrawable(this.player.new FOV());

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