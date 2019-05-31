import core.Environment;
import physics.Vector2D;
import shapes.Circle;

import java.awt.*;

public class EngineTest {
    public static void main (String[] args) {

        Environment e = new Environment();

        Circle c = new Circle(10, 10);
        c.setColor(Color.RED);
        c.setInitialPosition(new Vector2D(10, 10));
        e.add(c);
        e.simulate();
    }
}
