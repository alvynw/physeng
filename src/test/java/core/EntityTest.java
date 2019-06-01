package core;

import org.junit.Test;
import physics.Force;
import physics.Vector2D;
import shapes.Circle;
import shapes.Rectangle;
import shapes.Square;

import java.awt.*;
import java.awt.geom.Path2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static utils.Path2DTestUtils.assertVector2DEquals;
import static utils.Path2DUtils.generatePath;
import static utils.Path2DUtils.pathVertices;

public class EntityTest {

    final double TOLERANCE = 0.00001;
    @Test
    public void constructTest()
    {
        double mass = 10.0;

        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(200, 0);
        path.lineTo(200, 200);
        path.lineTo(0, 200);
        path.closePath();
        Entity entity = new Entity(mass, path);
        assertNotNull(entity);

        Vector2D[] vector2D = {new Vector2D(0, 0),
                new Vector2D(2, 0),
                new Vector2D(2, 2),
                new Vector2D(0, 2)};
        Entity entity2 = new Entity(mass, vector2D);
        assertNotNull(entity2);

        double[] xpoints = {1.0, 0.0, 2.0, 0.0, 2.0};
        double[] ypoints = {1.0, 0.0, 2.0, 2.0, 0.0};
        Entity entity3 = new Entity(mass, xpoints, ypoints);
        assertNotNull(entity3);

        int[] intXPoints = {1, 0, 2, 0, 2};
        int[] intYpoints = {1, 0, 2, 2, 0};
        Entity entity4 = new Entity(mass, intXPoints, intYpoints);
        assertNotNull(entity4);

        double[][] dPoints2D = {{1.0, 1.0},{0.0, 0.0}, {2.0, 2.0}, {0.0, 2.0}, {2, 0.0}};
        Entity entity5 = new Entity(mass, dPoints2D);
        assertNotNull(entity5);

        int[][] iPoints2D = {{1, 1},{0, 0}, {2, 2}, {0, 2}, {2, 0}};
        Entity entity6 = new Entity(mass, iPoints2D);
        assertNotNull(entity6);

        Polygon p = new Polygon();
        p.addPoint(1, 2);
        p.addPoint(0, 0);
        p.addPoint(2, 2);
        p.addPoint(0, 2);
        p.addPoint(2, 0);
        Entity entity7 = new Entity(mass, p);
        assertNotNull(entity7);

    }

    @Test
    public void shapeTest()
    {
        double mass = 10.0;
        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(200, 0);
        path.lineTo(200, 200);
        path.lineTo(0, 200);
        path.closePath();

        Entity entity = new Entity(mass, path);

        Path2D result = entity.getShape();


        Vector2D[] vertices = pathVertices(result);

        Vector2D[] expected = {
                new Vector2D(-100, -100),
                new Vector2D(100, -100),
                new Vector2D(100, 100),
                new Vector2D(-100,100)};

        for (int i = 0; i < expected.length; i++) {
            assertVector2DEquals(expected[i], vertices[i], TOLERANCE);
        }

    }


    @Test
    public void massTest()
    {
        double mass = 10.0;
        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(200, 0);
        path.lineTo(200, 200);
        path.lineTo(0, 200);
        path.closePath();

        Entity entity = new Entity(mass, path);
        double newMass = entity.getMass();

        assertEquals(10.0, mass, TOLERANCE);
    }

    @Test
    public void positionTest()
    {
        double mass = 10.0;
        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(200, 0);
        path.lineTo(200, 200);
        path.lineTo(0, 200);
        path.closePath();

        Entity entity = new Entity(mass, path);
        entity.setInitialPosition(new Vector2D(20, 50));
        Vector2D pos = entity.getPosition();
        Vector2D expected = new Vector2D(20, 50);

        assertVector2DEquals(expected, pos, TOLERANCE);
    }

    @Test
    public void velocityTest()
    {
        double mass = 10.0;
        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(200, 0);
        path.lineTo(200, 200);
        path.lineTo(0, 200);
        path.closePath();

        Entity entity = new Entity(mass, path);
        entity.setInitialVelocity((new Vector2D(10, 20)));
        Vector2D velocity = entity.getVelocity();

        Vector2D expected = new Vector2D(10, 20);

        assertVector2DEquals(expected, velocity, TOLERANCE);
    }

    @Test
    public void accelerationTest()
    {
        double mass = 10.0;
        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(200, 0);
        path.lineTo(200, 200);
        path.lineTo(0, 200);
        path.closePath();

        Entity entity = new Entity(mass, path);
        entity.setInitialAcceleration(new Vector2D(5, 10));
        Vector2D acc = entity.getAcceleration();

        Vector2D expected = new Vector2D(5, 10);

        assertVector2DEquals(expected, acc, TOLERANCE);
    }

    @Test
    public void colorTest()
    {
        double mass = 10.0;
        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(200, 0);
        path.lineTo(200, 200);
        path.lineTo(0, 200);
        path.closePath();

        Entity entity = new Entity(mass, path);
        entity.setColor(Color.CYAN);
        Color color = entity.getColor();
        assertEquals(Color.CYAN, color);
    }

    @Test
    public void getOrientationTest()
    {
        double mass = 10.0;
        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(200, 0);
        path.lineTo(200, 200);
        path.lineTo(0, 200);
        path.closePath();

        Entity entity = new Entity(mass, path);
        entity.rotate(180);
        double orient = entity.getOrientation();

        assertEquals(180, orient, TOLERANCE);
    }

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

        final double TOLERANCE = 0.05;

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
