package physics;

import java.util.function.Function;

/**
 * Represents a uniform field, that is, a field with the same force at every point
 */
public class UniformField {
    private Function<Double, Vector2D> function;

    /**
     * Creates a UniformField using the given {@link Function}
     * @param function the {@link Function} to use
     */
    public UniformField(Function<Double, Vector2D> function){
        this.function = function;
    }

    /**
     * Finds the value of the <code>UniformField</code> at the specified time
     * @param time the time
     * @returnthe value of the <code>UniformField</code> at the specified time
     */
    public Vector2D apply(Double time) {
        return function.apply(time);
    }

}