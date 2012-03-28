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
    private static final int              BULLET_SCORE = -20;
    private static final int              MIN_POINT    = -20;
    private static final float            HIT_DELTA    = .3f;

    private PlaceComponent                place;
    private DrawComponent                 draw;
    private PhysicsComponent              phys;
    private PlayerMoveInputComponent      moveInput;
    private PlayerShootInputComponent     fireInput;
    private FactionableCollisionComponent collision;
    private LifeComponent                 life;
    private SGASGame                      sgasg;

    private String                        pRoot;
    private float                         sumDelta     = 0;
    private boolean                       hit          = false;

    public SGASPlayer(SGASGame game, String pRoot) {
        super(game);
        this.sgasg = game;
        this.pRoot = pRoot;
        init(0, 0);
    }

    public SGASPlayer(SGASGame game, String pRoot, float x, float y) {
        super(game);
        this.sgasg = game;
        this.pRoot = pRoot;
        init(x, y);
    }

    private void init(float x, float y) {
        place = new PlaceComponent(this, x, y, RADIUS * 2, RADIUS * 2);

        draw = new DrawComponent(this, place, SPRITE);
        draw.setTint(Color.GREEN);

        phys = new PhysicsComponent(this, place, MIN_SPEED, MAX_SPEED);

        moveInput = new PlayerMoveInputComponent(this, place, phys);
        fireInput = new PlayerShootInputComponent(this, place, pRoot);
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

        if (hit) {
            sumDelta += delta;

            if (sumDelta >= HIT_DELTA) {
                sumDelta = 0;
                hit = false;
            } else {
                draw.setTint(Color.RED);
            }
        }

        if (sgasg.getScore() <= MIN_POINT) {
            life.setAlive(false);
        }

        draw.draw();
    }

    public void collideUnfriendly(Factionable uf) {
    }

    @Override
    public void collideUnfriendlyBullet(Factionable ufBullet) {
        sgasg.addScore(BULLET_SCORE);
        hit = true;
    }

    public void collideSolid(RadialCollisionComponent rcc) {
        collision.stopAtSolid(rcc);
    }

    public boolean isAlive() {
        // return true;
        return life.isAlive();
    }
}
