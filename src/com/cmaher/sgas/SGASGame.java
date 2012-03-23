package com.cmaher.sgas;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cmaher.sgas.entities.Player;

public class SGASGame implements ApplicationListener {
    public static final String ASSETS = "assets/"; // NOTE: "assets\" fails
    public static final int    WIDTH  = 800;
    public static final int    HEIGHT = 600;

    public AssetWrapper        assetWrapper;
    public SpriteBatch         spriteBatch;

    private Player             player;

    @Override
    public void create() {
        assetWrapper = new AssetWrapper();
        spriteBatch = new SpriteBatch();
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);

        player = new Player(this);

        assetWrapper.addTextureAsset(Player.SPRITE);
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
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        player.update(Gdx.graphics.getDeltaTime());
        spriteBatch.end();

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