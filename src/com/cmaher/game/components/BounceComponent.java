package com.cmaher.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.cmaher.game.entity.Entity;

public class BounceComponent extends Component {

    private PlaceComponent   place;
    private PhysicsComponent phys;

    public BounceComponent(Entity master, PlaceComponent place, PhysicsComponent phys) {
        super(master);
        this.place = place;
        this.phys = phys;
    }
    
    public void update(float delta) {
        Vector2 a = phys.getAcceleration();
        Vector2 v = phys.getVelocity();
        
        if(place.getX() <= 0 || place.getX() >= Gdx.graphics.getWidth() - place.getWidth()) {
            a.x = -a.x;
            v.x = -v.x;          
        }
        
        if(place.getY() <= 0 || place.getY() >= Gdx.graphics.getHeight() - place.getHeight()) {
            a.y = -a.y;
            v.y = -v.y;
        }
        
        phys.setAcceleration(a);
        phys.setVelocity(v);
    }
}
