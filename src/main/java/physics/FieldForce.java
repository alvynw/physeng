package physics;

import core.*;

import java.util.Vector;

public class FieldForce extends Vector2D implements funcInterface{
    funcInterface fEquation;
    static Vector2D fieldForce;
    Vector2D position;
    //static Entity followingEntity;
    public FieldForce(funcInterface equation, Vector2D position){
        this.position = position;
        fEquation = equation;
    }
    public FieldForce(Vector2D fieldForce, Vector2D position){
        this.position = position;
        this.fieldForce = fieldForce;
    }
    public void updateForce(double x, double v, double a, double t){
        fEquation.updateForce(x , v,t,a);
    }
    public static void main(String args[]){

        Entity obj = new Entity(10, null);
        obj.setInitialPosition(new Vector2D(10, 10));

        FieldForce f = new FieldForce((double t, double a, double x, double v)->{
            fieldForce = new Vector2D( t*a, 4 * t+v+x);
        }, new Vector2D(10, 12));
        f.updateForce(obj.getPosition().getMag(),obj.getVelocity().getMag(),obj.getAcceleration().getMag(),15);
        System.out.println(fieldForce.getMag());
        // This calls above lambda expression and prints 10.
    }
    public void actOn(Entity entity){
        entity.setInitialAcceleration
                (new Vector2D(entity.getAcceleration().getX()+fieldForce.getX(),entity.getAcceleration().getY()+fieldForce.getY())
        );
    }

}