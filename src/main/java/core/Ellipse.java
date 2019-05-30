package core;

import physics.Vector2D;
import utils.Path2DUtils;
import java.awt.geom.Path2D;
import java.util.ArrayList;

/**
 * An {@link Entity} with a bounding shape of an ellipse
 */
public class Ellipse extends Entity {

    /**
     *width and height of the ellipse
     */
    private double width, height;

    /**
     * Creates an <code>Ellipse</code> with the specified mass, width, and height. Note that what you pass in for <code>width</code>width
     * and <code>height</code> merely determines the 0 degree orientation of the <code>Ellipse</code>
     * @param mass mass of the <code>Ellipse</code>
     * @param width width of the <code>Ellipse</code>
     * @param height height of the <code>Ellipse</code>
     */
    public Ellipse(double mass, double width, double height) {
        super(mass, createPath(width, height));
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the width of the <code>Ellipse</code>
     * @return the width of the <code>Ellipse</code>
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the <code>Ellipse</code>
     * @return the height of the <code>Ellipse</code>
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns a {@link Path2D} in the shape of an ellipse
     * @param width width of the <code>Ellipse</code>
     * @param height height of the <code>Ellipse</code>
     * @return a {@link Path2D} in the shape of an ellipse
     */
    private static Path2D createPath(double width, double height) {
        double halfY = height / 2;
        double halfX = width / 2;
        int counter = 0;
        ArrayList<Vector2D> coords = new ArrayList<>();
        for (double y = -1 * halfY; y <= halfY; y += height / 100) {
            double x = Math.sqrt(Math.abs(halfX * halfX * (1 - y * y / halfY / halfY)));
            coords.add(new Vector2D(x, y));
            coords.add(new Vector2D(-1 * x, y));
            counter += 2;
        }
        return Path2DUtils.generatePath(coords.toArray(new Vector2D[counter]));
    }
}
