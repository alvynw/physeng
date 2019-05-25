package utils;

import physics.Vector2D;

import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.util.ArrayList;

public class Path2DUtils {
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

    public static Path2D shift(Path2D path, Vector2D shift) {
        Vector2D[] vertices = pathVertices(path);

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = vertices[i].add(shift);
        }

        return generatePath(vertices);
    }


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
