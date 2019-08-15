import core.Environment;
import physics.Force;
import physics.Vector2D;
import shapes.Square;

public class NewtonsCradle {
    public static void main (String[] args) {
        Environment e = new Environment();

        Square s1 = new Square(10, 50);
        s1.setInitialPosition(new Vector2D(300, 500));
        //s1.setInitialVelocity(new Vector2D(100, 0));
        s1.addForce(new Force((t) -> {
            if (s1.getPosition().getX() < 400) return new Vector2D(50 * s1.getMass(), 0);
            else return new Vector2D(0, 0);
        }));


        Square s2 = new Square(10, 50);
        s2.setInitialPosition(new Vector2D(450, 500));

        Square s3 = new Square(10, 50);
        s3.setInitialPosition(new Vector2D(500, 500));

        Square s4 = new Square(10, 50);
        s4.setInitialPosition(new Vector2D(550, 500));

        Square s5 = new Square(10, 50);
        s5.setInitialPosition(new Vector2D(600, 500));

        s5.addForce(new Force((t) -> {
            if (s5.getPosition().getX() > 600) return new Vector2D(-50 * s5.getMass(), 0);
            else return new Vector2D(0, 0);
        }));

        e.add(s1);
        e.add(s2);
        e.add(s3);
        e.add(s4);
        e.add(s5);

        e.simulate(1000, 1000, 0.05);

    }
}
