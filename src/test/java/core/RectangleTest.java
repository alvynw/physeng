package core;
import physics.Vector2D;
import utils.Path2DUtils;
import static org.junit.Assert.*;
public class RectangleTest {
    public void constructorTest(){
        Rectangle test = new Rectangle(10, 15, 10);
        assertTrue(10==test.getMass() &&15==test.getHeight()&&10==test.getWidth());
    }
    public void createPathTest(){
        double y = 20;
        double x = 15;
        Rectangle test = new Rectangle(10, y, x);
        for(Vector2D z:
            Path2DUtils.pathVertices(test.getShape())){
            assertTrue(z.getX()== x +test.getPosition().getX()|| z.getX()==test.getPosition().getX()&&
                    z.getY()==y+test.getPosition().getY()||z.getY()==test.getPosition().getY());
        }
    }
}
