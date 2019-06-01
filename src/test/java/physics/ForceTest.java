package physics;

import org.junit.Test;

import static testingUtils.Path2DTestUtils.assertVector2DEquals;

public class ForceTest {

    final double TOLERANCE = 0.001;

    @Test
    public void forceTest() {
        Force f = new Force((t) -> new Vector2D(t, t));
        assertVector2DEquals(new Vector2D(5,5), f.apply(5.0), TOLERANCE);
    }
}
