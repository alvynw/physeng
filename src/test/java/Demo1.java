import core.Environment;
import physics.Force;
import physics.Vector2D;
import shapes.Square;

public class Demo1 {
    public static void main (String[] args) {

        Environment e = new Environment();

        Square s1 = new Square(10, 50);
        s1.setInitialPosition(new Vector2D(350, 500));
        s1.setInitialVelocity(new Vector2D(100,0));

        Square s2 = new Square(10, 50);
        s2.setInitialPosition(new Vector2D(650, 500));
        s2.setInitialVelocity(new Vector2D(-100,0));

        Square s3 = new Square(10, 50);
        s3.setInitialPosition(new Vector2D(100, 500));
        s3.addForce(new Force((t) -> new Vector2D(400, 0)));

        Square s4 = new Square(10, 50);
        s4.setInitialPosition(new Vector2D(900, 500));
        s4.addForce(new Force((t) -> new Vector2D(-400, 0)));

        e.add(s1);
        e.add(s2);
        e.add(s3);
        e.add(s4);


        e.simulate(1000, 1000, 0.05);
    }
}
