package core;

import javafx.util.Pair;
import org.junit.Test;
import physics.Vector2D;
import shapes.Circle;
import shapes.Square;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CollisionHandlerTest {

    @Test
    public void sortAndSweepTest() {
        CollisionHandler detector = new CollisionHandler();


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

    @Test
    public void SATTest() {
        final double TOLERANCE = 0.001;

        CollisionHandler detector = new CollisionHandler();
        Square s1 = new Square(10, 100);
        //s1.setInitialPosition(new Vector2D(405, 500));
        s1.setInitialPosition(new Vector2D(405, 500));

        Square s2 = new Square(10, 100);
        s2.setInitialPosition(new Vector2D(595, 500));

       System.out.println(detector.SAT(s1, s2));
       //detector.resolveCollisions(s2, s1, detector.SAT(s2, s1));

    }




}
