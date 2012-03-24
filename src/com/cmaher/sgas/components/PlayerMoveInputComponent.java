package com.cmaher.sgas.components;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.cmaher.sgas.SGASGame;
import com.cmaher.sgas.entities.Entity;

public class PlayerMoveInputComponent extends Component {
    private final List<MoveInput> MOVE_KEYS    = new LinkedList<MoveInput>();

    private final static float    ACCELERATION = 1024;
    private final static float    DECELERATION = 1024;
    private final static float    FUZZY_DELTA  = .02f;
    private final static int      KEY_UP       = Input.Keys.W;
    private final static int      KEY_LEFT     = Input.Keys.A;
    private final static int      KEY_DOWN     = Input.Keys.S;
    private final static int      KEY_RIGHT    = Input.Keys.D;

    private PlaceComponent        place;
    private PhysicsComponent      phys;
    private float                 cumDelta     = 0;

    public PlayerMoveInputComponent(Entity master, PlaceComponent place,
            PhysicsComponent phys) {
        super(master);
        this.place = place;
        this.phys = phys;

        MOVE_KEYS.add(new MoveInput(KEY_UP, PhysicsComponent.VECTOR_UP));
        MOVE_KEYS.add(new MoveInput(KEY_LEFT, PhysicsComponent.VECTOR_LEFT));
        MOVE_KEYS.add(new MoveInput(KEY_DOWN, PhysicsComponent.VECTOR_DOWN));
        MOVE_KEYS.add(new MoveInput(KEY_RIGHT, PhysicsComponent.VECTOR_RIGHT));
    }

    public void update(float delta) {
        updateVelocity(delta);
        updateAngle();
    }

    //TODO: fix a bug with this function where the window losing focus keeps the key pressed down
    private void updateVelocity(float delta) {
        Vector2 direction = new Vector2();

        // sum all the input vectors to get the total direction
        for (MoveInput input : MOVE_KEYS) {
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
        float angle = place.findAngle(Gdx.input.getX(), SGASGame.HEIGHT
                - Gdx.input.getY());
        place.setAngle(angle);
    }

    private class MoveInput {
        private int     keyCode;
        private Vector2 direction;

        public MoveInput(int keyCode, Vector2 direction) {
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