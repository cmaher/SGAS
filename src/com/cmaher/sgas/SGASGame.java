package com.cmaher.sgas;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.cmaher.game.GameBase;
import com.cmaher.game.asset.AssetWrapper;
import com.cmaher.game.collision.CollisionManager;
import com.cmaher.sgas.entity.RotatingEnemy;
import com.cmaher.sgas.entity.SGASPlayer;

public class SGASGame implements GameBase {
    public static final String  ASSETS     = "assets/"; // NOTE: "assets\" fails
    private static final float  SHOOT_LOW  = 1.5f;
    private static final float  SHOOT_HIGH = 3.0f;

    private AssetWrapper        assetWrapper;
    private SpriteBatch         spriteBatch;
    private CollisionManager    collisionManager;

    private SGASPlayer          player;
    private List<RotatingEnemy> enemies;
    private int                 score      = 0;

    @Override
    public void create() {
        assetWrapper = new AssetWrapper();
        spriteBatch = new SpriteBatch();
        collisionManager = new CollisionManager();

        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);

        player = new SGASPlayer(this, Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2);
        createEnemies();

        assetWrapper.getAssetManager().finishLoading();

    }

    private void createEnemies() {
        float diameter = 2 * RotatingEnemy.RADIUS;
        enemies = new ArrayList<RotatingEnemy>();

        // top and bottom enemies
        for (int i = 0; i < 6; i ++) {
            RotatingEnemy eBottom = new RotatingEnemy(this, 4 * i * diameter, 0,
                    MathUtils.random(360));
            RotatingEnemy eTop = new RotatingEnemy(this, 4 * i * diameter,
                    Gdx.graphics.getHeight() - diameter, MathUtils.random(360));

            enemies.add(eBottom);
            enemies.add(eTop);
        }

        // side enemies
        for (int i = 1; i < 4; i ++) {
            RotatingEnemy eLeft = new RotatingEnemy(this, 0, 3* i * diameter,
                    MathUtils.random(360));
            RotatingEnemy eRight = new RotatingEnemy(this,
                    Gdx.graphics.getWidth() - diameter, 3* i * diameter,
                    MathUtils.random(360));

            enemies.add(eLeft);
            enemies.add(eRight);
        }
        
        for(RotatingEnemy e : enemies) {
            e.randomizeShootWait(SHOOT_LOW, SHOOT_HIGH);
        }
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

        if (player.isAlive()) {
            float delta = Gdx.graphics.getDeltaTime();
            Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

            spriteBatch.begin();

            player.update(delta);
            for (RotatingEnemy e : enemies) {
                e.update(delta);
            }

            spriteBatch.end();

            collisionManager.clearResolvedCollisions();
        } else {
            System.out.println("Game Over -- Score: " + getScore());
            System.exit(0);
        }
    }

    @Override
    public void resize(int arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public AssetWrapper getAssetWrapper() {
        return assetWrapper;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }
}