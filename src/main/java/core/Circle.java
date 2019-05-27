package core;

import com.sun.deploy.config.VerboseDefaultConfig;
import physics.Vector2D;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import utils.*;
public class Circle extends Entity {
    private double radius;
    private int[] xPoints;
    private int[] yPoints;
    private int nPoints;
    public Circle(double mass,double radius ){
        super(mass,createPath(radius));
        this.radius = radius;

    }
    public static Path2D createPath(double radius){
        int counter = 0;
        ArrayList<Vector2D> coords = new ArrayList<>();
        for(double x = -1*radius; x <=radius; x+=.01){
            coords.add(new Vector2D(x, Math.sqrt(radius*radius-x*x)));
            counter++;

        }
        return Path2DUtils.generatePath(coords.toArray(new Vector2D[counter]));

    }
//    public Circle(double mass, Vector2D position, Vector2D velocity, double radius){
//        super(mass, position, velocity, new Polygon());
//        this.radius = radius;
//    }




}
