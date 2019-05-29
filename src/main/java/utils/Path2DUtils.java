package utils;

import physics.Vector2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.util.ArrayList;

/**
 * Utility class for {@link Path2D} <br>
 * Intended to not be instantiated; use static methods.
 */
public class Path2DUtils {

    /**
     * Converts a path to a {@link Vector2D}<code>[]</code> which contains the same vertices
     * as the {@link Path2D} in the same order
     * @param path the {@link Path2D} to convert
     * @return a {@link Vector2D}<code>[]</code> which contains the same vertices
     *         as the {@link Path2D} in the same order
     */
    public static Vector2D[] pathVertices(Path2D path) {
        ArrayList<double[]> list = new ArrayList<>();
        double[] currentSegData = new double[6];

        for (PathIterator iterator = path.getPathIterator(null); !iterator.isDone(); iterator.next()) {
            int status = iterator.currentSegment(currentSegData);

            if (status == PathIterator.SEG_MOVETO ||
                    status == PathIterator.SEG_LINETO) {
                double[] coord = {currentSegData[0], currentSegData[1]};
                list.add(coord);
            }
        }

        Vector2D[] vertices = new Vector2D[list.size()];

        for (int i = 0; i < list.size(); i++) {
            vertices[i] = new Vector2D(list.get(i)[0], list.get(i)[1]);
        }

        return vertices;
    }

    /**
     * Returns a new {@link Path2D} that is the specified {@link Path2D} translated by the specified translation {@link Vector2D}
     * @param path the {@link Path2D} to shift
     * @param shift the {@link Vector2D} to shift by
     * @return a new {@link Path2D} that is the specified {@link Path2D} translated by the specified translation {@link Vector2D}
     */
    public static Path2D shift(Path2D path, Vector2D shift) {
        Vector2D[] vertices = pathVertices(path);

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = vertices[i].add(shift);
        }

        return generatePath(vertices);
    }

    /**
     * Creates a {@link Path2D} using the specified {@link Vector2D}<code>[]</code>. The {@link Path2D} is given by
     *
     * <pre>
     *     (vertices[0], vertices[1]), (vertices[1], vertices[2]), ... , (vertices[n-3], vertices[n-2], (vertices[n-2], vertices[n-1])
     * </pre>
     *
     * where <code>n = vertices.length</code>
     *
     * @param vertices the {@link Vector2D}<code>[]</code> to create a {@link Path2D} from
     * @returna {@link Path2D} using the specified {@link Vector2D}<code>[]</code>
     */
    public static Path2D generatePath(Vector2D[] vertices) {

        if (vertices.length == 0) throw new IllegalArgumentException("Must be at least 1 point");

        Vector2D origin = vertices[0];

        Path2D path = new Path2D.Double();

        path.moveTo(origin.getX(), origin.getY());

        for (int i = 1; i < vertices.length; i++) {
            path.lineTo(vertices[i].getX(), vertices[i].getY());
        }

        path.closePath();

        return path;
    }
}
