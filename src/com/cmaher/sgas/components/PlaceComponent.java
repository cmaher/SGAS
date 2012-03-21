package com.cmaher.sgas.components;

import com.badlogic.gdx.math.Vector2;
import com.cmaher.sgas.entities.Entity;

public class PlaceComponent extends Component {
    protected float      x;
    protected float      y;
    protected int     width;
    protected int     height;
    protected float   angle;
    protected Vector2 center; // relative to x, y

    public PlaceComponent(Entity master) {
        super(master);
        init(0, 0, 0, 0);
    }

    public PlaceComponent(Entity master, float x, float y, int width, int height) {
        super(master);
        init(x, y, width, height);
    }

    private void init(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = 0;
        center = new Vector2(x + width / 2, y + height / 2);
    }

    private void updateCenter() {
        center.x = x + width / 2;
        center.y = y + height / 2;
    }
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        updateCenter();
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        updateCenter();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        updateCenter();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        updateCenter();
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public Vector2 getCenter() {
        return center;
    }
}
