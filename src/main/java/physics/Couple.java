package physics;

import java.util.function.Function;

/**
 * Represents a couple that follows an {@link core.Entity}. This couple always acts about the {@link core.Entity}'s center of mass.
 * Positive is counterclockwise. The unit is in Newton-meters
 */
public class Couple {
    private Function<Double, Double> function;

    /**
     * Creates a <code>FollowingFoce</code> with the specified function of time
     * @param function the function of time
     */
    public Couple(Function<Double, Double> function){
        this.function = function;
    }

    /**
     * Returns the value of the couple at a specified time
     * @param time the time
     * @return the value of the couple at the specified time
     */
    public Double apply(Double time) {
        return function.apply(time);
    }
}
