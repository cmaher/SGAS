package com.cmaher.sgas.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.cmaher.game.components.Component;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.components.ShootBulletComponent;
import com.cmaher.game.entity.EntityBase;
import com.cmaher.sgas.entity.PlayerBullet;

public class PlayerShootInputComponent extends Component {
    private final static int     BUTTON_FIRE_PRIMARY      = Input.Buttons.LEFT;
    
    ShootBulletComponent shoot;
    
    // use pool of bullets
    public PlayerShootInputComponent(EntityBase master, PlaceComponent place) {
        super(master);
        shoot = new ShootBulletComponent(master, place);
        
    }
    
    public void update(float delta) {
        if(Gdx.input.isButtonPressed(BUTTON_FIRE_PRIMARY)) {
            shoot.fireNewBullet(delta, new PlayerBullet(master.getGame()));
        }
        shoot.update(delta);
    }
}