package com.cmaher.sgas.components;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.cmaher.sgas.entities.Entity;

public class PlayerInputComponent extends Component {
    private final List<PlayerKeyInput> PLAYER_KEYS  = new LinkedList<PlayerKeyInput>();
    private final static Vector2       ZERO         = new Vector2(0f, 0f);

    private final static float         ACCELERATION = 1024;
    private final static float         DECELERATION = 1024;
    private final static float         FUZZY_DELTA  = .02f;

    private PlaceComponent             place;
    private PhysicsComponent           phys;
    private boolean                    decelerating = false;
    private float                      cumDelta     = 0;

    public PlayerInputComponent(Entity master, PlaceComponent place,
            PhysicsComponent phys) {
        super(master);
        this.place = place;
        this.phys = phys;

        PLAYER_KEYS.add(new PlayerKeyInput(Input.Keys.W, new Vector2(0, 1)));
        PLAYER_KEYS.add(new PlayerKeyInput(Input.Keys.A, new Vector2(-1, 0)));
        PLAYER_KEYS.add(new PlayerKeyInput(Input.Keys.S, new Vector2(0, -1)));
        PLAYER_KEYS.add(new PlayerKeyInput(Input.Keys.D, new Vector2(1, 0)));
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

            if (decelerating) {
                Vector2 vDir = phys.getVelocity().cpy().nor();
                Vector2 aDir = phys.getAcceleration().cpy().nor();
                if (((vDir.x >= 0 && aDir.x >= 0) || (vDir.x <= 0 && aDir.x <= 0))
                        && ((vDir.y >= 0 && aDir.y >= 0) || (vDir.y <= 0 && aDir.y <= 0))) {
                    phys.setAcceleration(ZERO);
                    phys.setVelocity(ZERO);
                    decelerating = false;
                }
            }

            if (!decelerating
                    && (phys.getAcceleration().x != 0 || phys.getAcceleration().y != 0)) {
                phys.setAcceleration(ZERO.cpy().sub(phys.getAcceleration()),
                        DECELERATION);
                decelerating = true;
            }

            // stop decelerating when velocity is 0 or reversed
        } else {
            if (cumDelta < FUZZY_DELTA) {
                cumDelta += delta;
            } else {
                phys.setAcceleration(direction, ACCELERATION);
                System.out.println(direction + " " + phys.getAcceleration());
                decelerating = false;
                cumDelta = 0;
            }
        }
    }

    private void updateAngle() {
        // float angle = new Vector2(Gdx.input.getX(), Gdx.input.getY()).sub(
        // place.getCenter()).angle();
        // System.out.println(angle);
        // place.setAngle(angle);
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