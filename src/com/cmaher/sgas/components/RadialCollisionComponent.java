package com.cmaher.sgas.components;

import com.cmaher.sgas.SGASType;
import com.cmaher.sgas.collision.BoundingCircle;
import com.cmaher.sgas.entities.Entity;

public class RadialCollisionComponent extends Component {
    private PlaceComponent   place;
    private SGASType         type;
    private BoundingCircle   bounds;

    public RadialCollisionComponent(Entity master, PlaceComponent place, SGASType type) {
        super(master);
        this.place = place;
        this.type = type;
        bounds = new BoundingCircle(place.getCenter(), place.getWidth() / 2);
        master.game.collisions.add(this);
    }

    public void update(float delta) {
        bounds.setCenter(place.getCenter());
    }
    
    public SGASType checkCollision(RadialCollisionComponent other) {
        SGASType collisionType = SGASType.None;
        if(bounds.collides(other.getBounds())) {
            collisionType = other.getType();
        }
        
        return collisionType;
    }
    
    public SGASType getType() {
        return type;
    }

    public void setType(SGASType type) {
        this.type = type;
    }

    public BoundingCircle getBounds() {
        return bounds;
    }

    public void setBounds(BoundingCircle bounds) {
        this.bounds = bounds;
    }
}
