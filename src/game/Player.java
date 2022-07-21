package src.game;

import src.math.Vector;
import src.math.Ray;
import src.map.GameMap;

import src.window.Drawable;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Player implements Drawable {
    private Vector pos;
    private double angle;
    private double viewAngle;
    private Ray[] rays;
    private GameMap map;
    private double maxMoveSpeed;
    private double maxTurnSpeed;
    private double curMoveSpeed;
    private double curTurnSpeed;

    public Player(Vector startPos, GameMap map, double moveSpeed, double turnSpeed, double viewAngle) {
        this.pos = startPos.clone();
        this.map = map;
        this.angle = 0;
        this.viewAngle = viewAngle;
        this.maxMoveSpeed = moveSpeed;
        this.maxTurnSpeed = turnSpeed;
        this.curMoveSpeed = 0;
        this.curTurnSpeed = 0;

        this.rays = new Ray[1];
        rays[0] = new Ray(this.pos, this.angle);
    }

    public Player(double x, double y, GameMap map, double moveSpeed, double turnSpeed, double viewAngle) {
        this(new Vector(x, y), map, moveSpeed, turnSpeed, viewAngle);
    }

    public void update() {
        boolean moved = false;
        if (Double.compare(this.curTurnSpeed, 0) != 0) {
            this.turn(this.curTurnSpeed);
            moved = true;
        }

        if (Double.compare(this.curMoveSpeed, 0)!= 0) {
            this.move(this.curMoveSpeed);
            moved = true;
        }

        if (moved) {
            this.castRays();
        }
    }

    public void castRays() {
        for (Ray ray: this.rays) {
            ray.setAngle(this.angle);
            ray.resetLength();
            while (!this.map.checkSolid(ray.getEndPos())) {
                ray.lengthen();
            }
        }
    }

    public void move(double distance) {
        double radians = Math.toRadians(this.angle);

        double dx = Math.cos(radians) * distance;
        double dy = Math.sin(radians) * distance;

        Vector tmp = this.pos.clone();
        tmp.add(dx, dy);

        if (!this.map.checkSolid(tmp.getX(), tmp.getY())) {
            this.pos.add(dx, dy);
        }
    }

    public void turn(double degrees) {
        this.angle += degrees;

        // Ensure 0 <= player direction < 360.
        while(Double.compare(this.angle, 0) < 0) {
            this.angle += 360;
        } 
        while (Double.compare(this.angle, 360) >= 0) {
            this.angle -= 360;
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

        for (Ray ray: this.rays) {
            ray.draw(graphics);
        }
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
