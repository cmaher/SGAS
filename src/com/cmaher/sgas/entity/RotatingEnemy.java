package com.cmaher.sgas.entity;

import com.badlogic.gdx.graphics.Color;
import com.cmaher.game.components.DrawComponent;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.components.RotationalComponent;
import com.cmaher.sgas.SGASGame;
import com.cmaher.sgas.components.EnemyCollisionComponent;

public class RotatingEnemy extends Enemy {
    private static final String     SPRITE    = SGASGame.ASSETS + "player.png";
    private static final float      RADIUS    = 32f;
    private static final Color      COLOR     = new Color(255, 192, 203, 255);
    private static final float      ANGULAR_V = 30f;

    private PlaceComponent          place;
    private RotationalComponent     rotational;
    private EnemyCollisionComponent collision;
    private DrawComponent           draw;

    public RotatingEnemy(SGASGame game) {
        super(game);
        init(0f, 0f, 0f);
    }

    public RotatingEnemy(SGASGame game, float x, float y, float angle) {
        super(game);
        init(x, y, angle);
    }

    private void init(float x, float y, float angle) {
        place = new PlaceComponent(this, x, y, RADIUS * 2, RADIUS * 2);
        place.setAngle(angle);
        rotational = new RotationalComponent(this, place, ANGULAR_V);
        collision = new EnemyCollisionComponent(this, place);

        draw = new DrawComponent(this, place, SPRITE);
        draw.setTint(COLOR);

        game.assetWrapper.addTextureAsset(SPRITE);
    }

    public void collidePlayer(Player player) {
        player.hitSolid(collision);

    }

    public void update(float delta) {
        rotational.update(delta);
        draw.draw();
    }

    @Override
    public void collidePlayerBullet(StraightBullet bullet) {
        // TODO Auto-generated method stub

    }

}
