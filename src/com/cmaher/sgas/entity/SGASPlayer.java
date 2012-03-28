package com.cmaher.sgas.entity;

import com.badlogic.gdx.graphics.Color;
import com.cmaher.game.FactionType;
import com.cmaher.game.components.DrawComponent;
import com.cmaher.game.components.FactionableCollisionComponent;
import com.cmaher.game.components.LifeComponent;
import com.cmaher.game.components.PhysicsComponent;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.game.entity.EntityBase;
import com.cmaher.game.entity.Factionable;
import com.cmaher.sgas.SGASGame;
import com.cmaher.sgas.components.PlayerMoveInputComponent;
import com.cmaher.sgas.components.PlayerShootInputComponent;

/**
 * The class controlling the player The Player can move, rotate, shoot, and get
 * hit
 * 
 * @author Christian
 * 
 */
public class SGASPlayer extends EntityBase implements Factionable {
    private static final String           SPRITE       = SGASGame.ASSETS
                                                               + "player.png";
    private static final float            RADIUS       = 32;
    private static final float            MIN_SPEED    = 0f;
    private static final float            MAX_SPEED    = 128f;
    private static final int              TOUCH_SCORE  = -1;
    private static final int              BULLET_SCORE = -20;
    private static final int              MIN_POINT    = -20;

    private PlaceComponent                place;
    private DrawComponent                 draw;
    private PhysicsComponent              phys;
    private PlayerMoveInputComponent      moveInput;
    private PlayerShootInputComponent     fireInput;
    private FactionableCollisionComponent collision;
    private LifeComponent                 life;
    private SGASGame                      sgasg;

    public SGASPlayer(SGASGame game) {
        super(game);
        this.sgasg = game;
        init(0, 0);
    }

    public SGASPlayer(SGASGame game, float x, float y) {
        super(game);
        this.sgasg = game;
        init(x, y);
    }

    private void init(float x, float y) {
        place = new PlaceComponent(this, x, y, RADIUS * 2, RADIUS * 2);

        draw = new DrawComponent(this, place, SPRITE);
        draw.setTint(Color.GREEN);

        phys = new PhysicsComponent(this, place, MIN_SPEED, MAX_SPEED);

        moveInput = new PlayerMoveInputComponent(this, place, phys);
        fireInput = new PlayerShootInputComponent(this, place);
        collision = new FactionableCollisionComponent(this, place,
                FactionType.Player, FactionType.Enemy, FactionType.EnemyBullet);

        life = new LifeComponent(this);

        game.getAssetWrapper().addTextureAsset(SPRITE);
    }

    @Override
    public void update(float delta) {
        moveInput.update(delta);
        fireInput.update(delta);
        phys.update(delta);
        place.moveToScreenBounds();

        draw.setTint(Color.GREEN);

        collision.update(delta);

        if (sgasg.getScore() <= MIN_POINT) {
            life.setAlive(false);
        }

        draw.draw();
    }

    public void collideUnfriendly(Factionable uf) {
        sgasg.addScore(TOUCH_SCORE);
        draw.setTint(Color.RED);
    }

    @Override
    public void collideUnfriendlyBullet(Factionable ufBullet) {
        sgasg.addScore(BULLET_SCORE);
    }

    public void collideSolid(RadialCollisionComponent rcc) {
        collision.stopAtSolid(rcc);
    }

    public boolean isAlive() {
        return true;
        //return life.isAlive();
    }
}
