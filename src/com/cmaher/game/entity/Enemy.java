package com.cmaher.game.entity;

import com.cmaher.sgas.SGASGame;
import com.cmaher.sgas.entity.SGASPlayer;

public abstract class Enemy extends Entity {
    public Enemy(SGASGame game) {
        super(game);
    }
    
    public abstract void collidePlayer(SGASPlayer player);
    public abstract void collidePlayerBullet(Bullet bullet);
}
