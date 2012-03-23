package com.cmaher.sgas.components;

import com.badlogic.gdx.math.Vector2;
import com.cmaher.sgas.entities.Entity;

public class PhysicsComponent extends Component {
    public static final Vector2  VECTOR_UP    = new Vector2(0, 1);
    public static final Vector2  VECTOR_DOWN  = new Vector2(0, -1);
    public static final Vector2  VECTOR_LEFT  = new Vector2(-1, 0);
    public static final Vector2  VECTOR_RIGHT = new Vector2(1, 0);
    private final static Vector2 ZERO         = new Vector2(0f, 0f);

    private PlaceComponent       place;
    private Vector2              velocity;                          // pixels/second
    private Vector2              acceleration;                      // pixels/second^2
    private float                maxSpeed;
    private float                minSpeed;
    private boolean              decelerating = false;
    private float                deceleration = 0f;

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
        velocity.add(acceleration.cpy().mul(delta));

        if (velocity.len() > maxSpeed) {
            velocity = velocity.nor().mul(maxSpeed);

        } else if (velocity.len() <= minSpeed) {
            velocity = velocity.nor().mul(minSpeed);
        }

        place.setPosition(place.getX() + (velocity.x * delta), place.getY()
                + (velocity.y * delta));
    }
    
    public void processDeceleration() {
        if (decelerating) {
            Vector2 vDir = velocity.cpy().nor();
            Vector2 aDir = acceleration.cpy().nor();
            if (((vDir.x >= 0 && aDir.x >= 0) || (vDir.x <= 0 && aDir.x <= 0))
                    && ((vDir.y >= 0 && aDir.y >= 0) || (vDir.y <= 0 && aDir.y <= 0))) {
                setAcceleration(ZERO);
                setVelocity(ZERO);
                decelerating = false;
            }
        }

        if (!decelerating && (acceleration.x != 0 || acceleration.y != 0)) {
            setAcceleration(ZERO.cpy().sub(acceleration), deceleration);
            decelerating = true;
        }
    }
 
    public boolean isDecelerating() {
        return decelerating;
    }

    public void setDecelerating(boolean decelerating) {
        this.decelerating = decelerating;
    }
    
    public float getDeceleration() {
        return deceleration;
    }
    
    public void setDeceleration(float deceleration) {
        this.deceleration = deceleration;
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