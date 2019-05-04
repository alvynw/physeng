package math;

import math.ConvexHull;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.*;

public class ConvexHullTest {
    @Test
    public void squareTest() {
        Polygon p = new Polygon();
        p.addPoint(1,1);
        p.addPoint(0,0);
        p.addPoint(2,2);
        p.addPoint(0,2);
        p.addPoint(2,0);
        Polygon newP = ConvexHull.getHull(p);

        int[] x = newP.xpoints;
        int[] y = newP.ypoints;

        assertEquals(x[0], 0);
        assertEquals(x[1], 2);
        assertEquals(x[2], 2);
        assertEquals(x[3], 0);
        assertEquals(x[4], x[0]);

        assertEquals(y[0], 0);
        assertEquals(y[1], 0);
        assertEquals(y[2], 2);
        assertEquals(y[3], 2);
        assertEquals(y[4], y[0]);
    }


    @Test
    public void collinearTest() {
        Polygon p = new Polygon();
        p.addPoint(1,2);
        p.addPoint(0,0);
        p.addPoint(2,2);
        p.addPoint(0,2);
        p.addPoint(2,0);
        Polygon newP = ConvexHull.getHull(p);

        int[] x = newP.xpoints;
        int[] y = newP.ypoints;

        assertEquals(x[0], 0);
        assertEquals(x[1], 2);
        assertEquals(x[2], 2);
        assertEquals(x[3], 0);
        assertEquals(x[4], x[0]);

        assertEquals(y[0], 0);
        assertEquals(y[1], 0);
        assertEquals(y[2], 2);
        assertEquals(y[3], 2);
        assertEquals(y[4], y[0]);
    }
}
