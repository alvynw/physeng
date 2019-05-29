package physics;

/**
 * Represents a 2-dimensional vector
 */
public class Vector2D {
    private double x;
    private double y;

    /**
     * Creates a <code>Vector2D</code> with the specified <code>x</code> and <code>y</code> component
     * @param x the x component
     * @param y the y component
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates the <code>(0; 0)</code> vector
     */
    public Vector2D() {
        x = 0;
        y = 0;
    }

    /**
     * Returns the x component of this <code>Vector2D</code>
     * @return the x component of this <code>Vector2D</code>
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y component of this <code>Vector2D</code>
     * @return the y component of this <code>Vector2D</code>
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the magnitude of this <code>Vector2D</code>
     * @return the magnitude of this <code>Vector2D</code>
     */
    public double getMag() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Adds two <code>Vector2D</code>s together and returns the result
     * @return the sum of the two <code>Vector2D</code>s
     */
    public Vector2D add(Vector2D that) {
        return new Vector2D(
                this.x + that.x,
                this.y + that.y
        );
    }

    /**
     * Returns the current <code>Vector2D</code> scaled by the specified amount
     * @param f the factor to scale by
     * @return the scaled <code>Vector2D</code>
     */
    public Vector2D scale(double f) {
        return new Vector2D(
                this.x * f,
                this.y * f
        );
    }

    /**
     * Returns the dot product of two <code>Vector2D</code>s
     * @param that the <code>Vector2D</code> to take the dot product with
     * @return the dot product of the two <code>Vector2D</code>s
     */
    public double dot(Vector2D that) {
        return this.x * that.x + this.y * that.y;
    }

    /**
     * Returns a <code>Vector2D</code> rotated 90 degrees counterclockwise to this <code>Vector2D</code> with the same magnitude.
     * @return a <code>Vector2D</code> rotated 90 degrees counterclockwise to this <code>Vector2D</code> with the same magnitude.
     */
    public Vector2D normalLeft() {
        return new Vector2D(-y, x);
    }

    /**
     * Returns a <code>Vector2D</code> rotated 90 degrees clockwise to this <code>Vector2D</code> with the same magnitude.
     * @return a <code>Vector2D</code> rotated 90 degrees clockwise to this <code>Vector2D</code> with the same magnitude.
     */
    public Vector2D normalRight() {
        return new Vector2D(y, -x);
    }

    /**
     * Returns a <code>Vector2D</code> rotated 180 degrees to this <code>Vector2D</code> with the same magnitude.
     * @return a <code>Vector2D</code> rotated 180 degrees to this <code>Vector2D</code> with the same magnitude.
     */
    public Vector2D opposite() {
        return new Vector2D(-x, -y);
    }

    /**
     * Returns the array form of this <code>Vector2D</code>. The array is of the form <code>double[2]</code>
     * @return the array form of this <code>Vector2D</code>
     */
    public double[] toArray() {
        double[] t = {x, y};
        return t;
    }

    /**
     * Returns the string representation of this <code>Vector2D</code>
     * @return the string representation of this <code>Vector2D</code>
     */
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
