package math;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import physics.Vector2D;

import static testingUtils.Path2DTestUtils.assertVector2DEquals;
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

        Vector2D[] vertices = pathVertices(path);
        Vector2D[] expected = {new Vector2D(0, 0),
                new Vector2D(2, 0),
                new Vector2D(2, 2),
                new Vector2D(0, 2)};

        for (int i = 0; i < expected.length; i++) {
            assertVector2DEquals(expected[i], vertices[i], TOLERANCE);
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
            assertVector2DEquals(expected[i], vertices[i], TOLERANCE);
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

    @Test
    public void doubleArrPoints()
    {
        double[] xpoints = {1.0, 0.0, 2.0, 0.0, 2.0};
        double[] ypoints = {1.0, 0.0, 2.0, 2.0, 0.0};

        int npoints = xpoints.length;
        Path2D path = ConvexHull.getHull(xpoints, ypoints, npoints);
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
    public void doubleArrExceptions()
    {
        double[] xpoints = {1.0, 0.0, 2.0, 0.0, 2.0};
        double[] ypoints = {1.0, 0.0, 2.0, 2.0, 0.0};

        int npoints = 4;
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("xpoints and ypoints must be of equal length and must be equal to npoints");
        Path2D path = ConvexHull.getHull(xpoints, ypoints, npoints);

        int smallNPoints = 1;
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Must have over 2 non-collinear points! Lines are not supported. " +
                "To create a line, use a thin rectangle");
        Path2D path2 = ConvexHull.getHull(xpoints, ypoints, smallNPoints);
    }

    @Test
    public void vectorTest()
    {

        Vector2D[] vector = {new Vector2D(1, 1),
                new Vector2D(0, 0),
                new Vector2D(2, 2),
                new Vector2D(0, 2),
                new Vector2D(2, 0)};

        Path2D path = ConvexHull.getHull(vector);
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
    public void intArrPoints()
    {
        int[] xpoints = {1, 0, 2, 0, 2};
        int[] ypoints = {1, 0, 2, 2, 0};

        int npoints = xpoints.length;
        Path2D path = ConvexHull.getHull(xpoints, ypoints, npoints);
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
    public void intArrExceptions()
    {
        int[] xpoints = {1, 0, 2, 0, 2};
        int[] ypoints = {1, 0, 2, 2, 0};

        int npoints = 4;
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("npoints must be equal to the size of xpoints and ypoints");
        Path2D path = ConvexHull.getHull(xpoints, ypoints, npoints);
    }

    @Test
    public void double2DArray() {

        double[][] arr = {{1.0, 1.0}, {0.0, 0.0}, {2.0, 2.0}, {0.0, 2.0}, {2, 0.0}};
        Path2D path = ConvexHull.getHull(arr);

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
    public void d2DArrayException()
    {
        double[][] arr = {{1.0, 1.0, 1.0}, {0.0, 0.0}, {2.0, 2.0}, {0.0, 2.0}, {2, 0.0}};

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("points must be of the shape double[n][2]");
        Path2D path = ConvexHull.getHull(arr);
    }

    @Test
    public void int2DArray()
    {
        int[][] arr ={{1, 1},{0, 0}, {2, 2}, {0, 2}, {2, 0}};
        Path2D path = ConvexHull.getHull(arr);

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
    public void i2DArrayException()
    {
        int[][] arr ={{1, 1, 1},{0, 0}, {2, 2}, {0, 2}, {2, 0}};

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("points must be of the shape int[n][2]");
        Path2D path = ConvexHull.getHull(arr);
    }

    @Test
    public void polyTest()
    {
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
}
