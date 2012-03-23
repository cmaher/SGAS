package com.cmaher.sgas.components;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.cmaher.sgas.SGASGame;
import com.cmaher.sgas.entities.Entity;

public class PlayerInputComponent extends Component {
    private final List<PlayerKeyInput> PLAYER_KEYS  = new LinkedList<PlayerKeyInput>();

    private final static float         ACCELERATION = 1024;
    private final static float         DECELERATION = 1024;
    private final static float         FUZZY_DELTA  = .02f;

    private PlaceComponent             place;
    private PhysicsComponent           phys;
    private float                      cumDelta     = 0;

    public PlayerInputComponent(Entity master, PlaceComponent place,
            PhysicsComponent phys) {
        super(master);
        this.place = place;
        this.phys = phys;

        PLAYER_KEYS.add(new PlayerKeyInput(Input.Keys.W, PhysicsComponent.VECTOR_UP));
        PLAYER_KEYS.add(new PlayerKeyInput(Input.Keys.A, PhysicsComponent.VECTOR_LEFT));
        PLAYER_KEYS.add(new PlayerKeyInput(Input.Keys.S, PhysicsComponent.VECTOR_DOWN));
        PLAYER_KEYS.add(new PlayerKeyInput(Input.Keys.D, PhysicsComponent.VECTOR_RIGHT));
    }

    public void update(float delta) {
        updateVelocity(delta);
        updateAngle();
    }

    private void updateVelocity(float delta) {
        Vector2 direction = new Vector2();

        // sum all the input vectors to get the total direction
        for (PlayerKeyInput input : PLAYER_KEYS) {
            if (Gdx.input.isKeyPressed(input.getKeyCode())) {
                direction.add(input.getDirection());
            }
        }

        direction.nor();
        if (direction.x == 0 && direction.y == 0) {
            phys.setDeceleration(DECELERATION);
            phys.processDeceleration();
        } else {
            if (cumDelta < FUZZY_DELTA) {
                cumDelta += delta;
            } else {
                phys.setAcceleration(direction, ACCELERATION);          
                cumDelta = 0;
            }
        }
    }

    private void updateAngle() {
        float angle = place.findAngle(Gdx.input.getX(), SGASGame.HEIGHT - Gdx.input.getY());
        place.setAngle(angle);
    }

    private class PlayerKeyInput {
        private int     keyCode;
        private Vector2 direction;

        public PlayerKeyInput(int keyCode, Vector2 direction) {
            this.keyCode = keyCode;
            this.direction = direction;
        }

        public int getKeyCode() {
            return keyCode;
        }

        public Vector2 getDirection() {
            return direction;
        }
    }
}