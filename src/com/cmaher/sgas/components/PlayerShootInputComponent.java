package com.cmaher.sgas.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.cmaher.game.components.Component;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.components.ShootBulletComponent;
import com.cmaher.sgas.entity.PlayerBullet;
import com.cmaher.sgas.entity.SGASPlayer;

public class PlayerShootInputComponent extends Component {
    private final static int     BUTTON_FIRE_PRIMARY      = Input.Buttons.LEFT;
    
    private ShootBulletComponent shoot;
    private String pRoot;
    
    // use pool of bullets
    public PlayerShootInputComponent(SGASPlayer master, PlaceComponent place, String pRoot) {
        super(master);
        this.pRoot = pRoot;
        shoot = new ShootBulletComponent(master, place);
        
    }
    
    public void update(float delta) {
        if(Gdx.input.isButtonPressed(BUTTON_FIRE_PRIMARY)) {
            shoot.fireNewBullet(new PlayerBullet(master.getGame(), pRoot));
        }
        shoot.update(delta);
    }
}