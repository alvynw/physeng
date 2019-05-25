package physics;

public class DoubleVectorVector {
    private double time;
    private Vector2D vector, vector1;

    public DoubleVectorVector(double time, Vector2D vector, Vector2D vector1) {
        this.time = time;
        this.vector = vector;
        this.vector1 = vector1;
    }

    public double getTime() {
        return time;
    }


    public Vector2D getVector() {
        return vector;
    }



    public Vector2D getVector1() {
        return vector1;
    }


    }

