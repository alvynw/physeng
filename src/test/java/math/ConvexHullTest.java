package math;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import physics.Vector2D;

import static org.junit.Assert.*;
import static utils.Path2DTestUtils.assertVector2DEquals;
import static utils.Path2DUtils.pathVertices;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Vector;

public class ConvexHullTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    final double TOLERANCE = 0.00001;

    @Test
    public void squareTest() {

        Polygon p = new Polygon();
        p.addPoint(1, 1);
        p.addPoint(0, 0);
        p.addPoint(2, 2);
        p.addPoint(0, 2);
        p.addPoint(2, 0);

        Path2D path = ConvexHull.getHull(p);

        Vector2D[] vertices = pathVertices(path);
        Vector2D[] expected = {new Vector2D(0, 0),
                new Vector2D(2, 0),
                new Vector2D(2, 2),
                new Vector2D(0, 2)};

        for (int i = 0; i < expected.length; i++) {
            assertVector2DEquals(vertices[i], expected[i], TOLERANCE);
        }
    }

    @Test
    public void collinearTest() {
        Polygon p = new Polygon();
        p.addPoint(1, 2);
        p.addPoint(0, 0);
        p.addPoint(2, 2);
        p.addPoint(0, 2);
        p.addPoint(2, 0);

        Path2D path = ConvexHull.getHull(p);

        Vector2D[] vertices = pathVertices(path);
        Vector2D[] expected = {new Vector2D(0, 0),
                new Vector2D(2, 0),
                new Vector2D(2, 2),
                new Vector2D(0, 2)};

        for (int i = 0; i < expected.length; i++) {
            assertVector2DEquals(vertices[i], expected[i], TOLERANCE);
        }
    }

    @Test
    public void lineTest() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Must have over 2 non-collinear points! Lines are not supported. To create a line," +
                "use a thin rectangle");

        double[][] vertices = {{0.0, 0.0}, {1.1, 1.1}};

        Path2D path = ConvexHull.getHull(vertices);
    }
}
