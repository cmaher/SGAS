package com.cmaher.sgas.entity;

import com.cmaher.game.FactionType;
import com.cmaher.game.GameBase;
import com.cmaher.game.components.FactionableCollisionComponent;
import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.game.entity.Bullet;
import com.cmaher.game.entity.Factionable;

public class EnemyBullet extends Bullet implements Factionable {

    private RadialCollisionComponent collision;

    public EnemyBullet(GameBase game) {
        super(game);
        this.collision = new FactionableCollisionComponent(this, place,
                FactionType.EnemyBullet, FactionType.Player,
                FactionType.PlayerBullet);
    }

    public void update(float delta) {
        super.update(delta);
        collision.update(delta);
    }

    @Override
    public void collideUnfriendly(Factionable uf) {
        this.kill();
        uf.collideUnfriendlyBullet(this);
    }

    @Override
    public void collideUnfriendlyBullet(Factionable ufBullet) {
        this.kill();
        ufBullet.collideUnfriendlyBullet(this);
    }

    @Override
    public void collideSolid(RadialCollisionComponent rcc) {
        
    }

    @Override
    public void kill() {
        super.kill();
        game.getCollisionManager().remove(collision);
    }
    
}
