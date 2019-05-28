package physics;

import java.util.function.Function;

public class FieldForce {
    Function<Double, Function<Vector2D, Vector2D>> function;

    public FieldForce(Function<Double, Function<Vector2D, Vector2D>> function){
        this.function = function;
    }

    public Function<Vector2D, Vector2D> queryFunction(Double input) {
        return function.apply(input);
    }

    public Vector2D query(Double input, Vector2D location) {
        return function.apply(input).apply(location);
    }
}