package shapes;
import core.Entity;
import physics.Vector2D;
import utils.*;

import java.awt.geom.Path2D;

/**
 * An {@link Entity} with the bounding shape of a rectangle
 */
public class Rectangle extends Entity{

    /**
     * width and height of rectangle
     */
    private double height, width;

    /**
     * Creates a <code>Rectangle</code> with the specified mass and dimensions. Note that what you pass in for <code>width</code>
     * and <code>height</code> merely determines the 0 degree orientation of the <code>Rectangle</code>
     * @param mass mass of the <code>Rectangle</code>
     * @param height of rectangle
     * @param width of rectangle
     */
    public Rectangle(double mass, double height, double width){
        super(mass, createPath(height, width));
        this.height = height;
        this.width = width;
    }

    /**
     * creates path2d of rectangle
     * @param height of rectangle
     * @param width width of rectangle
     * @return path2d/shape of the rectangle
     */
    public static Path2D createPath(double height, double width){
        Vector2D[] points = new Vector2D[4];
        points[0] = new Vector2D(0,0);
        points[1] = new Vector2D(0, height);
        points[2] = new Vector2D(width, height);
        points[3] = new Vector2D(width, 0);
        return Path2DUtils.generatePath(points);
    }

    /**
     * access the height of the rectangle
     * @return height of rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * access width of the rectangle
     * @return width of the rectangle
     */
    public double getWidth() {
        return width;
    }
}
