import core.Entity;
import core.Environment;
import physics.Vector2D;
import shapes.Square;

import java.awt.*;

public class PolygonDemo {
    public static void main (String[] args) {

        Environment e = new Environment();

        Vector2D[] polygon = {new Vector2D(0, 0),
                new Vector2D(30, 30),
                new Vector2D(45, 45),
                new Vector2D(30, 60),
                new Vector2D(-15, 45)};

        Entity e1 = new Entity(10, polygon);

        e1.setInitialPosition(new Vector2D(10, 200));
        e1.setInitialVelocity(new Vector2D(100, 0));
        e1.setColor(Color.RED);

        Entity e2 = new Entity(30, polygon);

        e2.setInitialPosition(new Vector2D(500, 200));
        e2.setColor(Color.BLUE);

        Square s = new Square(30, 100);
        s.setInitialPosition(new Vector2D(800, 600));

//        Square s1 = new Square(200, 100);
//        s1.setInitialPosition(new Vector2D(360, 900));
//        s1.setInitialVelocity(new Vector2D(0, -100));

        e.add(e1);
        e.add(e2);
        e.add(s);
//        e.add(s1);

        e.simulate(1000, 1000 ,0.05);

    }
}
