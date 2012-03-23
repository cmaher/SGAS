package com.cmaher.sgas.entities;

import com.badlogic.gdx.graphics.Color;
import com.cmaher.sgas.SGASGame;
import com.cmaher.sgas.components.DrawComponent;
import com.cmaher.sgas.components.PhysicsComponent;
import com.cmaher.sgas.components.PlaceComponent;
import com.cmaher.sgas.components.PlayerInputComponent;

/**
 * The class controlling the player The Player can move, rotate, shoot, and get
 * hit
 * 
 * @author Christian
 * 
 */
public class Player extends Entity {
    public static final String   SPRITE    = SGASGame.ASSETS + "player.png";
    private static final int     RADIUS    = 32;
    private static final float   MIN_SPEED = 0f;
    private static final float   MAX_SPEED = 128f;

    private PlaceComponent       place;
    private DrawComponent        draw;
    private PhysicsComponent     phys;
    private PlayerInputComponent input;

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

        input = new PlayerInputComponent(this, place, phys);
    }

    @Override
    public void update(float delta) {
        input.update(delta);
        phys.update(delta);
        draw.draw();   
    }
}
