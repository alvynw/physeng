package math;

import java.awt.*;
import java.util.Arrays;
import java.util.Stack;

public class ConvexHull {

    public static Polygon getHull(Polygon p) {
        return getHull(p.xpoints, p.ypoints, p.npoints);
    }

    public static Polygon getHull(int[] xpoints, int[] ypoints, int npoints) {
        if (npoints > xpoints.length || npoints > ypoints.length) {
            throw new IllegalArgumentException("Not enough points in lists!");
        }

        if (npoints <= 2) {
            throw new IllegalArgumentException("Must have over 2 points! Lines are not supported");
        }

        int[][] list = new int[npoints][2];

        for (int i = 0; i < npoints; i++) {
            list[i][0] = xpoints[i];
            list[i][1] = ypoints[i];
        }

        return hull(list);

    }

    private static Polygon hull(int[][] list) {


        //find lowest in list

        int[] lowest = list[0];
        for (int i = 1; i < list.length; i++) {
            if (list[i][1] < lowest[1]) {
                lowest = list[1];
            }
        }

        int[] finalLowest = lowest;

        Arrays.sort(list, (o1, o2) -> {
            double differenceInAngle = Math.cos(Math.atan2(o1[1] - finalLowest[1], o1[0] - finalLowest[0])) -
                    Math.cos(Math.atan2(o2[1] - finalLowest[1], o2[0] - finalLowest[0]));
            if (differenceInAngle > 0) return -1;
            if (differenceInAngle < 0) return 1;
            return 0;
        });

        Stack<int[]> stack = new Stack<>();

        stack.push(list[0]);
        stack.push(list[1]);

        for (int i = 2; i <= list.length; i++) {
            int[] p = stack.pop();
            int[] pp = stack.pop();

            int[] curr;
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

        Polygon p = new Polygon();

        for (int[] e : stack) {
            p.addPoint(e[0], e[1]);
        }

        return p;

    }

}
