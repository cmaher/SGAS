package com.cmaher.sgas;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cmaher.game.GameBase;
import com.cmaher.game.asset.AssetWrapper;
import com.cmaher.game.collision.CollisionManager;
import com.cmaher.sgas.entity.RotatingEnemy;
import com.cmaher.sgas.entity.SGASPlayer;

public class SGASGame implements ApplicationListener, GameBase {
    public static final String ASSETS = "assets/"; // NOTE: "assets\" fails

    public AssetWrapper        assetWrapper;
    public SpriteBatch         spriteBatch;
    public CollisionManager    collisionManager;

    public AssetWrapper getAssetWrapper() {
        return assetWrapper;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    private SGASPlayer             player;
    private RotatingEnemy      rEnemy;

    @Override
    public void create() {
        assetWrapper = new AssetWrapper();
        spriteBatch = new SpriteBatch();
        collisionManager = new CollisionManager();
        
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);

        player = new SGASPlayer(this);
        rEnemy = new RotatingEnemy(this, 200, 300, 0);

        assetWrapper.getAssetManager().finishLoading();
        
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        player.update(delta);
        rEnemy.update(delta);
        spriteBatch.end();
        collisionManager.clearResolvedCollisions();

    }

    @Override
    public void resize(int arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

}