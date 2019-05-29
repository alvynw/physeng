package physics;

import java.util.function.Function;

/**
 * Represents a uniform field
 */
public class UniformField{
    private Function<Double, Vector2D> function;

    public UniformField(Function<Double, Vector2D> function){
        this.function = function;
    }

    public Vector2D apply(Double time) {
        return function.apply(time);
    }

}