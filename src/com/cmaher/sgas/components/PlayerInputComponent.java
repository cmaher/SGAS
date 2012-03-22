package com.cmaher.sgas.components;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.cmaher.sgas.entities.Entity;

public class PlayerInputComponent extends Component {
    private final List<PlayerKeyInput> PLAYER_KEYS = new LinkedList<PlayerKeyInput>();

    private PlaceComponent             place;
    private PhysicsComponent           phys;

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
        updateVelocity();
        updateAngle();
    }

    private void updateVelocity() {
        Vector2 velocity = new Vector2();

        // sum all the input vectors to get the total direction
        for (PlayerKeyInput input : PLAYER_KEYS) {
            if (Gdx.input.isKeyPressed(input.getKeyCode())) {
                velocity.add(input.getDirection());
            }
        }

        velocity.nor().mul(phys.getMaxSpeed());
        phys.setVelocity(velocity);
    }

    private void updateAngle() {
//        float angle = new Vector2(Gdx.input.getX(), Gdx.input.getY()).sub(
//                place.getCenter()).angle();
//        System.out.println(angle);
//        place.setAngle(angle);
    }

    private class PlayerKeyInput {
        private int     keyCode;
        private Vector2 direction;
        private boolean isDown;

        public PlayerKeyInput(int keyCode, Vector2 direction) {
            this.keyCode = keyCode;
            this.direction = direction;
            isDown = false;
        }

        public int getKeyCode() {
            return keyCode;
        }

        public Vector2 getDirection() {
            return direction;
        }

        public void setDown(boolean down) {
            isDown = down;
        }

        public boolean isDown() {
            return isDown;
        }
    }
}