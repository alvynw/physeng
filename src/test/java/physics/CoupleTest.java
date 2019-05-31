package physics;

import org.junit.Test;

import static java.lang.Math.PI;
import static java.lang.Math.sin;
import static org.junit.Assert.assertEquals;

public class CoupleTest {

    final double TOLERANCE = 0.001;

    @Test
    public void coupleTest() {
        Couple c = new Couple((t) -> t * t * sin(PI / 2));
        assertEquals(5 * 5 * sin(PI / 2), c.apply(5.0), TOLERANCE);
    }
}
