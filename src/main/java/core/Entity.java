package core;

import physics.Vector2D;

import java.awt.*;

public class Entity {
    protected double mass;
    protected Vector2D position;
    protected Vector2D velocity;
    protected Vector2D acceleration;
    protected Color color = Color.BLACK;

    public Entity(double mass, Vector2D position, Vector2D velocity, Vector2D acceleration) {
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public Entity(double mass, Vector2D position) {
        this.mass = mass;
        this.position = position;
        this.velocity = new Vector2D(0,0);
        acceleration = new Vector2D(0,0);
    }

    public Entity(double mass, Vector2D position, Vector2D velocity) {
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        acceleration = new Vector2D(0,0);
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

    public void setColor(Color color) {
        this.color = color;
    }
    public void setAcceleration(Vector2D acceleration){
        this.acceleration=acceleration;
    }
}
