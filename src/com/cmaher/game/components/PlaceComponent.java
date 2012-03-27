package com.cmaher.game.components;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.cmaher.game.entity.EntityBase;

public class PlaceComponent extends Component {
    protected float   x;
    protected float   y;
    protected float   width;
    protected float   height;
    protected float   angle;                 // in degrees
    protected Vector2 origin = new Vector2();
    protected Vector2 center = new Vector2();

    public PlaceComponent(EntityBase master) {
        super(master);
        init(0, 0, 0, 0);
    }

    public PlaceComponent(EntityBase master, float x, float y, float width,
            float height) {
        super(master);
        init(x, y, width, height);
    }

    private void init(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = 0;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setPositionByCenter(Vector2 newCenter) {
        this.x = newCenter.x - width / 2;
        this.y = newCenter.y - height / 2;
    }

    public float findAngle(float x1, float y1) {
        Vector2 center = getCenter();
        float x0 = center.x;
        float y0 = center.y;
        float a = MathUtils.atan2(y1 - y0, x1 - x0);

        return a * MathUtils.radiansToDegrees;
    }

    public boolean inBounds(float x1, float y1, float x2, float y2) {
        return (x >= x1 && x1 <= x2 && y >= y1 && y <= y2);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle % 360;
    }

    public Vector2 getCenter() {
        center.x = x + width / 2;
        center.y = y + height / 2;
        return center.cpy();
    }

    public Vector2 getOrigin() {
        return origin.cpy();
    }

    public void setOrigin(float x, float y) {
        this.origin.x = x;
        this.origin.y = y;
    }

}
