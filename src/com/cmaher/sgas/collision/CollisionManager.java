package com.cmaher.sgas.collision;

import java.util.HashSet;
import java.util.Set;

import com.cmaher.sgas.components.RadialCollisionComponent;

/**
 * A Highly naive implementation of collision management
 * In general, my collision detection is naive, but it should get my point across.
 * If this ends up being a bottleneck, I'll fix it later.
 * @author Christian
 *
 */
public class CollisionManager {
    
    private Set<RadialCollisionComponent> collisionObjects;
    
    public CollisionManager() {
        collisionObjects = new HashSet<RadialCollisionComponent>();
    }
    
    public void add(RadialCollisionComponent rcc) {
        collisionObjects.add(rcc);
    }
    
    public void remove(RadialCollisionComponent rcc) {
        collisionObjects.remove(rcc);
    }
    
    public Set<RadialCollisionComponent> getCollisionObjects() {
        return collisionObjects;
    }
}
