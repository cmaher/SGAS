package com.cmaher.game.entity;

import com.cmaher.game.GameBase;

/**
 * Anything that goes in the Game world, including players, enemies, scripts,
 * triggers, etc.
 * 
 * @author Christian
 * 
 */
public abstract class EntityBase implements Entity {
    public final GameBase game;

    public EntityBase(GameBase game) {
        this.game = game;
    }

    /**
     * Updates the entity
     * 
     * @param delta
     *            - the amount of time elapsed since the last update
     */
    public abstract void update(float delta);
    
    public GameBase getGame() {
        return game;
    }
}
