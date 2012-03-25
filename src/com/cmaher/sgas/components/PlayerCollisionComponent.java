package com.cmaher.sgas.components;

import java.util.Set;

import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.sgas.SGASType;
import com.cmaher.sgas.entity.Enemy;
import com.cmaher.sgas.entity.Player;

public class PlayerCollisionComponent extends RadialCollisionComponent {
    
    public final Player player;
    
    public PlayerCollisionComponent(Player master, PlaceComponent place) {
        super(master, place, SGASType.Player);
        this.player = master;
    }
    
    public void update(float delta) {
        Set<RadialCollisionComponent> collisions = master.game.collisions.getCollisions(this);
        
        for(RadialCollisionComponent collision : collisions) {
            if(collision.getType().equals(SGASType.Enemy)) {
                player.hitBy((Enemy) collision.master);
            }
        }
    }
}
