package com.cmaher.sgas.entities;

import com.cmaher.sgas.SGASGame;

/**
 * Anything that goes in the Game world, including players, enemies, scripts,
 * triggers, etc.
 * 
 * @author Christian
 * 
 */
public abstract class Entity {
    public final SGASGame game;

    public Entity(SGASGame game) {
        this.game = game;
    }

    /**
     * Updates the entity
     * 
     * @param delta
     *            - the amount of time elapsed since the last update
     */
    public abstract void update(float delta);
}
