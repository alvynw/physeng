package physics;

import java.awt.*;
import java.awt.geom.Path2D;


import static utils.Path2DUtils.pathVertices;

/**
 * Finds the center of mass of a geometric object assumed to be uniformly distributed.
 *
 * Intended to never be initialized; use static methods
 */

public class CenterOfMass {

    /**
     * Returns the center of mass of the specified {@link Path2D}
     * @param path the {@link Path2D} to find the center of mass of
     * @return the center of mass of the specified {@link Path2D}
     */
    public static Vector2D uniformCOM(Path2D path) {
        return uniformCOM(pathVertices(path));
    }

    /**
     * Returns the center of mass of the specified points. A point is represented by a {@link Vector2D}.
     * @param points the {@link Vector2D}<code>[]</code> to find the center of mass of
     * @return the center of mass of the specified points
     */
    public static Vector2D uniformCOM(Vector2D[] points) {
        double[][] t = new double[points.length][2];

        for (int i = 0; i < points.length; i++) {
            t[i] = points[i].toArray();
        }

        return uniformCOM(t);
    }

    /**
     * Returns the center of mass of the specified points. <br>
     * <code>xpoints</code> and <code>ypoints</code> must be of the same length and must equal to <code>npoints</code>. <br>
     * The shape of this <code>Entity</code> is the center of mass of all points <code>(xpoints[i], ypoints[i])</code> where
     * <code>0 &le; i &lt; npoints </code>
     * @param xpoints <code>double[]</code> containing x-value of points
     * @param ypoints <code>double[]</code> containing y-value of points
     * @param npoints the length of xpoints and ypoints
     * @return the center of mass of the specified points
     */
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

    /**
     * Returns the center of mass of the specified points. <br>
     * <code>xpoints</code> and <code>ypoints</code> must be of the same length and must equal to <code>npoints</code>. <br>
     * The shape of this <code>Entity</code> is the center of mass of all points <code>(xpoints[i], ypoints[i])</code> where
     * <code>0 &le; i &lt; npoints </code>
     * @param xpoints <code>int[]</code> containing x-value of points
     * @param ypoints <code>int[]</code> containing y-value of points
     * @param npoints the length of xpoints and ypoints
     * @return the center of mass of the specified points
     */
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

    /**
     * Returns the center of mass of the specified points. <br>
     * <code>points</code> must be of the form <code>double[n][2]</code> where <code>n</code> is the number of points and the
     * <code>n</code>th point is given by <code>(points[n][0], points[n][1])</code>.
     * @param points the points to take the center of mass of
     * @return the center of mass of the specified points
     */
    public static Vector2D uniformCOM(double[][] points) {
        double[] x = new double[points.length];
        double[] y = new double[points.length];

        for (int i = 0; i < points.length; i++) {
            x[i] = points[i][0];
            y[i] = points[i][1];
        }
        return uniformCOM(x, y, points.length);
    }

    /**
     * Returns the center of mass of the specified points. <br>
     * <code>points</code> must be of the form <code>int[n][2]</code> where <code>n</code> is the number of points and the
     * <code>n</code>th point is given by <code>(points[n][0], points[n][1])</code>.
     * @param points the points to take the center of mass of
     * @return the center of mass of the specified points
     */
    public static Vector2D uniformCOM(int[][] points) {
        int[] x = new int[points.length];
        int[] y = new int[points.length];

        for (int i = 0; i < points.length; i++) {
            x[i] = points[i][0];
            y[i] = points[i][1];
        }
        return uniformCOM(x, y, points.length);
    }

    /**
     * Returns the center of mass of the specified {@link Polygon}
     * @param p the {@link Polygon} to find the center of mass of
     * @return the center of mass of the specified {@link Path2D}
     */
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
