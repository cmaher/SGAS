package com.cmaher.sgas.entity;

import com.cmaher.game.FactionType;
import com.cmaher.game.GameBase;
import com.cmaher.game.components.FactionableCollisionComponent;
import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.game.entity.Bullet;
import com.cmaher.game.entity.Factionable;

public class PlayerBullet extends Bullet implements Factionable {

    private RadialCollisionComponent collision;

    public PlayerBullet(GameBase game) {
        super(game);
        this.collision = new FactionableCollisionComponent(this, place,
                FactionType.PlayerBullet, FactionType.Enemy,
                FactionType.EnemyBullet);
    }

    public void update(float delta) {
        super.update(delta);
        collision.update(delta);
    }

    @Override
    public void collideUnfriendly(Factionable uf) {
        uf.collideUnfriendlyBullet(this);
        this.kill();
    }

    @Override
    public void collideUnfriendlyBullet(Factionable ufBullet) {
        ufBullet.collideUnfriendlyBullet(this);
        this.kill();
    }

    @Override
    public void collideSolid(RadialCollisionComponent rcc) {
        this.kill();
    }
}
