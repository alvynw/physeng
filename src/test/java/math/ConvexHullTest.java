package math;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static utils.Path2DUtils.pathVertices;

import java.awt.*;
import java.awt.geom.Path2D;

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

        double[][] vertices = pathVertices(path);
        double[][] expected = {{0, 0}, {0, 2}, {2, 2}, {2, 0}};

        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(vertices[i], expected[i], TOLERANCE);
        }
    }

    @Test
    public void collinearTest() {
        Polygon p = new Polygon();
        p.addPoint(1,2);
        p.addPoint(0,0);
        p.addPoint(2,2);
        p.addPoint(0,2);
        p.addPoint(2,0);

        Path2D path = ConvexHull.getHull(p);

        double[][] vertices = pathVertices(path);
        double[][] expected = {{0, 0}, {0, 2}, {2, 2,}, {2, 0}};

        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(vertices[i], expected[i], TOLERANCE);
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
