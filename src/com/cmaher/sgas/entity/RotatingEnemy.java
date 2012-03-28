package com.cmaher.sgas.entity;

import com.badlogic.gdx.graphics.Color;
import com.cmaher.game.FactionType;
import com.cmaher.game.GameBase;
import com.cmaher.game.components.DeactivateComponent;
import com.cmaher.game.components.DrawComponent;
import com.cmaher.game.components.FactionableCollisionComponent;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.game.components.RotationalComponent;
import com.cmaher.game.components.ShootBulletComponent;
import com.cmaher.game.entity.EntityBase;
import com.cmaher.game.entity.Factionable;
import com.cmaher.sgas.SGASGame;

public class RotatingEnemy extends EntityBase implements Factionable {
    private static final String           SPRITE             = SGASGame.ASSETS
                                                                     + "player.png";
    private static final float            RADIUS             = 32f;
    private static final Color            COLOR              = new Color(255,
                                                                     192, 203,
                                                                     255);
    private static final float            ANGULAR_V          = 30f;

    private static final float            DEACTIVE_TIME      = .6f;

    private static final float            DEFAULT_SHOOT_WAIT = 1.2f;
    private static final float            SHOOT_RATE_CHANGE  = -.05f;
    private static final float            ANGLE_RATE_CHANGE  = 1f;

    private PlaceComponent                place;
    private RotationalComponent           rotational;
    private FactionableCollisionComponent collision;
    private DrawComponent                 draw;
    private DeactivateComponent           deactivate;
    private ShootBulletComponent          shoot;

    public RotatingEnemy(GameBase game) {
        super(game);
        init(0f, 0f, 0f);
    }

    public RotatingEnemy(GameBase game, float x, float y, float angle) {
        super(game);
        init(x, y, angle);
    }

    private void init(float x, float y, float angle) {
        place = new PlaceComponent(this, x, y, RADIUS * 2, RADIUS * 2);
        place.setAngle(angle);
        rotational = new RotationalComponent(this, place, ANGULAR_V);
        collision = new FactionableCollisionComponent(this, place,
                FactionType.Enemy, FactionType.Player, FactionType.PlayerBullet);

        deactivate = new DeactivateComponent(this, DEACTIVE_TIME);
        shoot = new ShootBulletComponent(this, place);
        shoot.setBulletWaitTime(DEFAULT_SHOOT_WAIT);

        draw = new DrawComponent(this, place, SPRITE);
        draw.setTint(COLOR);
        game.getAssetWrapper().addTextureAsset(SPRITE);
    }

    public void update(float delta) {
        deactivate.update(delta);
        if (!deactivate.isDeactivated()) {
            rotational.update(delta);
            shoot.fireNewBullet(new EnemyBullet(game));
        }
        shoot.update(delta);
        collision.update(delta);
        draw.draw();
    }

    @Override
    public void collideUnfriendly(Factionable uf) {
        uf.collideSolid(collision);
    }

    @Override
    public void collideUnfriendlyBullet(Factionable ufBullet) {
        deactivate.resetDeactivation();
        rotational.setAngularVelocity(rotational.getAngularVelocity() + ANGLE_RATE_CHANGE);
        shoot.setBulletWaitTime(shoot.getBulletWaitTime() + SHOOT_RATE_CHANGE);
    }

    @Override
    public void collideSolid(RadialCollisionComponent rcc) {

    }

}
