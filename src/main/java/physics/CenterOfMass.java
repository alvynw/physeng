package physics;

import java.awt.*;
import java.awt.geom.Path2D;


import static utils.Path2DUtils.pathVertices;

public class CenterOfMass {

    public static Vector2D uniformCOM(Path2D path) {
        return uniformCOM(pathVertices(path));
    }

    public static Vector2D uniformCOM(double[] xpoints, double[] ypoints, int npoints) {

        if (npoints != xpoints.length || npoints != ypoints.length) {
            throw new IllegalArgumentException("npoints must be equal to the size of xpoints and ypoints");
        }

        if (npoints <= 0) {
            throw new IllegalArgumentException("Must have at least 1 point to calculate center of mass");
        }

       Vector2D[] list = new Vector2D[npoints];

        for (int i = 0; i < npoints; i++) {
            list[i] = new Vector2D(xpoints[i], ypoints[i]);
        }

        return com(list);
    }

    public static Vector2D uniformCOM(Vector2D[] points) {
        double[][] t = new double[points.length][2];

        for (int i = 0; i < points.length; i++) {
            t[i] = points[i].toArray();
        }

        return uniformCOM(t);
    }

    public static Vector2D uniformCOM(int[] xpoints, int[] ypoints, int npoints) {

        if (npoints != xpoints.length || npoints != ypoints.length) {
            throw new IllegalArgumentException("npoints must be equal to the size of xpoints and ypoints");
        }

        double[] dxpoints = new double[npoints];
        double[] dypoints = new double[npoints];

        for (int i = 0; i < npoints; i++) {
            dxpoints[i] = xpoints[i];
            dypoints[i] = ypoints[i];
        }

        return uniformCOM(dxpoints, dypoints, npoints);
    }

    public static Vector2D uniformCOM(double[][] points) {
        double[] x = new double[points.length];
        double[] y = new double[points.length];

        for (int i = 0; i < points.length; i++) {
            x[i] = points[i][0];
            y[i] = points[i][1];
        }
        return uniformCOM(x, y, points.length);
    }

    public static Vector2D uniformCOM(int[][] points) {
        int[] x = new int[points.length];
        int[] y = new int[points.length];

        for (int i = 0; i < points.length; i++) {
            x[i] = points[i][0];
            y[i] = points[i][1];
        }
        return uniformCOM(x, y, points.length);
    }

    public static Vector2D uniformCOM(Polygon p) {

        int[] pxpoints = new int[p.npoints];
        int[] pypoints = new int[p.npoints];

        for (int i = 0; i < p.npoints; i++) {
            pxpoints[i] = p.xpoints[i];
            pypoints[i] = p.ypoints[i];
        }

        return uniformCOM(pxpoints, pypoints, p.npoints);
    }

    private static Vector2D com(Vector2D[] points) {
        double sumX = 0;
        double sumY = 0;

        for (int i = 0; i < points.length; i++) {
            sumX += points[i].getX();
            sumY += points[i].getY();
        }

        Vector2D com = new Vector2D(sumX / points.length, sumY / points.length);
        return com;
    }
    

}
