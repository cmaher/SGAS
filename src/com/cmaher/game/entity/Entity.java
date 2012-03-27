package com.cmaher.game.entity;

import com.cmaher.game.GameBase;

public interface Entity {
    public void update(float delta);

    public GameBase getGame();
}
