package core;

import javafx.util.Pair;
import physics.Vector2D;

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static utils.Path2DUtils.pathVertices;

public class CollisionDetector {

    public Set<Pair<Entity, Entity>> sortAndSweep(ArrayList<Entity> list) {
        list.sort((o1, o2) -> (int) (o1.getPosition().getX() + o1.AABB().getX() - o2.getPosition().getX() - o2.AABB().getX()));

        ArrayList<Entity> active = new ArrayList<>();

        HashSet<Pair<Entity, Entity>> set = new HashSet<>();

        for (Entity e : list) {
            double pos = e.getPosition().getX() - e.AABB().getX();

            for (int i = active.size() - 1; i >= 0; i--) {
                Entity a = active.get(i);
                Rectangle2D aabb = a.AABB();
                double rightBound = a.getPosition().getX()  + aabb.getX() + aabb.getWidth();
                if (pos > rightBound) {
                    active.remove(a);
                } else {
                    set.add(new Pair<>(a, e));
                }
            }
        }

        return set;
    }

}