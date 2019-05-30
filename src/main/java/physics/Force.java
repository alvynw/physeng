package physics;

import java.util.function.Function;

/**
 * Represents a force that follows an {@link core.Entity}. This force always acts on the {@link core.Entity}'s center of mass.
 *
 * @see CenterOfMass
 */
public class Force {
    private Function<Double, Vector2D> function;

    /**
     * Creates a <code>Force</code> with the specified {@link Function} of time
     * @param function the {@link Function} of time
     */
    public Force(Function<Double, Vector2D> function){
        this.function = function;
    }

    /**
     * Returns the value of the <code>Force</code> at a specified time
     * @param time the time
     * @return the value of the <code>Force</code> at the specified time
     */
    public Vector2D apply(Double time) {
        return function.apply(time);
    }
}
