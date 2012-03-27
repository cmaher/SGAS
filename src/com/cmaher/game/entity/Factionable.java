package com.cmaher.game.entity;

import com.cmaher.game.components.RadialCollisionComponent;

public interface Factionable extends Entity {    
    public abstract void collideUnfriendly(Factionable uf);
    public abstract void collideUnfriendlyBullet(Factionable ufBullet);
    public abstract void collideSolid(RadialCollisionComponent rcc);
}
