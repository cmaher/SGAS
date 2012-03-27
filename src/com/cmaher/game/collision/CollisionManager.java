package com.cmaher.game.collision;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cmaher.game.components.RadialCollisionComponent;

/**
 * A Highly naive implementation of collision management In general, my
 * collision detection is naive, but it should get my point across. If this ends
 * up being a bottleneck, I'll fix it later.
 * 
 * @author Christian
 * 
 */
public class CollisionManager {

    private Set<RadialCollisionComponent> collisionObjects;
    private List<CollisionPair>           resolvedCollisions;

    public CollisionManager() {
        collisionObjects = new HashSet<RadialCollisionComponent>();
        resolvedCollisions = new ArrayList<CollisionPair>();
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

    // naive implementation, can be run in parallel
    public Set<RadialCollisionComponent> getCollisions(
            RadialCollisionComponent rcc) {
        Set<RadialCollisionComponent> collisions = new HashSet<RadialCollisionComponent>();

        for (RadialCollisionComponent other : collisionObjects) {
            if (rcc != other) {
                if (rcc.checkCollision(other)) {
                    collisions.add(other);
                }
            }
        }

        return collisions;
    }

    public void setResolved(RadialCollisionComponent r1,
            RadialCollisionComponent r2) {

        resolvedCollisions.add(new CollisionPair(r1, r2));
    }

    public boolean isResolved(RadialCollisionComponent r1,
            RadialCollisionComponent r2) {
        
        boolean found = false;
        CollisionPair pair = new CollisionPair(r1, r2);
        
        for(CollisionPair other : resolvedCollisions) {
            if(pair.equals(other)) {
                found = true;
                break;
            }
        }
        
        return found;
    }
    
    public void clearResolvedCollisions() {
        resolvedCollisions.clear();
    }

    private class CollisionPair implements Comparable<CollisionPair> {
        private long first;
        private long second;

        public CollisionPair(RadialCollisionComponent r1,
                RadialCollisionComponent r2) {
            this.first = r1.getId();
            this.second = r2.getId();
        }

        
        /**
         * Returns 0 if the pairs contain the same two values (not necessarily in the same order)
         */
        @Override
        public int compareTo(CollisionPair other) {

            if ((this.first == other.first && this.second == other.second)
                    || (this.first == other.second && this.second == other.first)) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
