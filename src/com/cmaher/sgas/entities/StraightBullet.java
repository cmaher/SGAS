package com.cmaher.sgas.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.cmaher.sgas.SGASGame;
import com.cmaher.sgas.components.DrawComponent;
import com.cmaher.sgas.components.PhysicsComponent;
import com.cmaher.sgas.components.PlaceComponent;

public class StraightBullet extends Entity {
    private static final String BULLET         = SGASGame.ASSETS + "bullet.png";
    private static final String BULLET_OVERLAY = SGASGame.ASSETS
                                                       + "bullet_overlay.png";

    private PlaceComponent      place;
    private PhysicsComponent    phys;
    private DrawComponent       bulletDraw;
    private DrawComponent       overlayDraw;

    private boolean             alive          = false;

    public StraightBullet(SGASGame game) {
        super(game);
        place = new PlaceComponent(this);
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
        }
    }

    public void kill() {
        this.alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
}