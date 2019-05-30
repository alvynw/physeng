package core;

import physics.Vector2D;
import utils.Path2DUtils;
import java.awt.geom.Path2D;
import java.util.ArrayList;

/**
 * Creates an ellipse of specified width and length.
 */
public class Ellipse extends Entity {
    /**
     *width and height of the ellipse
     */
    private double width, height;

    /**
     * constructs an ellipse
     * @param mass of ellipse
     * @param width of ellipse
     * @param height of ellipse
     */
    public Ellipse(double mass, double width, double height) {
        super(mass, createPath(width, height));
        this.width = width;
        this.height = height;
    }

    /**
     * acess width of the ellipse
     * @return width of the ellipse
     */
    public double getWidth() {
        return width;
    }

    /**
     * acess height of ellipse
     * @return height of ellipse
     */
    public double getHeight() {
        return height;
    }

    /**
     * creates path2d of ellipse
     * @param width of the ellipse
     * @param height of the ellipse
     * @return shape/path2d of the ellipse
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
