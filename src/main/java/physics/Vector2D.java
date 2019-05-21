package physics;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector2D() {
        x = 0;
        y = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getMag() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D add(Vector2D that) {
        return new Vector2D(
                this.x + that.x,
                this.y + that.y
        );
    }

    public Vector2D scale(double f) {
        return new Vector2D(
                this.x * f,
                this.y * f
        );
    }

    public double dot(Vector2D that) {
        return this.x * that.x + this.y * that.y;
    }

    public Vector2D normalLeft() {
        return new Vector2D(-y, x);
    }
    public Vector2D normalRight() {
        return new Vector2D(y, -x);
    }

    public Vector2D opposite() {
        return new Vector2D(-x, -y);
    }

    public double[]toArray() {
        double[] t = {x, y};
        return t;
    }

    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
