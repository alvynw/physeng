package physics;

import core.*;

punoblic class Force extends Vector2D {
    //Vector2D location;

    public Force( double x, double y) {
        super(x, y);
        //location = loc;
    }

    public void actOn(Entity entity) {
        entity.setAcceleration(new Vector2D(entity.getAcceleration().getX() + getX() / entity.getMass(), entity.getAcceleration().getX() + getY() / entity.getMass()));

    }
}
