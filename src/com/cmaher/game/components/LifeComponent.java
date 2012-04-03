package com.cmaher.game.components;

import com.cmaher.game.entity.Entity;

public class LifeComponent extends Component {

    private boolean alive;
    
    public LifeComponent(Entity master) {
        super(master);
        alive = true;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
