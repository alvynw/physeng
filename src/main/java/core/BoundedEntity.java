package core;

import vectors.Vector2D;

import java.awt.*;

public class BoundedEntity extends Entity {
    private Shape shape;

    public BoundedEntity(double mass, Vector2D position, Vector2D velocity, Vector2D acceleration, Shape shape) {
        super(mass, position, velocity, acceleration);
        this.shape = shape;
    }

    public BoundedEntity(double mass, Vector2D position, Shape shape) {
        super(mass, position);
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }
}
