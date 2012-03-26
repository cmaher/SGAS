package com.cmaher.sgas.entity;

import com.cmaher.game.entity.Entity;
import com.cmaher.sgas.SGASGame;

public abstract class Enemy extends Entity {
    public Enemy(SGASGame game) {
        super(game);
    }
    
    public abstract void collidePlayer(Player player);
    public abstract void collidePlayerBullet(StraightBullet bullet);
}
