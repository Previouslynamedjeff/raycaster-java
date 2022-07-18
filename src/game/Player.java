package src.game;

import src.math.Vector;
import src.map.GameMap;

import src.window.Drawable;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Player implements Drawable {
    private Vector pos;
    private double direction;
    private GameMap map;
    private double maxMoveSpeed;
    private double maxTurnSpeed;
    private double curMoveSpeed;
    private double curTurnSpeed;

    public Player(Vector startPos, GameMap map, double moveSpeed, double turnSpeed) {
        this.pos = startPos.clone();
        this.map = map;
        this.direction = 0;
        this.maxMoveSpeed = moveSpeed;
        this.maxTurnSpeed = turnSpeed;
        this.curMoveSpeed = 0;
        this.curTurnSpeed = 0;
    }

    public Player(double x, double y, GameMap map, double moveSpeed, double turnSpeed) {
        this(new Vector(x, y), map, moveSpeed, turnSpeed);
    }

    public void update() {
        if (Double.compare(this.curTurnSpeed, 0) != 0) {
            this.turn(this.curTurnSpeed);
        }

        if (Double.compare(this.curMoveSpeed, 0)!= 0) {
            this.move(this.curMoveSpeed);
        }
    }

    public void move(double distance) {
        double radians = Math.toRadians(this.direction);

        double dx = Math.cos(radians) * distance;
        double dy = Math.sin(radians) * distance;

        Vector tmp = this.pos.clone();
        tmp.add(dx, dy);

        if (!this.map.checkSolid(tmp.getX(), tmp.getY())) {
            this.pos = tmp;
        }
    }



    public void turn(double degrees) {
        this.direction += degrees;

        // Ensure 0 <= player direction < 360.
        while(Double.compare(this.direction, 0) < 0) {
            this.direction += 360;
        } 
        while (Double.compare(this.direction, 360) >= 0) {
            this.direction -= 360;
        }
    }

    @Override
    public void draw(Graphics graphics) {
        int l = 3;

        // Draw the body.
        graphics.setColor(Color.GREEN);
        graphics.fillOval((int) this.pos.getX() - l, (int) this.pos.getY() - l, 2*l, 2*l);
        graphics.setColor(Color.BLACK);
        graphics.drawOval((int) this.pos.getX() - l, (int) this.pos.getY() - l, 2*l, 2*l);

        // Draw the current direction.
        double radians = Math.toRadians(this.direction);
        int x1 = (int) this.pos.getX();
        int y1 = (int) this.pos.getY();
        int x2 = (int) (Math.cos(radians) * 4*l + this.pos.getX());
        int y2 = (int) (Math.sin(radians) * 4*l + this.pos.getY());

        graphics.drawLine(x1, y1, x2, y2);
    }

    public class InputHandler implements KeyListener {
        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    curMoveSpeed = maxMoveSpeed;
                    break;
                case KeyEvent.VK_S:
                    curMoveSpeed = -maxMoveSpeed;
                    break;
                case KeyEvent.VK_A:
                    curTurnSpeed = -maxTurnSpeed;
                    break;
                case KeyEvent.VK_D:
                    curTurnSpeed = maxTurnSpeed;
                    break;
            }
        }

        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                case KeyEvent.VK_S:
                    curMoveSpeed = 0;
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_D:
                    curTurnSpeed = 0;
                    break;
            }
        }
    }
}
