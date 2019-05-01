package core;

import vectors.Vector2D;

class Entity {
    protected double mass;
    protected Vector2D position;
    protected Vector2D velocity;
    protected Vector2D acceleration;

    public Entity(double mass, Vector2D position, Vector2D velocity, Vector2D acceleration) {
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public Entity(double mass, Vector2D position) {
        this.mass = mass;
        this.position = position;
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
}
