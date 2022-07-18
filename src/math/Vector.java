package src.math;

public class Vector {
    public static final Vector ZERO = new Vector(0, 0);

    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    public void add(Vector other) {
        this.x += other.getX();
        this.y += other.getY();
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void sub(Vector other) {
        this.x -= other.getX();
        this.y -= other.getY();
    }

    public void sub(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void scale(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public void normalize() {
        double mag = this.getMagnitude();
        
        if (Double.compare(mag, 0.0) != 0) {
            this.scale(1 / mag);
        } else {
            this.scale(0);
        }
    }

    // --- Getters and Setters ---

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getMagnitude() {
        return Math.sqrt(this.x*this.x + this.y*this.y);
    }

    public Vector getNormalized() {
        Vector copy = this.clone();
        copy.normalize();
        return copy;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Vector clone() {
        return new Vector(this.x, this.y);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
