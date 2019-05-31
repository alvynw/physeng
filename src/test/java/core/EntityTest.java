package core;

import org.junit.Test;
import physics.Vector2D;
import shapes.Circle;
import shapes.Rectangle;
import shapes.Square;

import java.awt.geom.Path2D;

import static org.junit.Assert.assertEquals;
import static utils.Path2DTestUtils.assertVector2DEquals;
import static utils.Path2DUtils.generatePath;
import static utils.Path2DUtils.pathVertices;

public class EntityTest {

    @Test
    public void rotationTest() {

        final double TOLERANCE = 0.001;

        Vector2D[] vertices = {new Vector2D(0, 0), new Vector2D(2, 0), new Vector2D(2, 2),  new Vector2D(0, 2)};

        Path2D asdf = generatePath(vertices);

        Vector2D[] asdfpath = pathVertices(asdf);

        Entity e = new Entity(10, asdf);

        Vector2D[] path = pathVertices(e.getShape());

        Vector2D[] expected = {new Vector2D(-1, -1), new Vector2D(1, -1), new Vector2D(1, 1),  new Vector2D(-1, 1)};

        for (int i = 0; i < path.length; i++) {
            assertVector2DEquals(path[i], expected[i], TOLERANCE);
        }

        e.rotate(45);

        path = pathVertices(e.getShape());

        Vector2D[] expectedRotation = {new Vector2D(0, -1.414214), new Vector2D(1.414214, 0), new Vector2D(0, 1.414214), new Vector2D(-1.414214, 0)};

        for (int i = 0; i < path.length; i++) {
            assertVector2DEquals(path[i], expectedRotation[i], TOLERANCE);
        }

    }

    @Test
    public void momentOfInertiaTest() {

        final double TOLERANCE = 1;

        //square
        Square s = new Square(10, 2);
        assertEquals(1.0 / 12 * s.getMass() * (s.getWidth() * s.getWidth() * 2), s.getMomentOfInertia(), TOLERANCE);

        //rectangle
        Rectangle r = new Rectangle(10, 2, 2);
        assertEquals(1.0 / 12 * r.getMass() * (r.getWidth() * r.getWidth() + r.getHeight() * r.getHeight()), r.getMomentOfInertia(), TOLERANCE);

        //circle
        Circle c = new Circle(10, 2);
        assertEquals(0.5 * c.getMass() * c.getRadius() * c.getRadius(), c.getMomentOfInertia(), TOLERANCE);
    }
}
