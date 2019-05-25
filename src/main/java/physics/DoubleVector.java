package physics;

public class DoubleVector {
    private double time;
    private Vector2D vector;
    public DoubleVector(double time, Vector2D vector){
        this.time = time;
        this.vector = vector;
    }

    public double getTime() {
        return time;
    }


    public Vector2D getVector() {
        return vector;
    }

}
