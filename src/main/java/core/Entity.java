package core;

import physics.Couple;
import physics.Force;
import physics.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static java.lang.Math.*;
import static math.ConvexHull.getHull;
import static utils.Path2DUtils.*;

/**
 * Class representing anything in an {@link Environment}
 * <br>
 * All constructors do the following:
 * <ul>
 *     <li>The color of this <code>Entity</code> is randomly assigned.</li>
 *     <li>The position, velocity, acceleration are set to <code>(0,0)</code> in their respective units.</li>
 *     <li>The bearing is set to <code>0</code> degrees</li>
 * </ul>
 *
 *
 * @see Environment
 */

public class Entity {

    /**
     * In kilograms
     */
    private double mass;

    /**
     * In kilogram-meter-squared
     * About the axis perpendicular to the plane passing through the center of mass
     */
    private double momentOfInertia;

    /**
     * In meters
     */
    private Vector2D position;

    /**
     * In meters per second
     */
    private Vector2D velocity;

    /**
     * In meters per second squared
     */
    private Vector2D acceleration;

    /**
     * Color of the <code>Entity</code>
     */
    private Color color = new Color((int) (random() * 256), (int) (random() * 256), (int) (random() * 256));

    /**
     * List containing <code>Forces</code> acting on this <code>Entity</code>
     */
    private ArrayList<Force> forces = new ArrayList<>();

    /**
     * List containing <code>Couples</code> acting on this <code>Entity</code>
     */
    private ArrayList<Couple> couples = new ArrayList<>();

    /**
     * <code>Path2D</code> representing the bounding shape of this <code>Entity</code>
     */
    private Path2D shape;

    /**
     * The orientation of the shape in degrees
     */
    private double degree = 0;

    /**
     * Creates a new <code>Entity</code> with the specified mass. <br>
     * The shape of this <code>Entity</code> is the convex hull of the {@link Path2D} passed in.
     *
     * @param mass the mass of this <code>Entity</code>
     * @param shape <code>Path2D</code> containing points
     */
    public Entity(double mass, Path2D shape) {
        this.mass = mass;
        this.position = new Vector2D(0, 0);
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);
        Path2D shape1 = getHull(shape);
        this.shape = shift(shape1, com().opposite());
    }

    /**
     * Creates a new <code>Entity</code> with the specified mass. <br>
     * The shape of this <code>Entity</code> is the convex hull of the {@link Vector2D}<code>[]</code> passed in,
     * where each {@link Vector2D} represents a point.
     *
     * @param mass the mass of this <code>Entity</code>
     * @param points {@link Vector2D}<code>[]</code> containing points
     */
    public Entity(double mass, Vector2D[] points) {
        this(mass, getHull(generatePath(points)));
    }

    /**
     * Creates a new <code>Entity</code> with the specified mass. <br>
     * <code>xpoints</code> and <code>ypoints</code> must be of the same length. <br>
     * The shape of this <code>Entity</code> is the convex hull of all points <code>(xpoints[i], ypoints[i])</code> where
     * <code>0 &le; i &lt; xpoints.length = ypoints.length </code>
     *
     * @param mass the mass of this <code>Entity</code>
     * @param xpoints <code>double[]</code> containing x-value of points
     * @param ypoints <code>double[]</code> containing y-value of points
     */
    public Entity(double mass, double[] xpoints, double[] ypoints) {
        this(mass, getHull(xpoints, ypoints, Math.min(xpoints.length, ypoints.length)));

    }

    /**
     * Creates a new <code>Entity</code> with the specified mass. <br>
     * <code>xpoints</code> and <code>ypoints</code> must be of the same length. <br>
     * The shape of this <code>Entity</code> is the convex hull of all points <code>(xpoints[i], ypoints[i])</code> where
     * <code>0 &le; i &lt; xpoints.length = ypoints.length </code>
     *
     * @param mass the mass of this <code>Entity</code>
     * @param xpoints <code>int[]</code> containing x-value of points
     * @param ypoints <code>int[]</code> containing y-value of points
     */
    public Entity(double mass, int[] xpoints, int[] ypoints) {
        this(mass, getHull(xpoints, ypoints, Math.min(xpoints.length, ypoints.length)));
    }

    /**
     * Creates a new <code>Entity</code> with the specified mass. <br>
     * <code>points</code> must be of the form <code>double[n][2]</code> where <code>n</code> is the number of points and the
     * <code>n</code>th point is given by <code>(points[n][0], points[n][1])</code>.
     * The shape of this <code>Entity</code> is the convex hull of all points
     *
     * @param mass the mass of this <code>Entity</code>
     * @param points <code>double[]</code> containing points
     */
    public Entity(double mass, double[][] points) {
        this(mass, getHull(points));
    }

    /**
     * Creates a new <code>Entity</code> with the specified mass. <br>
     * <code>points</code> must be of the form <code>int[n][2]</code> where <code>n</code> is the number of points and the
     * <code>n</code>th point is given by <code>(points[n][0], points[n][1])</code>.
     * The shape of this <code>Entity</code> is the convex hull of all points
     *
     * @param mass the mass of this <code>Entity</code>
     * @param points <code>int[]</code> containing points
     */
    public Entity(double mass, int[][] points) {
        this(mass, getHull(points));
    }

    /**
     * Creates a new <code>Entity</code> with the specified mass. <br>
     * The shape of this <code>Entity</code> is the convex hull of the {@link Polygon} passed in.
     *
     * @param mass the mass of this <code>Entity</code>
     * @param p {@link Polygon} containing points
     */
    public Entity(double mass, Polygon p) {
        this(mass, getHull(p));
    }

    /**
     * Returns the bounding shape in its current orientation of this <code>Entity</code>. The returned shape has its center
     * of mass located at (0,0)
     * @return the bounding shape in its current orientation of this <code>Entity</code>
     */
    public Path2D getShape() { return shape; }

    /**
     * Returns the mass, in kilograms, of this <code>Entity</code>
     * @return the mass, in kilograms, of this <code>Entity</code>
     */
    public double getMass() {
        return mass;
    }

    /**
     * Returns the moment of inertia, in kilogram meters squared, of this <code>Entity</code>
     * about the axis perpendicular to the plane passing through the center of mass.
     * @return the moment of inertia, in kilogram meters squared, of this <code>Entity</code>
     */
    public double getMomentOfInertia() {
        return momentOfInertia;
    }

    /**
     * Returns the position, in meters, of this <code>Entity</code>'s center of mass
     * @return the position, in meters, of this <code>Entity</code>'s center of mass
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Returns the velocity, in meters per second, of this <code>Entity</code>
     * @return the velocity, in meters per second, of this <code>Entity</code>
     */
    public Vector2D getVelocity() {
        return velocity;
    }

    /**
     * Returns the acceleration, in meters per second squared, of this <code>Entity</code>
     * @return the acceleration, in meters per second squared, of this <code>Entity</code>
     */
    public Vector2D getAcceleration() {
        return acceleration;
    }

    /**
     * Returns the color of this <code>Entity</code>
     * @return the color of this <code>Entity</code>
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the degrees the shape has turned relative to 0, the initial orientation of the shape
     * @return the degrees the shape has turned relative to 0, the initial orientation of the shape
     */
    public double getOrientation() {
        return degree;
    }

    /**
     * Sets the color of this <code>Entity</code> to the specified {@link Color}
     * @param color the {@link Color} to set this <code>Entity</code> to
     */
    public void setColor(Color color) { this.color = color; }

    /**
     * Sets the position of this <code>Entity</code> the specified position in meters <br>
     * To be used during the initialization process
     *
     * @param position the position to set this <code>Entity</code> to
     */
    public void setInitialPosition(Vector2D position) { this.position = position; }

    /**
     * Sets the velocity of this <code>Entity</code> the specified velocity in meters per second <br>
     * To be used during the initialization process
     *
     * @param velocity the position to set this <code>Entity</code> to
     */
    public void setInitialVelocity(Vector2D velocity) { this.velocity = velocity; }

    /**
     * Sets the velocity of this <code>Entity</code> the specified acceleration in meters per second squared <br>
     * To be used during the initialization process
     *
     * @param acceleration the position to set this <code>Entity</code> to
     */
    public void setInitialAcceleration(Vector2D acceleration) { this.acceleration = acceleration; }

    /**
     * Returns the x-y axis aligned bounding box of the shape of this <code>Entity</code>
     * @return the x-y axis aligned bounding box of the shape of this <code>Entity</code>
     */
    public Rectangle2D AABB() {

        double minX = 0;
        double maxX = 0;
        double minY = 0;
        double maxY = 0;

        for (Vector2D vertex : pathVertices(shape)) {
            minX = min(vertex.getX(), minX);
            maxX = max(vertex.getX(), maxX);
            minY = min(vertex.getY(), minY);
            maxY = max(vertex.getY(), maxY);
        }

        return new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);
    }

    /**
     * Adds a {@link Force} to this <code>Entity</code>
     * @param force the force to add
     */
    public void addForce(Force force) {
        forces.add(force);
    }

    /**
     * Adds a {@link Couple} to this <code>Entity</code>
     * @param couple the couple to add
     */
    public void addCouple(Couple couple) {
        couples.add(couple);
    }

    /**
     * Rotates the entity counterclockwise by the specified degrees <br>
     * The bounding shape of this <code>Entity</code> is rotated by the same amount.
     *
     * @param degree the amount of degrees to rotate this <code>Entity</code> counterclockwise
     */
    public void rotate(double degree) {
        this.degree += degree;
        this.degree %= 360;

        AffineTransform transform = new AffineTransform();
        transform.rotate(degree * Math.PI / 180);

        shape.transform(transform);
    }

    /**
     * Returns the center of mass of this <code>Entity</code>
     * @return the center of mass of this <code>Entity</code>
     */
    private Vector2D com() {

        /* Developer's note
         *
         * See https://en.wikipedia.org/wiki/Centroid#Of_a_polygon for formula and
         * see Han de Brujin's comment on https://math.stackexchange.com/questions/3177/why-doesnt-a-simple-mean-give-the-position-of-a-centroid-in-a-polygon
         * for the derivation
         *
         */

        Vector2D[] points = pathVertices(shape);

        double A = 0;
        for (int i = 0; i < points.length - 1; i++) {
            A += points[i].getX() * points[i + 1].getY() - points[i + 1].getX() * points[i].getY();
        }

        A /= 2;

        double sumX = 0;
        double sumY = 0;

        for (int i = 0; i < points.length - 1; i++) {
            sumX += (points[i].getX() + points[i + 1].getX()) * (points[i].getX() * points[i + 1].getY() - points[i + 1].getX() * points[i].getY());
            sumY += (points[i].getY() + points[i + 1].getY()) * (points[i].getX() * points[i + 1].getY() - points[i + 1].getX() * points[i].getY());;
        }

        sumX /= 6 * A;
        sumY /= 6 * A;

        Vector2D com = new Vector2D(sumX, sumY);
        return com;
    }

    /**
     * Returns the moment of inertia of this <code>Entity</code> rotating about the axis perpendicular to the plane and
     * passing through its center of mass
     * @return the moment of inertia of this <code>Entity</code>
     */
    private double momentOfInertia() {
        Vector2D[] vertices = pathVertices(this.shape);

        double totalArea = 0;
        double[] areas = new double[vertices.length];

        for (int i = 0; i < vertices.length - 1; i++) {

            Vector2D curr = vertices[i];
            Vector2D next = i == vertices.length - 1 ? vertices[0] : vertices[i + 1];

            areas[i] = areaOfTriangle(curr, next);
            totalArea += areas[i];
        }

        double momentOfInertia = 0;

        for (int i = 0; i < vertices.length - 1; i++) {

            Vector2D curr = vertices[i];
            Vector2D next = i == vertices.length - 1 ? vertices[0] : vertices[i + 1];

            momentOfInertia += triangleMomentOfInertia(curr, next, areas[i] / totalArea * mass);

        }

        return momentOfInertia;

    }

    /**
     * Computes the moment of inertia of the triangle with vertices at the origin, <code>p</code>, and <code>q</code>
     * rotating about the axis at the origin perpendicular to the plane of the triangle
     * @param p the first vertex
     * @param q the second vertex
     * @param m the mass of the triangle
     * @return the moment of inertia of the triangle
     */
    private double triangleMomentOfInertia(Vector2D p, Vector2D q, double m) {
        /*
         * See https://en.wikipedia.org/wiki/List_of_moments_of_inertia#Moments_of_inertia towards the bottom
         *
         * NO DERIVATION ON THAT PAGE!
         */

        return 1.0 / 6 * mass * (p.dot(p) + p.dot(q) + q.dot(q));
    }

    /**
     * Computes the area of the triangle with vertices at the origin, <code>p</code>, and <code>q</code>
     * @param p the first vertex
     * @param q the second vertex
     * @return the area of the triangle
     */
    private double areaOfTriangle(Vector2D p, Vector2D q) {
        double theta = acos(p.dot(q) / p.getMag() / q.getMag());
        return 0.5 * sin(theta) * p.getMag() * q.getMag();
    }
}
