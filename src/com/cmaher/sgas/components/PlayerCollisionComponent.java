package com.cmaher.sgas.components;

import java.util.Set;

import com.cmaher.game.GameType;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.game.entity.Enemy;
import com.cmaher.sgas.entity.SGASPlayer;

public class PlayerCollisionComponent extends RadialCollisionComponent {

    public final SGASPlayer player;

    public PlayerCollisionComponent(SGASPlayer master, PlaceComponent place) {
        super(master, place, GameType.Player);
        this.player = master;
    }

    public void update(float delta) {
        super.update(delta);
        Set<RadialCollisionComponent> collisions = master.game.collisions
                .getCollisions(this);

        for (RadialCollisionComponent collision : collisions) {
            if (collision.getType().equals(GameType.Enemy)) {
                player.collideEnemy((Enemy) collision.master);
            }
        }
    }
}
