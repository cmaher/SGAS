package com.cmaher.game.components;

import com.cmaher.game.entity.Entity;

public class Component {
    public final Entity master;

    public Component(Entity master) {
        this.master = master;
    }
}
