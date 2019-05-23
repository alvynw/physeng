package core;

import physics.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import static java.lang.Math.random;
import static jdk.nashorn.internal.objects.NativeMath.max;
import static jdk.nashorn.internal.objects.NativeMath.min;
import static math.ConvexHull.getHull;
import static physics.CenterOfMass.uniformCOM;
import static utils.Path2DUtils.pathVertices;
import static utils.Path2DUtils.shift;

/**
 * Class representing anything in an `Environment`
 *
 * @see Environment
 */

public class Entity {

    protected double mass;
    protected Vector2D position;
    protected Vector2D velocity;
    protected Vector2D acceleration;
    protected double degree = 0;
    protected Color color = new Color((int) (random() * 256), (int) (random() * 256), (int) (random() * 256));

    private Path2D shape;

    public Entity(double mass, Path2D shape) {
        this.mass = mass;
        this.position = new Vector2D(0, 0);
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);

        Path2D shape1 = getHull(shape);

        this.shape = shift(shape1, uniformCOM(shape1).opposite());
    }

    public Entity(double mass, double[] xpoints, double[] ypoints) {
        this(mass, getHull(xpoints, ypoints, Math.min(xpoints.length, ypoints.length)));
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

    /**
     * Returns the axis aligned bounding box of the shape of this entity
     * @return the axis aligned bounding box of the shape of this entity
     */
    public Rectangle2D AABB() {

        double minX = 0;
        double maxX = 0;
        double minY = 0;
        double maxY = 0;

        for (Vector2D vertex : pathVertices(shape)) {
            minX = min(vertex, minX);
            maxX = max(vertex, maxX);
            minY = min(vertex, minY);
            maxY = max(vertex, maxY);
        }

        return new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);
    }

    //ccw
    public void rotate(double degree) {
        this.degree += degree;
        this.degree %= 360;

        AffineTransform transform = new AffineTransform();
        transform.rotate(degree * Math.PI / 180);

        shape.transform(transform);
    }
}
