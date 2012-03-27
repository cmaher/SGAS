package com.cmaher.game.entity;

import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.sgas.SGASGame;

public abstract class Player extends Entity{

    public Player(SGASGame game) {
        super(game);
    }
    
    
    public abstract void collideEnemy(Enemy enemy);
    public abstract void hitSolid(RadialCollisionComponent rcc);
}
