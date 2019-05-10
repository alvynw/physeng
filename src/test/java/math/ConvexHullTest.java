package math;

import math.ConvexHull;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.util.ArrayList;

public class ConvexHullTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    final double TOLERANCE = 0.00001;

    @Test
    public void squareTest() {

        Polygon p = new Polygon();
        p.addPoint(1, 1);
        p.addPoint(0, 0);
        p.addPoint(2, 2);
        p.addPoint(0, 2);
        p.addPoint(2, 0);

        Path2D path = ConvexHull.getHull(p);

        double[][] vertices = hullVertices(path);
        double[][] expected = {{0, 0}, {0, 2}, {2, 2}, {2, 0}};

        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(vertices[i], expected[i], TOLERANCE);
        }
    }

    @Test
    public void collinearTest() {
        Polygon p = new Polygon();
        p.addPoint(1,2);
        p.addPoint(0,0);
        p.addPoint(2,2);
        p.addPoint(0,2);
        p.addPoint(2,0);

        Path2D path = ConvexHull.getHull(p);

        double[][] vertices = hullVertices(path);
        double[][] expected = {{0, 0}, {0, 2}, {2, 2,}, {2, 0}};

        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(vertices[i], expected[i], TOLERANCE);
        }
    }

    @Test
    public void lineTest() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Must have over 2 non-collinear points! Lines are not supported. To create a line," +
                "use a thin rectangle");

        double[][] vertices = {{0.0, 0.0}, {1.1, 1.1}};

        Path2D path = ConvexHull.getHull(vertices);
    }

    public static double[][] hullVertices(Path2D path) {
        ArrayList<double[]> list = new ArrayList<>();
        int index = 0;

        double[] currentSegData = new double[6];

        for (PathIterator iterator = path.getPathIterator(null); !iterator.isDone(); iterator.next()) {
            int status = iterator.currentSegment(currentSegData);

            if (status == PathIterator.SEG_MOVETO ||
                    status == PathIterator.SEG_LINETO) {
                double[] coord = {currentSegData[0], currentSegData[1]};
                list.add(coord);
            }
        }

        double[][] vertices = new double[list.size()][2];

        for (int i = 0; i < list.size(); i++) {
            vertices[i] = list.get(i);
        }

        return vertices;
    }
}
