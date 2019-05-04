package core;

import physics.Vector2D;

import java.awt.*;

public class BoundedEntity extends Entity {
    private Polygon shape;
    public BoundedEntity(double mass, Vector2D position, Vector2D velocity, Vector2D acceleration, Polygon shape) {
        super(mass, position, velocity, acceleration);

        this.shape = shape;
    }

    public BoundedEntity(double mass, Vector2D position, Polygon shape) {
        super(mass, position);
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }
}
