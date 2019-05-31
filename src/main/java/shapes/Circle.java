package shapes;

import core.Entity;

/**
 * An {@link Entity} with a bounding shape of a circle
 */
public class Circle extends Ellipse {

    /**
     * Creates a <code>Circle</code> with the specified mass and radius
     * @param mass mass of the <code>Circle</code>
     * @param radius radius of the <code>Circle</code>
     */
    public Circle(double mass, double radius ){
        super(mass, radius * 2, radius * 2);
    }

    /**
     * Returns the radius of the <code>Circle</code>
     * @return the radius of the <code>Circle</code>
     */
    public double getRadius() {
        return getHeight() / 2;
    }

}
