package core;

import org.junit.Test;
import physics.Vector2D;

import java.awt.geom.Path2D;

import static utils.Path2DTestUtils.assertVector2DEquals;
import static utils.Path2DUtils.generatePath;
import static utils.Path2DUtils.pathVertices;

public class EntityTest {

    final double TOLERANCE = 0.00001;

    @Test
    public void rotationTest() {
        Vector2D[] vertices = {new Vector2D(0, 0), new Vector2D(2, 0), new Vector2D(2, 2),  new Vector2D(0, 2)};

        Path2D asdf = generatePath(vertices);

        Vector2D[] asdfpath = pathVertices(asdf);

//        for (int i = 0; i < asdfpath.length; i++) {
//            System.out.println(asdfpath[i]);
//        }

        Entity e = new Entity(10, asdf);

        Vector2D[] path = pathVertices(e.getShape());

//        for (int i = 0; i < path.length; i++) {
//            System.out.println(path[i]);
//        }

        Vector2D[] expected = {new Vector2D(-1, -1), new Vector2D(1, -1), new Vector2D(1, 1),  new Vector2D(-1, 1)};

        for (int i = 0; i < path.length; i++) {
            System.out.println(i);
            assertVector2DEquals(path[i], expected[i], TOLERANCE);
        }
    }
}
