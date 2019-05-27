package shapes;

import core.Entity;
import physics.Vector2D;

import java.awt.*;
import java.awt.geom.Path2D;

import static java.lang.Math.random;
import static math.ConvexHull.getHull;
import static physics.CenterOfMass.uniformCOM;
import static utils.Path2DUtils.shift;


public class Circle extends Entity {
    private double myMass;
    private Path2D myPath;

    public Circle(double mass, Path2D shape)
    {
        super(mass, shape);
    }
}
