package com.cmaher.sgas.components;

import java.util.Set;

import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.sgas.SGASType;
import com.cmaher.sgas.entity.Enemy;
import com.cmaher.sgas.entity.Player;

public class EnemyCollisionComponent extends RadialCollisionComponent {

    private Enemy enemy;
    
    public EnemyCollisionComponent(Enemy master, PlaceComponent place) {
        super(master, place, SGASType.Enemy);
        this.enemy = master;
    }

    
    public void update(float delta) {
        Set<RadialCollisionComponent> collisions = master.game.collisions.getCollisions(this);
        
        for(RadialCollisionComponent collision : collisions) {
            if(collision.getType().equals(SGASType.Player)) {
                enemy.collidePlayer((Player) collision.master);
            }
        }
    }
}
