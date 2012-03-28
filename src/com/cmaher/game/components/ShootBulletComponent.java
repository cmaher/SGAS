package com.cmaher.game.components;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.cmaher.game.entity.Bullet;
import com.cmaher.game.entity.EntityBase;

public class ShootBulletComponent extends Component {

    private final static float DEFAULT_BULLET_WAIT_TIME = .2f;
    private final static float OVERLAY_DISTANCE         = 24;
    private final static float OVERLAY_RADIUS           = 8;
    private final static float BULLET_SPEED             = 512;

    private PlaceComponent     place;
    private float              sumDelta                 = 0;
    private List<Bullet>       bullets;
    private float              bulletWaitTime;
    private float              bulletSpeed;

    public ShootBulletComponent(EntityBase master, PlaceComponent place) {
        super(master);
        this.place = place;
        bullets = new LinkedList<Bullet>();
        bulletWaitTime = DEFAULT_BULLET_WAIT_TIME;
        this.bulletSpeed = BULLET_SPEED;

    }

    public void update(float delta) {
        // update bullets
        Iterator<Bullet> bulletIter = bullets.iterator();
        while (bulletIter.hasNext()) {
            Bullet sb = bulletIter.next();
            sb.update(delta);
            if (!sb.isAlive()) {
                bulletIter.remove();
            }
        }

        sumDelta += delta;
        // handle extremely rare overflow case
        if (sumDelta < 0) {
            sumDelta = bulletWaitTime;
        }
    }

    public void fireNewBullet(Bullet newBullet) {
        if (sumDelta >= bulletWaitTime) {
            float a = place.getAngle() * MathUtils.degreesToRadians;
            float xOff = OVERLAY_DISTANCE * MathUtils.cos(a) - OVERLAY_RADIUS;
            float yOff = OVERLAY_DISTANCE * MathUtils.sin(a) - OVERLAY_RADIUS;

            Vector2 bulletStart = place.getCenter().add(xOff, yOff);

            Vector2 bulletDirection = PhysicsComponent.VECTOR_RIGHT.cpy()
                    .rotate(place.getAngle());
            bullets.add(newBullet);
            newBullet.shoot(bulletStart, bulletDirection, bulletSpeed);

            sumDelta = 0;
        }
    }

    public float getBulletWaitTime() {
        return bulletWaitTime;
    }

    public void setBulletWaitTime(float bulletWaitTime) {
        this.bulletWaitTime = bulletWaitTime;
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public void resetWaitTime() {
    	this.sumDelta = 0;
    }
}
