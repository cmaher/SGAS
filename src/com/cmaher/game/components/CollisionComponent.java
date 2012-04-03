package com.cmaher.game.components;

import com.cmaher.game.collision.BoundingCircle;

public interface CollisionComponent {

    public void update(float delta);

    public boolean checkCollision(CollisionComponent other);

    public void stopAtSolid(CollisionComponent cc);

    public BoundingCircle getBounds();
    
    public long getId();

}
