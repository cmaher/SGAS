package com.cmaher.sgas.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.cmaher.sgas.entities.Entity;

public class PlayerFireInputComponent extends Component {
    private final static int BUTTON_FIRE_PRIMARY = Input.Buttons.LEFT;

    PlaceComponent           place;

    // use pool of bullets
    public PlayerFireInputComponent(Entity master, PlaceComponent place) {
        super(master);
        this.place = place;
    }

    public void update(float delta) {
        if (Gdx.input.isButtonPressed(BUTTON_FIRE_PRIMARY)) {
            // fire a bullet
        }
    }
}