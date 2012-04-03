package com.cmaher.sgas.entity;

import com.badlogic.gdx.graphics.Color;
import com.cmaher.game.FactionType;
import com.cmaher.game.GameBase;
import com.cmaher.game.components.FactionableCollisionComponent;
import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.game.entity.Bullet;
import com.cmaher.game.entity.Factionable;
import com.cmaher.game.properties.GameProperties;

public class PlayerBullet extends Bullet implements Factionable {
    private static final String P_ROOT = "bullet"; 
    public PlayerBullet(GameBase game, String pRoot) {
        super(game, pRoot + GameProperties.SEP + P_ROOT);
        bulletDraw.setTint(Color.GREEN);
    }
    
    @Override
    protected void createCollision() {
        this.collision = new FactionableCollisionComponent(this, place,
                FactionType.PlayerBullet, FactionType.Enemy,
                FactionType.EnemyBullet);
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
        if(isAlive()) {
            this.kill();
        }
    }

    @Override
    public void kill() {
        super.kill();
        game.getCollisionManager().remove(collision);
    }
}
