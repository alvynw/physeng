package core;
import physics.Vector2D;
import utils.Path2DUtils;
import static org.junit.Assert.*;
public class SquareTest {
    public void constructorTest(){
        Square test = new Square(10, 15);
        assertTrue(10==test.getMass() &&15==test.getSide());
    }
    public void createPathTest(){
        double side = 15;
        Square test = new Square(10, side);
        for(Vector2D z:
                Path2DUtils.pathVertices(test.getShape())){
            assertTrue(z.getX()== side +side+test.getPosition().getX()|| z.getX()==test.getPosition().getX()&&
                    z.getY()==side+test.getPosition().getY()||z.getY()==test.getPosition().getY());
        }
    }
}
