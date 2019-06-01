import core.Environment;
import physics.Couple;
import physics.Force;
import physics.Vector2D;
import shapes.Circle;
import shapes.Square;

import java.awt.*;

public class EngineTest {
    public static void main (String[] args) {

        Environment e = new Environment();

        Square c = new Square(100, 50);
        c.setColor(Color.RED);
        c.setInitialPosition(new Vector2D(10, 500));
        c.setInitialVelocity(new Vector2D(10, 0));
        c.addForce(new Force(((t) -> new Vector2D(0, 0))));

        e.add(c);

        Square c2 = new Square(100, 50);
        c2.setColor(Color.BLUE);
        c2.setInitialPosition(new Vector2D(250, 500));

        e.add(c2);

        Square c3 = new Square(100, 50);
        c3.setColor(Color.BLUE);
        c3.setInitialPosition(new Vector2D(500, 500));

        e.add(c3);



        e.simulate(1000, 1000, 0.05);
    }
}
