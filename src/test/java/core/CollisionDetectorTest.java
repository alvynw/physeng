package core;

import javafx.util.Pair;
import org.junit.Test;
import physics.Vector2D;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CollisionDetectorTest {

    @Test
    public void sortAndSweepTest() {
        CollisionDetector detector = new CollisionDetector();


        ArrayList<Entity> list = new ArrayList<>();

        //empty list
        ArrayList<Pair<Entity, Entity>> pairs = detector.sortAndSweep(list);
        assertEquals(0, pairs.size());

        System.out.println();

        //one item list
        Vector2D[] square = {new Vector2D(0, 0), new Vector2D(1,0), new Vector2D(0, 1), new Vector2D(1,1)};
        Entity e1 = new Entity(10, square);
        list.add(e1);

        pairs = detector.sortAndSweep(list);
        assertEquals(0, pairs.size());

        System.out.println();


        //two item split apart
        Entity e2 = new Entity(10, square);
        e2.setInitialPosition(new Vector2D(10, 0));
        list.add(e2);

        pairs = detector.sortAndSweep(list);
        assertEquals(0, pairs.size());

        System.out.println();

        //two item together
        Entity e3 = new Entity(10, square);
        e3.setInitialPosition(new Vector2D(10, 0));
        list.add(e3);

        pairs = detector.sortAndSweep(list);
        assertEquals(1, pairs.size());
        assertEquals(pairs.get(0), new Pair<>(e2, e3));
    }
}
