package physics;

import core.*;

import java.util.ArrayList;
import java.util.function.Function;

public class FollowingForce<Input> {
    Function<Input, Vector2D> function;
    //static Entity followingEntity;
    public FollowingForce(Function<Input, Vector2D> function){
        this.function = function;
    }

    public Vector2D query(Input input) {
        return function.apply(input);
    }

    public static void main(String args[]){

        Entity obj = new Entity(10, null);
        obj.setInitialPosition(new Vector2D(10, 10));

        ArrayList<Double> list = new ArrayList<>();
        list.add(1.0);
        list.add(2.0);

        FollowingForce<ArrayList<Double>> f = new FollowingForce<ArrayList<Double>>((ArrayList<Double> list2) -> new Vector2D(list2.get(0), list2.get(1)));

        // This calls above lambda expression and prints 10.
    }

}
