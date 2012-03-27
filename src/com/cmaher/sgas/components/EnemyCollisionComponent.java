package com.cmaher.sgas.components;

import java.util.Set;

import com.cmaher.game.GameType;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.game.entity.Enemy;
import com.cmaher.sgas.entity.SGASPlayer;

public class EnemyCollisionComponent extends RadialCollisionComponent {

    private Enemy enemy;
    
    public EnemyCollisionComponent(Enemy master, PlaceComponent place) {
        super(master, place, GameType.Enemy);
        this.enemy = master;
    }

    
    public void update(float delta) {
        Set<RadialCollisionComponent> collisions = master.game.collisions.getCollisions(this);
        
        for(RadialCollisionComponent collision : collisions) {
            if(collision.getType().equals(GameType.Player)) {
                enemy.collidePlayer((SGASPlayer) collision.master);
            }
        }
    }
}
