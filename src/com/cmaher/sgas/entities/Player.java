package com.cmaher.sgas.entities;

import com.cmaher.sgas.SGASGame;
import com.cmaher.sgas.components.DrawComponent;
import com.cmaher.sgas.components.PlaceComponent;

/**
 * The class controlling the player The Player can move, rotate, shoot, and get
 * hit
 * 
 * @author Christian
 * 
 */
public class Player extends Entity {
    public static final int    PLAYER_RADIUS = 16;
    public static final String PLAYER_SPRITE = SGASGame.ASSETS + "player.png";

    private PlaceComponent     place;
    private DrawComponent      draw;

    public Player(SGASGame game) {
        super(game);
        init(0, 0);
    }

    public Player(SGASGame game, int x, int y) {
        super(game);
        init(x, y);
    }

    private void init(int x, int y) {
        place = new PlaceComponent(this, x, y, PLAYER_RADIUS * 2,
                PLAYER_RADIUS * 2);
        draw = new DrawComponent(this, place, PLAYER_SPRITE);
    }

    @Override
    public void update(float delta) {
        draw.draw();
    }
}
