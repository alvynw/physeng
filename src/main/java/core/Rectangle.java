package core;
import physics.Vector2D;
import utils.*;

import java.awt.geom.Path2D;

public class Rectangle extends Entity{
    private double height, width;
    public Rectangle(double mass, double height, double width){
        super(mass, createPath(height, width));
        this.height = height;
        this.width = width;
    }
    public static Path2D createPath(double height, double width){
        Vector2D[] points = new Vector2D[4];
        points[0] = new Vector2D(0,0);
        points[1] = new Vector2D(0, height);
        points[2] = new Vector2D(width, height);
        points[3] = new Vector2D(width, 0);
        return Path2DUtils.generatePath(points);
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
