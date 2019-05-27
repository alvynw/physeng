package math;

import physics.Vector2D;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Arrays;
import java.util.Stack;

import static utils.Path2DUtils.pathVertices;

/**
 * Implementation of the Graham scan to find the convex hull of a set of points.
 * Methods have been overloaded to support a variety of inputs.
 *
 * This implementation of the Graham scan iterates counterclockwise. The path returned is also the counterclockwise path.
 * In other words, the path returned starts at the bottom most, left most point, and traverses along the convex hull in
 * a counterclockwise loop.
 *
 * Time complexity: O(n)
 */
public class ConvexHull {

    public static Path2D getHull(Path2D path) {
        return hull(pathVertices(path));
    }

    public static Path2D getHull(double[] xpoints, double[] ypoints, int npoints) {

        if (npoints != xpoints.length || npoints != ypoints.length) {
            throw new IllegalArgumentException("npoints must be equal to the size of xpoints and ypoints");
        }

        if (npoints <= 2) {
            throw new IllegalArgumentException("Must have over 2 non-collinear points! Lines are not supported. To create a line," +
                    "use a thin rectangle");
        }

        Vector2D[] list = new Vector2D[npoints];

        for (int i = 0; i < npoints; i++) {
            list[i] = new Vector2D(xpoints[i], ypoints[i]);
        }

        return hull(list);
    }

    public static Path2D gethUll(Vector2D[] points) {
        double[][] t = new double[points.length][2];

        for (int i = 0; i < points.length; i++) {
            t[i] = points[i].toArray();
        }

        return getHull(t);
    }


    public static Path2D getHull(int[] xpoints, int[] ypoints, int npoints) {

        if (npoints != xpoints.length || npoints != ypoints.length) {
            throw new IllegalArgumentException("npoints must be equal to the size of xpoints and ypoints");
        }

        double[] dxpoints = new double[npoints];
        double[] dypoints = new double[npoints];

        for (int i = 0; i < npoints; i++) {
            dxpoints[i] = xpoints[i];
            dypoints[i] = ypoints[i];
        }

        return getHull(dxpoints, dypoints, npoints);
    }

    public static Path2D getHull(double[][] points) {
        double[] x = new double[points.length];
        double[] y = new double[points.length];

        for (int i = 0; i < points.length; i++) {
            x[i] = points[i][0];
            y[i] = points[i][1];
        }
        return getHull(x, y, points.length);
    }

    public static Path2D getHull(int[][] points) {
        int[] x = new int[points.length];
        int[] y = new int[points.length];

        for (int i = 0; i < points.length; i++) {
            x[i] = points[i][0];
            y[i] = points[i][1];
        }
        return getHull(x, y, points.length);
    }

    public static Path2D getHull(Polygon p) {

        int[] pxpoints = new int[p.npoints];
        int[] pypoints = new int[p.npoints];

        for (int i = 0; i < p.npoints; i++) {
            pxpoints[i] = p.xpoints[i];
            pypoints[i] = p.ypoints[i];
        }

        return getHull(pxpoints, pypoints, p.npoints);
    }

    private static Path2D hull(Vector2D[] list) {

        if (list.length <= 2) {
            throw new IllegalArgumentException("Must have over 2 non-collinear points! Lines are not supported. To create a line," +
                    "use a thin rectangle");
        }

        //find lowest in list

        Vector2D lowest = list[0];
        for (int i = 1; i < list.length; i++) {
            if (list[i].getY() < lowest.getY()) {
                lowest = list[1];
            }
        }

        Vector2D finalLowest = lowest;

        Arrays.sort(list, (vector1, vector2) -> {
            double differenceInAngle = Math.cos(Math.atan2(vector1.getY() - finalLowest.getY(), vector1.getX() - finalLowest.getX())) -
                    Math.cos(Math.atan2(vector2.getY() - finalLowest.getY(), vector2.getX() - finalLowest.getX()));
            if (differenceInAngle > 0) return -1;
            if (differenceInAngle < 0) return 1;
            return 0;
        });

        Stack<Vector2D> stack = new Stack<>();

        stack.push(list[0]);
        stack.push(list[1]);

        for (int i = 2; i <= list.length; i++) {
            Vector2D p = stack.pop();
            Vector2D pp = stack.pop();

            Vector2D curr;
            if (i == list.length) {
                curr = list[0];
            } else {
                curr = list[i];
            }


            double degreesTurnedLeft = (Math.atan2(p.getY() - pp.getY(), p.getX() - pp.getX()) + 2 * Math.PI) % (2 * Math.PI) + Math.PI -
                    (Math.atan2(curr.getY() - p.getY(), curr.getX() - p.getX()) % (2 * Math.PI) + 2 * Math.PI) % (2 * Math.PI);

            if (degreesTurnedLeft < Math.PI) {
                stack.push(pp);
                stack.push(p);
                stack.push(curr);
            } else {
                stack.push(pp);
                stack.push(curr);
            }
        }

        if (stack.size() < 3) {
            throw new IllegalArgumentException("Must have over 2 non-collinear points! Lines are not supported. To create a line," +
                    "use a thin rectangle");
        }

        Stack<Vector2D> reverse = new Stack<>();

        while (!stack.isEmpty()) {
            reverse.push(stack.pop());
        }


        Path2D path = new Path2D.Double();

        Vector2D origin = reverse.pop();

        path.moveTo(origin.getX(), origin.getY());

        while(reverse.size() > 1) {
            Vector2D e = reverse.pop();
            path.lineTo(e.getX(), e.getY());
        }

        path.closePath();

        return path;
    }

}
