package core;

import javafx.util.Pair;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class CollisionDetector {

    public ArrayList<Pair<Entity, Entity>> sortAndSweep(ArrayList<Entity> list) {
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

}