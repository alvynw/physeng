package physics;

import java.util.function.Function;

public class FollowingForce<Input> {
    Function<Input, Vector2D> function;

    public FollowingForce(Function<Input, Vector2D> function){
        this.function = function;
    }

    public Vector2D query(Input input) {
        return function.apply(input);
    }

}
