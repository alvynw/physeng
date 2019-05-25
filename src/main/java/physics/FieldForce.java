package physics;

import core.*;

import java.util.function.Function;

public class FieldForce<Input>{
    Function<Input, Function<Vector2D, Vector2D>> function;
    Vector2D position;
    //static Entity followingEntity;
    public FieldForce(Function<Input, Function<Vector2D, Vector2D>> function){
        this.function = function;
        //this.position = position;
    }

    public Function<Vector2D, Vector2D> queryFunction(Input input) {
        return function.apply(input);
    }

    public Vector2D query(Input input, Vector2D location) {
        return function.apply(input).apply(location);
    }

    public static void main(String[] args) {
        FieldForce<Double> gravity = new FieldForce<Double>((Double d) -> ((Vector2D position) -> new Vector2D(0, -9.8 * d)));
    }
}