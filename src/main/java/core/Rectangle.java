package core;
import physics.Vector2D;
import utils.*;

import java.awt.geom.Path2D;

/**
 * Creates a rectangle of specified height and width
 */
public class Rectangle extends Entity{
    /**
     * width and height of rectangle
     */
    private double height, width;

    /**
     * constructs a rectangle
     * @param mass of entity
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
