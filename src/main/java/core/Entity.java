package core;

import physics.FollowingForce;
import physics.Vector2D;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import static java.lang.Math.random;
import static math.ConvexHull.getHull;

public class Entity {
    private double mass;
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;
    private Color color = new Color((int) (random() * 256), (int) (random() * 256), (int) (random() * 256));
    private ArrayList<FollowingForce> followingForces = new ArrayList<>();

    private Path2D shape;

    public Entity(double mass, Path2D shape) {
        this.mass = mass;
        this.position = new Vector2D(0, 0);
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);
        this.shape = getHull(shape);
    }

    public Entity(double mass, double[] xpoints, double[] ypoints) {
        this(mass, getHull(xpoints, ypoints, Math.min(xpoints.length, ypoints.length)));

    }

    public void addFollowingForce(FollowingForce force) {
        followingForces.add(force);
    }

    public Entity(double mass, int[] xpoints, int[] ypoints) {
        this(mass, getHull(xpoints, ypoints, Math.min(xpoints.length, ypoints.length)));
    }

    public Entity(double mass, double[][] points) {
        this(mass, getHull(points));
    }

    public Entity(double mass, int[][] points) {
        this(mass, getHull(points));
    }

    public Entity(double mass, Polygon p) {
        this(mass, getHull(p));
    }

    public Path2D getShape() { return shape; }

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
    public void setColor(Color color) { this.color = color; }

    public void setInitialVelocity(Vector2D velocity) { this.velocity = velocity; }

    public void setInitialPosition(Vector2D position) { this.position = position; }

    public void setInitialAcceleration(Vector2D acceleration) { this.acceleration = acceleration; }

}
