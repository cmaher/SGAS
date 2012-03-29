package com.cmaher.game.collision;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cmaher.game.components.CollisionComponent;

/**
 * A Highly naive implementation of collision management In general, my
 * collision detection is naive, but it should get my point across. If this ends
 * up being a bottleneck, I'll fix it later.
 * 
 * @author Christian
 * 
 */
public class CollisionManager {

    private Set<CollisionComponent> collisionObjects;
    private List<CollisionPair>     resolvedCollisions;

    public CollisionManager() {
        collisionObjects = new HashSet<CollisionComponent>();
        resolvedCollisions = new ArrayList<CollisionPair>();
    }

    public void add(CollisionComponent rcc) {
        collisionObjects.add(rcc);
    }

    public void remove(CollisionComponent rcc) {
        collisionObjects.remove(rcc);
    }

    public Set<CollisionComponent> getCollisionObjects() {
        return collisionObjects;
    }

    // naive implementation, can be made to run in parallel
    public Set<CollisionComponent> getCollisions(CollisionComponent rcc) {
        Set<CollisionComponent> collisions = new HashSet<CollisionComponent>();

        for (CollisionComponent other : collisionObjects) {
            if (rcc != other) {
                if (rcc.checkCollision(other)) {
                    collisions.add(other);
                }
            }
        }

        return collisions;
    }

    public void setResolved(CollisionComponent r1, CollisionComponent r2) {

        resolvedCollisions.add(new CollisionPair(r1, r2));
    }

    public boolean isResolved(CollisionComponent r1, CollisionComponent r2) {

        boolean found = false;
        CollisionPair pair = new CollisionPair(r1, r2);

        for (CollisionPair other : resolvedCollisions) {
            if (pair.equals(other)) {
                found = true;
                break;
            }
        }

        return found;
    }

    public void clearResolvedCollisions() {
        resolvedCollisions.clear();
    }

    public boolean isColliding(CollisionComponent cc) {
        boolean colliding = false;
        
        for(CollisionPair pair : resolvedCollisions) {
            if(pair.contains(cc)) {
                colliding = true;
                break;
            }
        }
        
        return colliding;
    }

    private class CollisionPair implements Comparable<CollisionPair> {
        private long first;
        private long second;

        public CollisionPair(CollisionComponent r1, CollisionComponent r2) {
            this.first = r1.getId();
            this.second = r2.getId();
        }

        /**
         * Returns 0 if the pairs contain the same two values (not necessarily
         * in the same order)
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
        
        public boolean contains(CollisionComponent cc) {
            return (cc.getId() == first || cc.getId() == second); 
        }
    }
}
