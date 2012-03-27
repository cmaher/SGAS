package com.cmaher.sgas.entity;

import com.badlogic.gdx.graphics.Color;
import com.cmaher.game.FactionType;
import com.cmaher.game.GameBase;
import com.cmaher.game.components.DrawComponent;
import com.cmaher.game.components.FactionableCollisionComponent;
import com.cmaher.game.components.PlaceComponent;
import com.cmaher.game.components.RadialCollisionComponent;
import com.cmaher.game.components.RotationalComponent;
import com.cmaher.game.entity.EntityBase;
import com.cmaher.game.entity.Factionable;
import com.cmaher.sgas.SGASGame;

public class RotatingEnemy extends EntityBase implements Factionable {
    private static final String           SPRITE    = SGASGame.ASSETS
                                                            + "player.png";
    private static final float            RADIUS    = 32f;
    private static final Color            COLOR     = new Color(255, 192, 203,
                                                            255);
    private static final float            ANGULAR_V = 30f;

    private PlaceComponent                place;
    private RotationalComponent           rotational;
    private FactionableCollisionComponent collision;
    private DrawComponent                 draw;

    public RotatingEnemy(GameBase game) {
        super(game);
        init(0f, 0f, 0f);
    }

    public RotatingEnemy(GameBase game, float x, float y, float angle) {
        super(game);
        init(x, y, angle);
    }

    private void init(float x, float y, float angle) {
        place = new PlaceComponent(this, x, y, RADIUS * 2, RADIUS * 2);
        place.setAngle(angle);
        rotational = new RotationalComponent(this, place, ANGULAR_V);
        collision = new FactionableCollisionComponent(this, place,
                FactionType.Enemy, FactionType.Player, FactionType.PlayerBullet);

        draw = new DrawComponent(this, place, SPRITE);
        draw.setTint(COLOR);

        game.getAssetWrapper().addTextureAsset(SPRITE);
    }

    public void update(float delta) {
        rotational.update(delta);
        collision.update(delta);
        draw.draw();
    }

    @Override
    public void collideUnfriendly(Factionable uf) {
        uf.collideSolid(collision);
    }

    @Override
    public void collideUnfriendlyBullet(Factionable ufBullet) {
        // TODO Auto-generated method stub

    }

    @Override
    public void collideSolid(RadialCollisionComponent rcc) {
        // TODO Auto-generated method stub

    }

}
