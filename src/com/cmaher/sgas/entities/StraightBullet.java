package com.cmaher.sgas.entities;
import com.cmaher.sgas.SGASGame;
import com.cmaher.sgas.components.DrawComponent;
import com.cmaher.sgas.components.PhysicsComponent;
import com.cmaher.sgas.components.PlaceComponent;


public class StraightBullet extends Entity {
    private static final String asset = SGASGame.ASSETS + "bullet.png";
    
    
    PlaceComponent place;
    PhysicsComponent phys;
    DrawComponent draw;

    private boolean alive = false;

    public StraightBullet(SGASGame game) {
        super(game);
        place = new PlaceComponent(this);
        phys = new PhysicsComponent(this, place);
        draw = new DrawComponent(this, place, asset);
    }

    public void shoot(float x0, float y0, float angle, float speed) {
        place.setPosition(x0, y0);
        phys.setVelocity(angle, speed);
        alive = true;
    }

    public void update(float delta) {
        if(alive) {
            phys.update(delta);
            draw.draw();
        }
    }

    public void kill() {
        this.alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
}