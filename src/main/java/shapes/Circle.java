package shapes;

import core.Entity;
import physics.Vector2D;

import java.awt.geom.Path2D;

public class Circle extends Entity {
    private double myMass;
    private Path2D myPath;

    public Circle(double mass, Path2D shape) {
        super(mass, shape);
    }
}
