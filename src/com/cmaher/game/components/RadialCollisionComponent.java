package com.cmaher.game.components;

import com.badlogic.gdx.math.Vector2;
import com.cmaher.game.FactionType;
import com.cmaher.game.collision.BoundingCircle;
import com.cmaher.game.entity.Factionable;

public class RadialCollisionComponent extends Component {
    private static long    nextId = 0;

    private PlaceComponent place;
    private FactionType    type;
    private BoundingCircle bounds;
    private long           id;

    public RadialCollisionComponent(Factionable master, PlaceComponent place,
            FactionType type) {
        super(master);
        this.place = place;
        this.type = type;
        bounds = new BoundingCircle(place.getCenter(), place.getWidth() / 2);
        master.getGame().getCollisionManager().add(this);
        
        this.id = nextId++;
    }

    public void update(float delta) {
        bounds.setCenter(place.getCenter());
        bounds.setRadius(place.getWidth() / 2);
    }

    public boolean checkCollision(RadialCollisionComponent other) {
        return bounds.collides(other.getBounds());
    }

    /**
     * Move this rcc so that it is touching, not overlapping the other rcc
     */
    public void stopAtSolid(RadialCollisionComponent rcc) {
        Vector2 newPosition = bounds.getCenter()
                .sub(rcc.getBounds().getCenter()).nor()
                .mul(getBounds().getRadius() + rcc.getBounds().getRadius());
        newPosition = rcc.getBounds().getCenter().add(newPosition);
        place.setPositionByCenter(newPosition);
        bounds.setCenter(place.getCenter());
    }

    public FactionType getType() {
        return type;
    }

    public void setType(FactionType type) {
        this.type = type;
    }

    public BoundingCircle getBounds() {
        return bounds;
    }

    public void setBounds(BoundingCircle bounds) {
        this.bounds = bounds;
    }

    public Factionable getFactionable() {
        return (Factionable) master;
    }
    
    public long getId() {
        return id;
    }
}
