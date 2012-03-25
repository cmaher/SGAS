package com.cmaher.sgas.components;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.cmaher.game.components.Component;
import com.cmaher.game.components.PhysicsComponent;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.entity.Entity;
import com.cmaher.sgas.entity.StraightBullet;

public class PlayerFireInputComponent extends Component {
    private final static int     BUTTON_FIRE_PRIMARY      = Input.Buttons.LEFT;
    private final static float   DEFAULT_BULLET_WAIT_TIME = .2f;
    private final static float   OVERLAY_DISTANCE         = 24;
    private final static float   OVERLAY_RADIUS           = 8;
    private final static float   BULLET_SPEED             = 512;

    private PlaceComponent       place;
    private float                cumDelta                 = 0;
    private List<StraightBullet> bullets;
    private float                bulletWaitTime;

    // use pool of bullets
    public PlayerFireInputComponent(Entity master, PlaceComponent place) {
        super(master);
        this.place = place;
        bullets = new LinkedList<StraightBullet>();
        bulletWaitTime = DEFAULT_BULLET_WAIT_TIME;
    }

    public void update(float delta) {
        // fire a bullet
        if (cumDelta >= bulletWaitTime) {
            if (Gdx.input.isButtonPressed(BUTTON_FIRE_PRIMARY)) {

                float a = place.getAngle() * MathUtils.degreesToRadians;
                float xOff = OVERLAY_DISTANCE * MathUtils.cos(a)
                        - OVERLAY_RADIUS;
                float yOff = OVERLAY_DISTANCE * MathUtils.sin(a)
                        - OVERLAY_RADIUS;

                Vector2 bulletStart = place.getCenter().add(xOff, yOff);

                Vector2 bulletDirection = PhysicsComponent.VECTOR_RIGHT.cpy()
                        .rotate(place.getAngle());
                StraightBullet bullet = new StraightBullet(master.game);
                bullets.add(bullet);
                bullet.shoot(bulletStart, bulletDirection, BULLET_SPEED);

                cumDelta = 0;
            }
        }

        // update bullets
        Iterator<StraightBullet> bulletIter = bullets.iterator();
        while (bulletIter.hasNext()) {
            StraightBullet sb = bulletIter.next();
            sb.update(delta);
            if (!sb.isAlive()) {
                bulletIter.remove();
            }
        }

        cumDelta += delta;
        // handle extremely rare overflow case
        if (cumDelta < 0) {
            cumDelta = bulletWaitTime;
        }
    }

    public float getBulletWaitTime() {
        return bulletWaitTime;
    }

    public void setBulletWaitTime(float bulletWaitTime) {
        this.bulletWaitTime = bulletWaitTime;
    }
}