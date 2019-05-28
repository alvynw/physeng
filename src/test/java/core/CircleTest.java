package core;
import physics.Vector2D;
import utils.Path2DUtils;
import static org.junit.Assert.*;

public class CircleTest  {

    public void circleConstructor(){
        Circle test = new Circle(10, 5);
        assertTrue(test.getRadius()==5&&test.getMass()==10);

    }
    public void createPathTest(){
        double radius = 5;
        Circle test = new Circle(10, radius);
        for(Vector2D iterator : Path2DUtils.pathVertices(test.getShape())){
            assertTrue(iterator.getX()*iterator.getX()+iterator.getY()*iterator.getY() == test.getRadius()*test.getRadius());
        }
    }



}