package shapes;

import org.junit.Test;
import physics.Vector2D;
import utils.Path2DUtils;

import static org.junit.Assert.*;

public class CircleTest {

    final double TOLERANCE = 0.001;

    @Test
    public void circleTest() {
        Circle test = new Circle(10, 5);
        assertNotNull(test);
        assertEquals(5, test.getRadius(), TOLERANCE);
        assertEquals(10, test.getMass(), TOLERANCE);
    }

}