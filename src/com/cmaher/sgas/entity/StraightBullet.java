package com.cmaher.sgas.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.cmaher.game.components.DrawComponent;
import com.cmaher.game.components.PhysicsComponent;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.entity.Entity;
import com.cmaher.sgas.SGASGame;

/**
 * TODO: In Order to make this truly generic, import assets from config
 * @author Christian
 *
 */
public class StraightBullet extends Entity {
    private static final String BULLET         = SGASGame.ASSETS + "bullet.png";
    private static final String BULLET_OVERLAY = SGASGame.ASSETS
                                                       + "bullet_overlay.png";
    private static final int    DIAMETER       = 16;

    private PlaceComponent      place;
    private PhysicsComponent    phys;
    private DrawComponent       bulletDraw;
    private DrawComponent       overlayDraw;

    private boolean             alive          = false;

    public StraightBullet(SGASGame game) {
        super(game);
        place = new PlaceComponent(this);
        place.setWidth(DIAMETER);
        place.setHeight(DIAMETER);
        phys = new PhysicsComponent(this, place);
        bulletDraw = new DrawComponent(this, place, BULLET);
        bulletDraw.setTint(Color.RED);
        overlayDraw = new DrawComponent(this, place, BULLET_OVERLAY);
    }

    public void shoot(Vector2 origin, Vector2 direction, float speed) {
        place.setPosition(origin.x, origin.y);
        phys.setVelocity(direction, speed);
        phys.setMaxSpeed(speed);
        phys.setMinSpeed(speed);

        alive = true;
    }

    public void update(float delta) {
        if (alive) {
            phys.update(delta);
            bulletDraw.draw();
            overlayDraw.draw();
            if(!place.inBounds(0, 0, SGASGame.WIDTH, SGASGame.HEIGHT)) {
                this.kill();
            }
        }
    }

    public void kill() {
        this.alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
}