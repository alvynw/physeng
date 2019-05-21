package physics;

import core.*;

public class FollowingForce extends Vector2D implements funcInterface{
    funcInterface fEquation;
    static Vector2D force;
    //static Entity followingEntity;
    public FollowingForce(funcInterface equation){
        fEquation = equation;
    }
    public FollowingForce(Vector2D force){
        this.force = force;
    }
       public void updateForce(double x, double v, double a, double t){
        fEquation.updateForce(x , v,t,a);
        }
    public static void main(String args[]){

        Entity obj = new Entity(10, null);
        obj.setInitialPosition(new Vector2D(10, 10));

        FollowingForce f = new FollowingForce((double t, double a, double x, double v)->{
            force = new Vector2D( t*a, 4 * t+v+x);
        });
        f.updateForce(obj.getPosition().getMag(),obj.getVelocity().getMag(),obj.getAcceleration().getMag(),15);
        System.out.println(force.getMag());


        // This calls above lambda expression and prints 10.
    }

}