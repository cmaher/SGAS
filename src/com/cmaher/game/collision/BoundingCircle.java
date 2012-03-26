package com.cmaher.game.collision;

import com.badlogic.gdx.math.Vector2;

public class BoundingCircle {
    private Vector2 center;
    private float   radius;

    public BoundingCircle(Vector2 center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean collides(BoundingCircle other) {
        return center.dst2(other.center) 
                < ((radius + other.radius) * (radius + other.radius));
    }

    public Vector2 getCenter() {
        return center.cpy();
    }

    public void setCenter(Vector2 center) {
        this.center.x = center.x;
        this.center.y = center.y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

}
