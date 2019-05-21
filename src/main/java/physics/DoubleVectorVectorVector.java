package physics;

public class DoubleVectorVectorVector {
    private double time;
    private Vector2D vector, vector1, vector2;

    public DoubleVectorVectorVector(double time, Vector2D vector, Vector2D vector1, Vector2D vector2) {
        this.time = time;
        this.vector = vector;
        this.vector1 = vector1;
        this.vector2 = vector2;
    }

    public Vector2D getVector2() {
        return vector2;
    }

    public void setVector2(Vector2D vector2) {
        this.vector2 = vector2;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Vector2D getVector() {
        return vector;
    }

    public void setVector(Vector2D vector) {
        this.vector = vector;
    }

    public Vector2D getVector1() {
        return vector1;
    }

    public void setVector1(Vector2D vector1) {
        this.vector1 = vector1;
    }
}
