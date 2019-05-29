package physics;

import java.util.function.Function;

public class FieldForce{
    Function<Double, Function<Vector2D, Vector2D>> function;

    public FieldForce(Function<Double, Function<Vector2D, Vector2D>> function){
        this.function = function;
    }

    public Function<Vector2D, Vector2D> function(Double time) {
        return function.apply(time);
    }

    public Vector2D apply(Double time, Vector2D location) {
        return function.apply(time).apply(location);
    }

}