package physics;

import java.util.function.Function;

public class FollowingForce {
    Function<Double, Vector2D> function;

    public FollowingForce(Function<Double, Vector2D> function){
        this.function = function;
    }

    public Vector2D apply(Double time) {
        return function.apply(time);
    }

}
