package shapes;

import org.junit.Test;
import physics.Vector2D;
import shapes.Ellipse;
import utils.Path2DUtils;

import static org.junit.Assert.*;

public class EllipseTest {

    final double TOLERANCE = 0.001;

    @Test
    public void ellipseTest() {
        Ellipse test = new Ellipse(10, 5, 6);
        assertNotNull(test);
        assertEquals(5, test.getWidth(), TOLERANCE);
        assertEquals(5, test.getHeight(), TOLERANCE);
        assertEquals(10, test.getMass(), TOLERANCE);
    }

}