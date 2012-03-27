package com.cmaher.sgas.entity;

import com.badlogic.gdx.graphics.Color;
import com.cmaher.game.components.DrawComponent;
import com.cmaher.game.components.PhysicsComponent;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.game.entity.Entity;
import com.cmaher.sgas.SGASGame;
import com.cmaher.sgas.components.PlayerCollisionComponent;
import com.cmaher.sgas.components.PlayerShootInputComponent;
import com.cmaher.sgas.components.PlayerMoveInputComponent;

/**
 * The class controlling the player The Player can move, rotate, shoot, and get
 * hit
 * 
 * @author Christian
 * 
 */
public class Player extends Entity {
    private static final String      SPRITE    = SGASGame.ASSETS + "player.png";
    private static final float       RADIUS    = 32;
    private static final float       MIN_SPEED = 0f;
    private static final float       MAX_SPEED = 128f;

    private PlaceComponent           place;
    private DrawComponent            draw;
    private PhysicsComponent         phys;
    private PlayerMoveInputComponent moveInput;
    private PlayerShootInputComponent fireInput;
    private PlayerCollisionComponent collision;

    public Player(SGASGame game) {
        super(game);
        init(0, 0);
    }

    public Player(SGASGame game, float x, float y) {
        super(game);
        init(x, y);
    }

    private void init(float x, float y) {
        place = new PlaceComponent(this, x, y, RADIUS * 2, RADIUS * 2);

        draw = new DrawComponent(this, place, SPRITE);
        draw.setTint(Color.GREEN);

        phys = new PhysicsComponent(this, place, MIN_SPEED, MAX_SPEED);

        moveInput = new PlayerMoveInputComponent(this, place, phys);
        fireInput = new PlayerShootInputComponent(this, place);
        collision = new PlayerCollisionComponent(this, place);
        
        game.assetWrapper.addTextureAsset(SPRITE);
    }

    @Override
    public void update(float delta) {
        moveInput.update(delta);
        fireInput.update(delta);
        phys.update(delta);
        
        draw.setTint(Color.GREEN);
        
        collision.update(delta);
        draw.draw();
    }

    public void hitBy(Enemy enemy) {
        enemy.collidePlayer(this);
        draw.setTint(Color.RED);
    }

    public void hitSolid(RadialCollisionComponent rcc) {
        collision.stopAtSolid(rcc);
    }
}
