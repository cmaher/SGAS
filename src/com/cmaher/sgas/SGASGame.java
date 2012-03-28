package com.cmaher.sgas;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cmaher.game.GameBase;
import com.cmaher.game.asset.AssetWrapper;
import com.cmaher.game.collision.CollisionManager;
import com.cmaher.game.entity.Text;
import com.cmaher.game.properties.GameProperties;
import com.cmaher.sgas.entity.RotatingEnemy;
import com.cmaher.sgas.entity.SGASPlayer;

public class SGASGame implements GameBase {
    public static final String  ASSETS     = "assets/";   // NOTE: "assets\"
                                                           // fails
    private static final float  SHOOT_LOW  = 1.5f;
    private static final float  SHOOT_HIGH = 2.0f;

    private static final String PROPS_FILE = "game.props";
    private static final String P_ENEMY    = "enemy";
    private static final String P_PLAYER   = "player";

    private AssetWrapper        assetWrapper;
    private SpriteBatch         spriteBatch;
    private CollisionManager    collisionManager;
    private GameProperties      gameProps;

    private SGASPlayer          player;
    private List<RotatingEnemy> enemies;
    private Text                text;
    private Text                fpsText;
    private int                 score;
    private int                 topScore;
    private int                 highFPS    = 0;
    private int                 lowFPS     = 999;

    @Override
    public void create() {
        assetWrapper = new AssetWrapper();
        spriteBatch = new SpriteBatch();
        collisionManager = new CollisionManager();

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);

        player = new SGASPlayer(this, P_PLAYER, Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2);
        createEnemies();
        text = new Text(this, 0, 20);
        fpsText = new Text(this, 0, Gdx.graphics.getHeight());

        assetWrapper.getAssetManager().finishLoading();
        gameProps = new GameProperties(PROPS_FILE);

        score = 0;
        topScore = 0;
    }

    private void createEnemies() {
        float diameter = 2 * RotatingEnemy.RADIUS;
        enemies = new ArrayList<RotatingEnemy>();

        // top and bottom enemies
        for (int i = 0; i < 6; i++) {
            float x = 2.88f * i * diameter;

            RotatingEnemy eBottom = new RotatingEnemy(this, P_ENEMY, x, 0);
            RotatingEnemy eTop = new RotatingEnemy(this, P_ENEMY, x,
                    Gdx.graphics.getHeight() - diameter);

            enemies.add(eBottom);
            enemies.add(eTop);
        }

        // side enemies
        for (int i = 1; i < 3; i++) {
            float y = 3 * i * diameter;
            RotatingEnemy eLeft = new RotatingEnemy(this, P_ENEMY, 0, y);
            RotatingEnemy eRight = new RotatingEnemy(this, P_ENEMY,
                    Gdx.graphics.getWidth() - diameter, y);

            enemies.add(eLeft);
            enemies.add(eRight);
        }

        for (RotatingEnemy e : enemies) {
            e.randomize(SHOOT_LOW, SHOOT_HIGH);
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
        float delta = Gdx.graphics.getDeltaTime();
        boolean reset = false;
        int fps = Gdx.graphics.getFramesPerSecond();
        if (fps > highFPS) {
            highFPS = fps;
        } else if (fps < lowFPS && fps != 0) {
            lowFPS = fps;
        }

        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();

        if (player.isAlive()) {
            player.update(delta);
            for (RotatingEnemy e : enemies) {
                e.update(delta);
            }
            collisionManager.clearResolvedCollisions();
        } else {
            Text gameover = new Text(this, Gdx.graphics.getWidth() * 1f / 3f,
                    Gdx.graphics.getHeight() * 2f / 3f);
            gameover.setText("Game Over -- Top Score: " + topScore
                    + " (space to reset)");
            gameover.update(delta);
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                this.dispose();
                reset = true;
                this.create();
            }
        }

        if (!reset) {
            text.setText("Score: " + getScore());
            text.update(delta);

            fpsText.setText("FPS: " + fps);
            fpsText.update(delta);

            spriteBatch.end();
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
        if (this.score >= topScore) {
            topScore = this.score;
        }
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

    public GameProperties getGameProperties() {
        return gameProps;
    }
}