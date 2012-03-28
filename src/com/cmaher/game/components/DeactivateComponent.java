package com.cmaher.game.components;

import com.cmaher.game.entity.Entity;

public class DeactivateComponent extends Component {

    private boolean deactivated = false;
    private float   deactiveTime;
    private float   sumTime;

    public DeactivateComponent(Entity master, float deactiveTime) {
        super(master);
        this.deactiveTime = deactiveTime;
        this.sumTime = 0;
    }

    public void update(float delta) {
        if(deactivated) {
            sumTime += delta;
        }
        
        if(sumTime >= deactiveTime) {
            deactivated = false;
            sumTime = 0;
        }
    }

    public boolean isDeactivated() {
        return deactivated;
    }

    public void deactivate() {
        this.deactivated = true;
    }
    
    public void resetDeactivation() {
        this.deactivated = true;
        sumTime = 0;
    }
}
