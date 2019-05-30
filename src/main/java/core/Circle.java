package core;

import physics.Vector2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import utils.*;

/**
 * creates a circle of specified radius
 */
public class Circle extends Entity {
    /**
     * radius of the circle
     */
    private double radius;

    /**
     * constructs a circle
     * @param mass of circle
     * @param radius of circle
     */
    public Circle(double mass,double radius ){
        super(mass,createPath(radius));
        this.radius = radius;

    }

    /**
     * creates path2d of the circle
     * @param radius of the circle
     * @return path2d/shape of circle
     */
    public static Path2D createPath(double radius){
        int counter = 0;
        ArrayList<Vector2D> coords = new ArrayList<>();
        for(double x = -1*radius; x <=radius; x+=radius/100) {
            double y = Math.sqrt(radius * radius - x * x);
            coords.add(new Vector2D(x, y));
            coords.add(new Vector2D(x, y * -1));
            counter += 2;

        }
        return Path2DUtils.generatePath(coords.toArray(new Vector2D[counter]));

    }

    /**
     * access radius of the circle
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

//    public Circle(double mass, Vector2D position, Vector2D velocity, double radius){
//        super(mass, position, velocity, new Polygon());
//        this.radius = radius;
//    }




}
