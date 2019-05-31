package shapes;
import org.junit.Test;
import physics.Vector2D;
import shapes.Rectangle;
import utils.Path2DUtils;
import static org.junit.Assert.*;
public class RectangleTest {

    final double TOLERANCE = 0.001;

    @Test
    public void rectangleTest(){
        Rectangle test = new Rectangle(10, 15, 7);
        assertNotNull(test);
        assertEquals(10, test.getMass(), TOLERANCE);
        assertEquals(15, test.getHeight(), TOLERANCE);
        assertEquals(7, test.getWidth(), TOLERANCE);
    }

}
