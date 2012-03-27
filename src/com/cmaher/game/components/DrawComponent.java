package com.cmaher.game.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.cmaher.game.entity.Entity;

public class DrawComponent extends Component {

    private PlaceComponent place;
    private Texture        texture;
    private TextureRegion  textureRegion;
    private Sprite         sprite;
    private Color          tint;
    private String         assetStr;

    public DrawComponent(Entity master, PlaceComponent place, String assetStr) {
        super(master);
        this.place = place;
        this.assetStr = assetStr;

        texture = master.game.assetWrapper.getTexture(assetStr);

        init(0, 0, texture.getWidth(), texture.getHeight());
    }

    private void init(int srcX, int srcY, int srcWidth, int srcHeight) {
        textureRegion = new TextureRegion(texture, srcX, srcY, srcWidth,
                srcHeight);
        sprite = new Sprite(textureRegion);
        tint = Color.WHITE;
    }

    public void draw() {
        sprite.setPosition(place.getX(), place.getY());
        sprite.setRotation(place.getAngle());
        sprite.setRegion(textureRegion);
        sprite.setColor(tint);
        sprite.draw(master.game.spriteBatch);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
        textureRegion.setTexture(texture);
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public Color getTint() {
        return tint;
    }

    public void setTint(Color tint) {
        this.tint = tint;
    }

    public String getAssetStr() {
        return assetStr;
    }
}
