package com.cmaher.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.cmaher.game.GameBase;
import com.cmaher.game.components.DrawComponent;
import com.cmaher.game.components.PhysicsComponent;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.sgas.SGASGame;

/**
 * TODO: In Order to make this truly generic, import assets from config
 * 
 * @author Christian
 * 
 */
public class Bullet extends EntityBase {
    protected static final String    BULLET         = SGASGame.ASSETS
                                                            + "bullet.png";
    protected static final String    BULLET_OVERLAY = SGASGame.ASSETS
                                                            + "bullet_overlay.png";
    protected static final int       DIAMETER       = 16;

    protected PlaceComponent         place;
    protected PhysicsComponent       phys;
    protected DrawComponent          bulletDraw;
    protected DrawComponent          overlayDraw;

    protected boolean                  alive;
    protected RadialCollisionComponent collision;

    public Bullet(GameBase game) {
        super(game);
        place = new PlaceComponent(this);
        place.setWidth(DIAMETER);
        place.setHeight(DIAMETER);
        phys = new PhysicsComponent(this, place);
        bulletDraw = new DrawComponent(this, place, BULLET);
        bulletDraw.setTint(Color.RED);
        overlayDraw = new DrawComponent(this, place, BULLET_OVERLAY);
        this.alive = false;
    }

    public void shoot(Vector2 origin, Vector2 direction, float speed) {
        place.setPosition(origin.x, origin.y);
        phys.setVelocity(direction, speed);
        phys.setMaxSpeed(speed);
        phys.setMinSpeed(speed);

        alive = true;
        createCollision();
    }
    
    protected void createCollision() {
        this.collision = new RadialCollisionComponent(this, place);
    }

    public void update(float delta) {
        if (alive) {
            phys.update(delta);
            collision.update(delta);
            bulletDraw.draw();
            overlayDraw.draw();
            if (!place.inBounds(0, 0, Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight())) {
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