package core;
import junit.framework.JUnit4TestAdapter;
import physics.Vector2D;
import utils.Path2DUtils;
import static org.junit.Assert.*;

public class EllipseTest  {

    public void ellipseConstructor(){
        Ellipse test = new Ellipse(10, 5 , 6);
        assertTrue(test.getWidth()==5&&test.getMass()==10&&test.getHeight()==6);

    }
    public void createPathTest(){
        double width = 10;
        double height = 20;
        Ellipse test = new Ellipse(10, width, height);
        double halfX = test.getWidth()/2;
        double halfY = test.getHeight()/2;
        for(Vector2D iterator : Path2DUtils.pathVertices(test.getShape())){

            assertTrue(iterator.getX()*iterator.getX()/(halfX*halfX)+iterator.getY()*iterator.getY()/(halfY*halfY) == 1);
        }
    }
//    public static void main( String args[] )
//    {
//        org.junit.runner.JUnitCore.runClasses( EllipseTest.class );
//    }
//    public static junit.framework.Test suite()
//    {
//        return new JUnit4TestAdapter( EllipseTest.class );
//    }
}