package physics;

import core.Entity;

import java.util.function.BiFunction;

/**
 * Represents a uniform field, that is, a field with the same force for all points on an Entity
 *
 * <br> <br>
 * Developer's Note:
 * <br><br>
 * Since {@link Entity} contains information regarding the center of mass of it,
 * the behavior of the {@link Entity} will as if all of its mass in concentrated on
 * its center of mass.
 */
public class UniformField {
    private BiFunction<Double, Entity, Vector2D> function;

    /**
     * Creates a UniformField using the given {@link BiFunction}
     * @param function the {@link BiFunction} to use
     */
    public UniformField(BiFunction<Double, Entity, Vector2D> function){
        this.function = function;
    }

    /**
     * Finds the value of the <code>UniformField</code> at the specified time
     * @param time the time
     * @returnthe value of the <code>UniformField</code> at the specified time
     */
    public Vector2D apply(Double time, Entity e) {
        return function.apply(time, e);
    }

}