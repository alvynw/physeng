package utils;

import math.ConvexHull;
import org.junit.Test;
import physics.Vector2D;

import java.awt.geom.Path2D;

import static testingUtils.Path2DTestUtils.assertVector2DEquals;
import static utils.Path2DUtils.*;

public class Path2DUtilsTest {

    final double TOLERANCE = 0.00001;

    @Test
    public void verticesTest()
    {
        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(200, 0);
        path.lineTo(100, 100);
        path.lineTo(200, 200);
        path.lineTo(150, 200);
        path.lineTo(0, 200);
        path.closePath();

        Path2D result = ConvexHull.getHull(path);
        Vector2D[] vertices = pathVertices(result);
        Vector2D[] expected = {new Vector2D(0, 0),
                new Vector2D(200, 0),
                new Vector2D(200, 200),
                new Vector2D(0, 200)};

        for (int i = 0; i < expected.length; i++) {
            assertVector2DEquals(expected[i], vertices[i], TOLERANCE);
        }

    }

    @Test
    public void shiftTest()
    {
        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(200, 0);
        path.lineTo(100, 100);
        path.lineTo(200, 200);
        path.lineTo(150, 200);
        path.lineTo(0, 200);
        path.closePath();

        Path2D result = ConvexHull.getHull(path);
        Path2D rPath = shift(result, new Vector2D(100, 200));
        Vector2D[] vertices = pathVertices(rPath);

        Vector2D[] expected = {new Vector2D(100, 200),
                new Vector2D(300, 200),
                new Vector2D(300, 400),
                new Vector2D(100, 400)};

        for (int i = 0; i < expected.length; i++) {
            assertVector2DEquals(expected[i], vertices[i], TOLERANCE);
        }
    }

    @Test
    public void generatePathTest()
    {

        Path2D path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(200, 0);
        path.lineTo(100, 100);
        path.lineTo(200, 200);
        path.lineTo(150, 200);
        path.lineTo(0, 200);
        path.closePath();

        Path2D result = ConvexHull.getHull(path);
        Vector2D[] vertices = pathVertices(result);
        Path2D newPath = generatePath(vertices);
        Vector2D[] newVertices = pathVertices(result);

        for (int i = 0; i < newVertices.length; i++) {
            assertVector2DEquals(newVertices[i], vertices[i], TOLERANCE);
        }

    }
}
