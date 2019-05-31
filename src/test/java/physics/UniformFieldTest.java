package physics;

import org.junit.Test;
import shapes.Circle;

import static utils.Path2DTestUtils.assertVector2DEquals;

public class UniformFieldTest {

    final double TOLERANCE = 0.001;

    @Test
    public void uniformFieldTest() {
        UniformField gravity = new UniformField((t, e) -> new Vector2D(0, -9.8 * e.getMass()));
        Circle c = new Circle(10, 10);
        c.setInitialPosition(new Vector2D(100, 100));

        double dt = 0.005;
        for (double t = 0; t < 5; t += dt) {
            assertVector2DEquals(new Vector2D(0, -98), gravity.apply(t, c), TOLERANCE);
            c.setInitialVelocity(c.getVelocity().add(new Vector2D(0, -9.8 * dt)));
            c.setInitialPosition(c.getPosition().add(c.getVelocity().scale(dt)));
        }

        assertVector2DEquals(new Vector2D(100, -22.5), c.getPosition(), 0.5);

    }
}
