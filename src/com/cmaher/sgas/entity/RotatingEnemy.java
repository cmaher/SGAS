package com.cmaher.sgas.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.cmaher.game.FactionType;
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
    public static final float             RADIUS               = 32f;
    private static final String           SPRITE               = SGASGame.ASSETS
                                                                       + "player.png";
    private static final Color            DEACTIVE_COLOR       = new Color(255,
                                                                       192,
                                                                       203, 255);
    private static final float            ANGULAR_V            = 30f;

    private static final float            DEACTIVE_TIME        = 1.5f;

    private static final float            DEFAULT_SHOOT_WAIT   = 0f;
    private static final float            DEFAULT_BULLET_SPEED = 256;
    private static final float            SHOOT_RATE_CHANGE    = -.2f;
    private static final float            ANGLE_RATE_CHANGE    = 8f;
    private static final int              SCORE_UPDATE         = 10;

    private PlaceComponent                place;
    private RotationalComponent           rotational;
    private FactionableCollisionComponent collision;
    private DrawComponent                 draw;
    private DeactivateComponent           deactivate;
    private ShootBulletComponent          shoot;
    private SGASGame                      sgasg;

    public RotatingEnemy(SGASGame game) {
        super(game);
        this.sgasg = game;
        init(0f, 0f, 0f);
    }

    public RotatingEnemy(SGASGame game, float x, float y) {
        super(game);
        this.sgasg = game;
        init(x, y, 0f);
    }

    public RotatingEnemy(SGASGame game, float x, float y, float angle) {
        super(game);
        this.sgasg = game;
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
        shoot.setBulletSpeed(DEFAULT_BULLET_SPEED);

        draw = new DrawComponent(this, place, SPRITE);
        draw.setTint(DEACTIVE_COLOR);
        game.getAssetWrapper().addTextureAsset(SPRITE);
    }

    public void update(float delta) {
        boolean prevDeactivated = deactivate.isDeactivated();

        deactivate.update(delta);
        if (prevDeactivated && !deactivate.isDeactivated()) {
            shoot.resetWaitTime();
        }

        if (!deactivate.isDeactivated()) {
            rotational.update(delta);
            shoot.fireNewBullet(new EnemyBullet(game));

            draw.setTint(Color.BLUE);
        } else {
            draw.setTint(DEACTIVE_COLOR);
        }

        shoot.update(delta);
        collision.update(delta);
        draw.draw();
    }

    @Override
    public void collideUnfriendly(Factionable uf) {
        uf.collideSolid(collision);
    }

    /**
     * deactivate, increase speed of angle and bullet fire rate only update
     * score if not already deactivated
     */
    @Override
    public void collideUnfriendlyBullet(Factionable ufBullet) {
        if (!deactivate.isDeactivated()) {
            sgasg.addScore(SCORE_UPDATE);
        }

        deactivate.resetDeactivation();
        rotational.setAngularVelocity(rotational.getAngularVelocity()
                + ANGLE_RATE_CHANGE);
        shoot.setBulletWaitTime(shoot.getBulletWaitTime() + SHOOT_RATE_CHANGE);
    }

    @Override
    public void collideSolid(RadialCollisionComponent rcc) {

    }

    public void randomize(float lowShoot, float highShoot) {
        shoot.setBulletWaitTime(MathUtils.random(lowShoot, highShoot));
        place.setAngle(MathUtils.random(0f, 360f));
        rotational.setReversed(MathUtils.randomBoolean());
    }
}
