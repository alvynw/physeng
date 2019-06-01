package physics;

import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;
import static utils.Path2DTestUtils.assertVector2DEquals;

public class Vector2DTest {

    final double TOLERANCE = 0.00001;

    @Test
    public void constructTest()
    {
        double x = 5.0;
        double y = 10.0;
        Vector2D vector = new Vector2D(x, y);
        assertNotNull(vector);

        Vector2D vector2 = new Vector2D();
        assertNotNull(vector2);
    }

    @Test
    public void xTest()
    {
        double x = 5.0;
        double y = 10.0;
        Vector2D vector = new Vector2D(x, y);

        double result = vector.getX();
        double expected = 5.0;

        assertEquals(expected, result, TOLERANCE);
    }

    @Test
    public void yTest()
    {
        double x = 5.0;
        double y = 10.0;
        Vector2D vector = new Vector2D(x, y);

        double result = vector.getY();
        double expected = 10.0;

        assertEquals(expected, result, TOLERANCE);
    }

    @Test
    public void magTest()
    {
        double x = 3;
        double y = 4;
        Vector2D vector = new Vector2D(x, y);

        double result = vector.getMag();
        double expected = 5.0;

        assertEquals(expected, result, TOLERANCE);
    }

    @Test
    public void addTest()
    {
        double x = 3;
        double y = 4;

        Vector2D vector = new Vector2D(x, y);
        Vector2D result = vector.add(new Vector2D(2, 5));
        Vector2D expected = new Vector2D(5, 9);

        assertVector2DEquals(expected, result, TOLERANCE);
    }

    @Test
    public void scaleTest()
    {
        double x = 3;
        double y = 4;

        Vector2D vector = new Vector2D(x, y);
        Vector2D result = vector.scale(3);
        Vector2D expected = new Vector2D(9, 12);

        assertVector2DEquals(expected, result, TOLERANCE);
    }

    @Test
    public void dotTest()
    {
        double x = 3;
        double y = 4;

        Vector2D vector = new Vector2D(x, y);
        double result = vector.dot(new Vector2D(2, 3));
        double expected = 18;

        assertEquals(expected, result, TOLERANCE);
    }

    @Test
    public void leftTest()
    {
        double x = 3;
        double y = 4;

        Vector2D vector = new Vector2D(x, y);
        Vector2D result = vector.normalLeft();
        Vector2D expected = new Vector2D(-4, 3);

        assertVector2DEquals(expected, result, TOLERANCE);
    }

    @Test
    public void rightTest()
    {
        double x = 3;
        double y = 4;

        Vector2D vector = new Vector2D(x, y);
        Vector2D result = vector.normalRight();
        Vector2D expected = new Vector2D(4, -3);

        assertVector2DEquals(expected, result, TOLERANCE);
    }

    @Test
    public void oppositeTest()
    {
        double x = 3;
        double y = 4;

        Vector2D vector = new Vector2D(x, y);
        Vector2D result = vector.opposite();
        Vector2D expected = new Vector2D(-3, -4);

        assertVector2DEquals(expected, result, TOLERANCE);
    }

    @Test
    public void arrayTest()
    {
        double x = 3;
        double y = 4;

        Vector2D vector = new Vector2D(x, y);
        double[] result = vector.toArray();
        double[] expected = {3, 4};
        Assert.assertArrayEquals(expected, result, TOLERANCE);
    }

    @Test
    public void toStringTest()
    {
        double x = 3;
        double y = 4;

        Vector2D vector = new Vector2D(x, y);
        String result = vector.toString();
        String expected = "[3.0, 4.0]";
        assertEquals(expected, result);
    }
}
