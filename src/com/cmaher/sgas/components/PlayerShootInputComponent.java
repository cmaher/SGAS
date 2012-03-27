package com.cmaher.sgas.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.cmaher.game.components.Component;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.entity.Entity;

public class PlayerShootInputComponent extends Component {
    private final static int     BUTTON_FIRE_PRIMARY      = Input.Buttons.LEFT;
    
    ShootBulletComponent shoot;
    
    // use pool of bullets
    public PlayerShootInputComponent(Entity master, PlaceComponent place) {
        super(master);
        shoot = new ShootBulletComponent(master, place);
        
    }
    
    public void update(float delta) {
        boolean fireNew = Gdx.input.isButtonPressed(BUTTON_FIRE_PRIMARY);
        shoot.update(delta, fireNew);
    }
}