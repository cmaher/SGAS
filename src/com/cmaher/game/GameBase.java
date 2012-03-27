package com.cmaher.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cmaher.game.asset.AssetWrapper;
import com.cmaher.game.collision.CollisionManager;

public interface GameBase extends ApplicationListener {
    public static final String ASSETS = null;

    public AssetWrapper getAssetWrapper();

    public SpriteBatch getSpriteBatch();

    public CollisionManager getCollisionManager();

}
