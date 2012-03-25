package com.cmaher.sgas.entity;

import com.cmaher.game.components.PlaceComponent;
import com.cmaher.sgas.SGASGame;
import com.cmaher.sgas.components.EnemyCollisionComponent;

public class StationaryEnemy extends Enemy {

    PlaceComponent          place;
    EnemyCollisionComponent collision;

    public StationaryEnemy(SGASGame game) {
        super(game);
        place = new PlaceComponent(this);
    }

    public void hit(Player player) {
        //make player stop
    }

    public void update(float delta) {
        // TODO Auto-generated method stub

    }

    @Override
    public void hitByPlayer(Player player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hitByBullet(StraightBullet bullet) {
        // TODO Auto-generated method stub
        
    }

}
