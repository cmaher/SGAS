package com.cmaher.sgas.components;

import com.cmaher.sgas.entities.Entity;

public class Component {
    public final Entity master;

    public Component(Entity master) {
        this.master = master;
    }
}
