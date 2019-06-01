package core;

import javafx.util.Pair;
import physics.Vector2D;

import javax.xml.ws.soap.MTOM;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static utils.Path2DUtils.pathVertices;
import static utils.Path2DUtils.shift;

public class CollisionHandler {

    public ArrayList<Pair<Entity, Entity>> sortAndSweep(List<Entity> list) {
        list.sort((o1, o2) -> (int) (o1.getPosition().getX() + o1.AABB().getX() - o2.getPosition().getX() - o2.AABB().getX()));

        ArrayList<Entity> active = new ArrayList<>();

        ArrayList<Pair<Entity, Entity>> listOfPairs = new ArrayList<>();

        for (Entity e : list) {
            double pos = e.getPosition().getX() + e.AABB().getX();

            for (int i = active.size() - 1; i >= 0; i--) {
                Entity a = active.get(i);
                Rectangle2D aabb = a.AABB();
                double rightBound = a.getPosition().getX() + aabb.getX() + aabb.getWidth();
                if (pos > rightBound) {
                    active.remove(a);
                } else {
                    listOfPairs.add(new Pair<>(a, e));
                }
            }

            active.add(e);
        }

        return listOfPairs;
    }

    public Vector2D SAT(Entity a, Entity b) {
        System.out.println("SAT called");

        Vector2D[] aVertices = pathVertices(shift(a.getShape(), a.getPosition()));
        Vector2D[] bVertices = pathVertices(shift(b.getShape(), b.getPosition()));

        Vector2D minAxis = aVertices[0].subtract(aVertices[1]).normalLeft().unit();
        double minIntersection = Double.MAX_VALUE;

        for (int i = 0; i < aVertices.length; i++) {

            Vector2D curr = aVertices[i];
            Vector2D next = i == aVertices.length - 1 ? aVertices[0] : aVertices[i + 1];

            Vector2D axis = curr.subtract(next).normalLeft().unit();

            DoubleRange projA = project(aVertices, axis);
            DoubleRange projB = project(bVertices, axis);

            if (!projA.intersects(projB)) {
//                System.out.println("hello");
//                System.out.println(curr);
//                System.out.println(next);
//                System.out.println(projA);
//                System.out.println(projB);
//                System.out.println(axis);
                return null;
            } else {

                double intersectionDist = projA.intersectionDistance(projB);
                if (intersectionDist < minIntersection) {
                    minIntersection = intersectionDist;
                    minAxis = axis;
                }
            }
        }

        for (int i = 0; i < bVertices.length; i++) {
            Vector2D curr = bVertices[i];
            Vector2D next = i == bVertices.length - 1 ? bVertices[0] : bVertices[i + 1];

            Vector2D axis = curr.subtract(next).normalLeft().unit();

            DoubleRange projA = project(aVertices, axis);
            DoubleRange projB = project(bVertices, axis);

            if (!projA.intersects(projB)) {
                System.out.println("hello2");
                System.out.println(axis);
                return null;
            } else {

                double intersectionDist = projA.intersectionDistance(projB);
                if (intersectionDist < minIntersection) {
                    minIntersection = intersectionDist;
                    minAxis = axis;
                }
            }
        }

        System.out.println(minAxis.scale(minIntersection / minAxis.getMag()));

        return minAxis.scale(minIntersection / minAxis.getMag());

    }


    private DoubleRange project(Vector2D[] vertices, Vector2D axis) {
        double min = vertices[0].dot(axis);
        double max = vertices[0].dot(axis);

        for (int i = 0; i < vertices.length; i++) {
            min = Math.min(min, vertices[i].dot(axis));
            max = Math.max(max, vertices[i].dot(axis));
        }

        return new DoubleRange(min, max);
    }

    private class DoubleRange {
        double min;
        double max;
        public DoubleRange(double min, double max) {
            this.min = min;
            this.max = max;
        }

        public boolean intersects(DoubleRange that) {
            return this.min < that.max && that.min < this.max;
        }

        public double intersectionDistance(DoubleRange that) {
            if (intersects(that)) {
                return Math.min(Math.abs(that.max - this.min), Math.abs(this.max - that.min));
            } else {
                return 0;
            }
        }
        public String toString() {
            return "min: " + min + " max: " + max;
        }
    }

    public void resolveCollisions(Entity a, Entity b, Vector2D MTV) {

        double currMag = a.getPosition().subtract(b.getPosition()).getMag();

        //MTV is the direction entity a needs to go
        if (a.getPosition().add(MTV).subtract(b.getPosition()).getMag() < currMag) {
            System.out.println("changing");
            MTV = MTV.opposite();
        } else {
            System.out.println("not changing");
        }

        Vector2D MTVnorm = MTV.unit();

        Vector2D v_r = a.getVelocity().subtract(b.getVelocity());

        final double TOLERANCE = 0.1;

        Vector2D[] aVertices = pathVertices(shift(a.getShape(), a.getPosition().add(MTV)));
        Vector2D[] bVertices = pathVertices(shift(b.getShape(), b.getPosition()));


//        Vector2D aRadius = null;
//        Vector2D aPoint = null;
//
//        Vector2D bRadius = null;
//        Vector2D bPoint = null;
//
//        for (int i = 0; i < aVertices.length; i++) {
//            Vector2D curr = aVertices[i];
//            Vector2D next = i == aVertices.length - 1 ? aVertices[0] : aVertices[i + 1];
//
//            for (int j = 0; j < bVertices.length; j++) {
//                double slope1 = (curr.getY() - bVertices[j].getY()) / (curr.getX() - bVertices[j].getX());
//
//                double slope2 = (bVertices[j].getY() - next.getY()) / (bVertices[j].getX() - next.getX());
//
//                if (Math.abs(slope1 - slope2) <= 0.1) {
//                    bRadius = pathVertices(b.getShape())[j];
//                    bPoint = bVertices[j];
//                }
//            }
//        }
//
//        for (int i = 0; i < bVertices.length; i++) {
//            Vector2D curr = bVertices[i];
//            Vector2D next = i == bVertices.length - 1 ? bVertices[0] : bVertices[i + 1];
//
//            for (int j = 0; j < aVertices.length; j++) {
//                double slope1 = (curr.getY() - aVertices[j].getY()) / (curr.getX() - aVertices[j].getX());
//
//                double slope2 = (aVertices[j].getY() - next.getY()) / (aVertices[j].getX() - next.getX());
//
//                if (Math.abs(slope1 - slope2) <= 0.1) {
//                    aRadius = pathVertices(b.getShape())[j];
//                    aPoint = pathVertices(b.getShape())[j];
//                }
//            }
//        }


//        if (aRadius == null) {
//            aRadius = bPoint.add(a.getPosition().opposite());
//        }
//
//        if (bRadius == null) {
//            bRadius = aPoint.add(b.getPosition().opposite());
//        }


//        double j_r = v_r.scale(-(1 + 1)).dot(MTVnorm) / (1.0 / a.getMass() + 1.0 / b.getMass() +
//                (aRadius.cross((aRadius.cross(MTVnorm))).scale(1.0 / a.getMomentOfInertia()).add(
//                        bRadius.cross((bRadius.cross(MTVnorm))).scale(1.0 / b.getMomentOfInertia()))).dot(MTVnorm));

        double j_r = v_r.scale(-(1 + 1)).dot(MTVnorm) / (1.0 / a.getMass() + 1.0 / b.getMass());

        a.addImpulseLinear(MTVnorm.scale(j_r));

        b.addImpulseLinear(MTVnorm.scale(j_r).opposite());

//        a.addImpulseAngular(j_r * (aRadius.cross(MTVnorm)));
//
//        b.addImpulseAngular(-j_r * (bRadius.cross(MTVnorm)));
    }


}

