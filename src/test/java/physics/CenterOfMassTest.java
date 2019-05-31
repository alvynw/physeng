package physics;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.awt.geom.Path2D;
import java.util.Vector;
import static org.junit.Assert.assertEquals;


import static physics.CenterOfMass.uniformCOM;
import static utils.Path2DTestUtils.assertVector2DEquals;

public class CenterOfMassTest {

    final double TOLERANCE = 0.00001;
    @Test
    public void test1()
    {
        Path2D path = new Path2D.Double();

        path.moveTo(0, 0);
        path.lineTo(200, 0);
        path.lineTo(200, 200);
        path.lineTo(0, 200);
        path.closePath();

        Vector2D result = uniformCOM(path);
        Vector2D expected = new Vector2D(100,100);
        assertVector2DEquals(expected, result, TOLERANCE);
    }

    @Test
    public void test2()
    {
        Vector2D[] points = {new Vector2D(0, 0),
            new Vector2D(200, 0),
            new Vector2D(200, 200),
            new Vector2D(0, 200)};

        Vector2D result = uniformCOM(points);
        Vector2D expected = new Vector2D(100,100);
        assertVector2DEquals(expected, result, TOLERANCE);
    }

    @Test
    public void test3()
    {
        double[] xpoints = {0.0, 200.0, 200.0, 0.0};
        double[] ypoints = {0.0, 0.0, 200.0, 200.0};

        int npoints = xpoints.length;

        Vector2D result = uniformCOM(xpoints, ypoints, npoints);
        Vector2D expected = new Vector2D(100,100);
        assertVector2DEquals(expected, result, TOLERANCE);
    }

    @Test
    public void test4()
    {
        int[] xpoints = {0, 200, 200, 0};
        int[] ypoints = {0, 0, 200, 200};

        int npoints = xpoints.length;

        Vector2D result = uniformCOM(xpoints, ypoints, npoints);
        Vector2D expected = new Vector2D(100, 100);
        assertVector2DEquals(expected, result, TOLERANCE);
    }

    public void test5()
    {

        double[][] points = new double[4][4];

    }

}
