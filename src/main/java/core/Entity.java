package core;

import physics.Vector2D;

import java.awt.*;
import java.awt.geom.Path2D;

class Entity {
    protected double mass;
    protected Vector2D position;
    protected Vector2D velocity;
    protected Vector2D acceleration;
    protected Color color = Color.BLACK;

    private Path2D shape;

    public Entity(double mass, Vector2D position, Vector2D velocity, Vector2D acceleration, Path2D shape) {
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.shape = shape;
    }

    public Entity(double mass, Vector2D position, Path2D shape) {
        this.mass = mass;
        this.position = position;
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }

    public double getMass() {
        return mass;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public Vector2D getAcceleration() {
        return acceleration;
    }

    public Color getColor() {
        return color;
    }
}
