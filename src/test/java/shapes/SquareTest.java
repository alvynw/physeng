package shapes;
import org.junit.Test;
import physics.Vector2D;
import shapes.Square;
import utils.Path2DUtils;
import static org.junit.Assert.*;
public class SquareTest {

    final double TOLERANCE = 0.001;

    @Test
    public void squareTest(){
        Square test = new Square(10, 15);
        assertNotNull(test);
        assertEquals(10, test.getMass(), TOLERANCE);
        assertEquals(15, test.getSide(), TOLERANCE);
    }

}
