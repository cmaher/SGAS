package com.cmaher.game.components;

import com.cmaher.game.entity.EntityBase;

public class RotationalComponent extends Component {

    private PlaceComponent place;
    private float          angularVelocity; // degrees/second
    private boolean        reversed;

    public RotationalComponent(EntityBase master, PlaceComponent place,
            float angularVelocity) {
        super(master);
        this.place = place;
        this.angularVelocity = angularVelocity;
    }

    public void update(float delta) {
        float angle;
        
        if(!reversed) {
            angle = place.getAngle() + angularVelocity * delta;
        } else {
            angle = place.getAngle() - angularVelocity * delta;
        }
        place.setAngle(angle);
    }

    public float getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(float angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }
    
    public boolean isReversed() {
        return reversed;
    }
}
