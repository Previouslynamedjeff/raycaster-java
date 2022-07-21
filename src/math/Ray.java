package src.math;

import java.awt.Graphics;
import src.window.Drawable;

public class Ray implements Drawable {
    private final static double STEP = 2;
    private Vector startPos;
    private Vector direction;
    private double angle;

    public Ray(Vector startPos, double angle) {
        this.startPos = startPos;
        this.direction = new Vector(STEP, 0);
        this.setAngle(angle);
    }

    public void setAngle(double angle) {
        this.angle = angle;
        double radians = Math.toRadians(angle);

        double x = Math.cos(radians) * direction.getMagnitude();
        double y = Math.sin(radians) * direction.getMagnitude();

        this.direction.set(x, y);
    }

    public Vector getEndPos() {
        return Vector.sum(this.startPos, this.direction);
    }

    public void lengthen() {
        this.direction.setLength(this.direction.getMagnitude() + STEP);
    }

    public void resetLength() {
        this.direction.set(STEP, 0);
        this.setAngle(this.angle);
    }

    @Override
    public void draw(Graphics graphics) {
        int x1 = (int) this.startPos.getX();
        int y1 = (int) this.startPos.getY();
        int x2 = (int) (this.startPos.getX() + this.direction.getX());
        int y2 = (int) (this.startPos.getY() + this.direction.getY());

        graphics.drawLine(x1, y1, x2, y2);
    }

    @Override
    public String toString() {
        return this.startPos + " --> " + this.getEndPos() + " | dir: " + this.direction; 
    }
}
