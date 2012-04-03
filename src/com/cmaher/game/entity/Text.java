package com.cmaher.game.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.cmaher.game.GameBase;
import com.cmaher.game.components.PlaceComponent;

public class Text extends EntityBase {
    private BitmapFont     font;
    private String         text;
    private PlaceComponent place;

    public Text(GameBase game, float x, float y) {
        super(game);
        font = new BitmapFont();
        place = new PlaceComponent(this);
        place.setX(x);
        place.setY(y);
    }

    @Override
    public void update(float delta) {
        game.getSpriteBatch().setColor(Color.WHITE);
        font.draw(game.getSpriteBatch(), text, place.getX(), place.getY());
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PlaceComponent getPlace() {
        return place;
    }

    public void setPlace(PlaceComponent place) {
        this.place = place;
    }

}
