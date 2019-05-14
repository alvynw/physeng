package math;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import static utils.Path2DUtils.pathVertices;

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

        double[][] list = new double[npoints][2];

        for (int i = 0; i < npoints; i++) {
            list[i][0] = xpoints[i];
            list[i][1] = ypoints[i];
        }

        return hull(list);
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

    private static Path2D hull(double[][] list) {

        //find lowest in list

        double[] lowest = list[0];
        for (int i = 1; i < list.length; i++) {
            if (list[i][1] < lowest[1]) {
                lowest = list[1];
            }
        }

        double[] finalLowest = lowest;

        Arrays.sort(list, (o1, o2) -> {
            double differenceInAngle = Math.cos(Math.atan2(o1[1] - finalLowest[1], o1[0] - finalLowest[0])) -
                    Math.cos(Math.atan2(o2[1] - finalLowest[1], o2[0] - finalLowest[0]));
            if (differenceInAngle > 0) return -1;
            if (differenceInAngle < 0) return 1;
            return 0;
        });

        Stack<double[]> stack = new Stack<>();

        stack.push(list[0]);
        stack.push(list[1]);

        for (int i = 2; i <= list.length; i++) {
            double[] p = stack.pop();
            double[] pp = stack.pop();

            double[] curr;
            if (i == list.length) {
                curr = list[0];
            } else {
                curr = list[i];
            }


            double degreesTurnedLeft = (Math.atan2(p[1] - pp[1], p[0] - pp[0]) + 2 * Math.PI) % (2 * Math.PI) + Math.PI -
                    (Math.atan2(curr[1] - p[1], curr[0] - p[0]) % (2 * Math.PI) + 2 * Math.PI) % (2 * Math.PI);

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

        Path2D path = new Path2D.Double();

        double[] origin = stack.pop();

        path.moveTo(origin[0], origin[1]);

        while(stack.size() > 1) {
            double[] e = stack.pop();
            path.lineTo(e[0], e[1]);
        }

        path.closePath();

        return path;
    }

}
