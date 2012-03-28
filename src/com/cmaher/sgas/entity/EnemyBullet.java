package com.cmaher.sgas.entity;

import com.badlogic.gdx.graphics.Color;
import com.cmaher.game.FactionType;
import com.cmaher.game.GameBase;
import com.cmaher.game.components.FactionableCollisionComponent;
import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.game.entity.Bullet;
import com.cmaher.game.entity.Factionable;

public class EnemyBullet extends Bullet implements Factionable {
    public EnemyBullet(GameBase game) {
        super(game);
        bulletDraw.setTint(Color.BLUE);
    }
    
    @Override
    protected void createCollision() {
        this.collision = new FactionableCollisionComponent(this, place,
                FactionType.EnemyBullet, FactionType.Player,
                FactionType.PlayerBullet);
    }

    @Override
    public void collideUnfriendly(Factionable uf) {
        if(isAlive()) {
            this.kill();
        }
    }

    @Override
    public void collideUnfriendlyBullet(Factionable ufBullet) {
        if(isAlive()) {
            this.kill();
        }
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
