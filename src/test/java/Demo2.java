import core.Environment;
import physics.Vector2D;
import shapes.Square;

public class Demo2 {
    public static void main(String[] args) {
        Environment e = new Environment();

        Square s1 = new Square(10, 50);
        s1.setInitialPosition(new Vector2D(10, 500));
        s1.setInitialVelocity(new Vector2D(100, 0));

        Square s2 = new Square(10, 50);
        s2.setInitialPosition(new Vector2D(990, 500));
        s2.setInitialVelocity(new Vector2D(-100, 0));

        Square s3 = new Square(10, 50);
        s3.setInitialPosition(new Vector2D(500, 990));
        s3.setInitialVelocity(new Vector2D(0, -100));


        e.add(s1);
        e.add(s2);
        e.add(s3);

        e.simulate(1000, 1000, 0.05);
        
    }
}
