package com.cmaher.sgas.components;

import com.badlogic.gdx.math.Vector2;
import com.cmaher.sgas.entities.Entity;

public class PhysicsComponent extends Component {
    public static final Vector2 VECTOR_UP    = new Vector2(0, 1);
    public static final Vector2 VECTOR_DOWN  = new Vector2(0, -1);
    public static final Vector2 VECTOR_LEFT  = new Vector2(-1, 0);
    public static final Vector2 VECTOR_RIGHT = new Vector2(1, 0);

    private PlaceComponent      place;
    private Vector2             velocity;                         // pixels/second
    private Vector2             acceleration;                     // pixels/second^2
    private float               maxSpeed;
    private float               minSpeed;

    public PhysicsComponent(Entity master, PlaceComponent place) {
        super(master);
        this.place = place;
        init(0f, 0f);
    }

    public PhysicsComponent(Entity master, PlaceComponent place,
            float minSpeed, float maxSpeed) {
        super(master);
        this.place = place;
        init(maxSpeed, minSpeed);
    }

    private void init(float maxSpeed, float minSpeed) {
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
        velocity = new Vector2(0f, 0f);
        acceleration = new Vector2(0f, 0f);
    }

    // delta in seconds
    public void update(float delta) {
        Vector2 deltaAccel = acceleration.cpy().mul(delta);
        velocity.add(deltaAccel);

        if (velocity.len() > maxSpeed) {
            velocity = velocity.nor().mul(maxSpeed);

        } else if (velocity.len() <= minSpeed) {
            velocity = velocity.nor().mul(minSpeed);
        }

        place.setPosition(place.getX() + (velocity.x * delta), place.getY()
                + (velocity.y * delta));
    }

    public void setVelocityDirection(Vector2 direction) {
        velocity = direction.nor().mul(velocity.len());
    }

    public Vector2 getVelocityDirection() {
        return velocity.cpy().nor();
    }

    public void setAccelerationDirection(Vector2 direction) {
        acceleration = direction.cpy().nor().mul(acceleration.len());
    }

    public Vector2 getAccelerationDirection() {
        return acceleration.cpy().nor();
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity.cpy();
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration.cpy();
    }

    public void setAcceleration(Vector2 direction, float magnitude) {
        this.acceleration = direction.cpy().nor().mul(magnitude);
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMinSpeed(float minSpeed) {
        this.minSpeed = minSpeed;
    }

    public float getMinSpeed() {
        return minSpeed;
    }
}