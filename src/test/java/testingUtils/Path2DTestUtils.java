package testingUtils;

import physics.Vector2D;

import static org.junit.Assert.assertEquals;

public class Path2DTestUtils {

    public static void assertVector2DEquals(Vector2D a, Vector2D b, double tolerance) {
        assertEquals(a.getX(), b.getX(), tolerance);
        assertEquals(a.getY(), b.getY(), tolerance);
    }
}
