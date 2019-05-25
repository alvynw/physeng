package utils;

import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.util.ArrayList;

public class Path2DUtils {
    public static double[][] pathVertices(Path2D path) {
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
