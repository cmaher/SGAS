package com.cmaher.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.cmaher.game.GameBase;
import com.cmaher.game.components.DrawComponent;
import com.cmaher.game.components.PhysicsComponent;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.game.properties.GameProperties;

/**
 * TODO: In Order to make this truly generic, import assets from config
 * 
 * @author Christian
 * 
 */
public class Bullet extends EntityBase {
    protected PlaceComponent           place;
    protected PhysicsComponent         phys;
    protected DrawComponent            bulletDraw;
    protected DrawComponent            overlayDraw;

    protected boolean                  alive;
    protected RadialCollisionComponent collision;

    protected String                   propertyBase;
    protected static final String      P_SPRITE       = "sprite";
    protected static final String      P_OVERLAY      = "overlay";
    protected static final String      P_WIDTH        = "width";
    protected static final String      P_HEIGHT       = "height";

    protected String                   pSprite;
    protected String                   pOverlay;
    protected float                    pWidth;
    protected float                    pHeight;

    public Bullet(GameBase game, String propertyRoot) {
        super(game);
        getProperties(propertyRoot);
        place = new PlaceComponent(this, 0, 0, pWidth, pHeight);
        phys = new PhysicsComponent(this, place);
        bulletDraw = new DrawComponent(this, place, pSprite);

        overlayDraw = new DrawComponent(this, place, pOverlay);
        this.alive = false;
    }

    private void getProperties(String propertyRoot) {
        GameProperties props = game.getGameProperties();
        pSprite = props.getString(propertyRoot, P_SPRITE, "");
        pOverlay = props.getString(propertyRoot, P_OVERLAY, "");
        pWidth = props.getFloat(propertyRoot, P_WIDTH, 1);
        pHeight = props.getFloat(propertyRoot, P_HEIGHT, 1);
    }

    public void shoot(Vector2 origin, Vector2 direction, float speed) {
        place.setPosition(origin.x, origin.y);
        phys.setVelocity(direction, speed);
        phys.setMaxSpeed(speed);
        phys.setMinSpeed(speed);
        alive = true;
        this.createCollision();
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