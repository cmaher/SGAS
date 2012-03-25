package com.cmaher.sgas.entity;

import com.badlogic.gdx.graphics.Color;
import com.cmaher.game.components.DrawComponent;
import com.cmaher.game.components.PhysicsComponent;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.entity.Entity;
import com.cmaher.sgas.SGASGame;
import com.cmaher.sgas.components.PlayerCollisionComponent;
import com.cmaher.sgas.components.PlayerFireInputComponent;
import com.cmaher.sgas.components.PlayerMoveInputComponent;

/**
 * The class controlling the player The Player can move, rotate, shoot, and get
 * hit
 * 
 * @author Christian
 * 
 */
public class Player extends Entity {
    public static final String       SPRITE    = SGASGame.ASSETS + "player.png";
    private static final int         RADIUS    = 32;
    private static final float       MIN_SPEED = 0f;
    private static final float       MAX_SPEED = 128f;

    private PlaceComponent           place;
    private DrawComponent            draw;
    private PhysicsComponent         phys;
    private PlayerMoveInputComponent moveInput;
    private PlayerFireInputComponent fireInput;
    private PlayerCollisionComponent collision;

    public Player(SGASGame game) {
        super(game);
        init(0, 0);
    }

    public Player(SGASGame game, int x, int y) {
        super(game);
        init(x, y);
    }

    private void init(int x, int y) {
        place = new PlaceComponent(this, x, y, RADIUS * 2, RADIUS * 2);

        draw = new DrawComponent(this, place, SPRITE);
        draw.setTint(Color.GREEN);

        phys = new PhysicsComponent(this, place, MIN_SPEED, MAX_SPEED);

        moveInput = new PlayerMoveInputComponent(this, place, phys);
        fireInput = new PlayerFireInputComponent(this, place);
        collision = new PlayerCollisionComponent(this, place);
    }

    @Override
    public void update(float delta) {
        moveInput.update(delta);
        draw.draw();
        fireInput.update(delta);
        collision.update(delta);
        phys.update(delta);

    }

    public void hitBy(Enemy enemy) {
        enemy.hit(this);
        draw.setTint(Color.RED);
    }
}
