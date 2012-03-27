package com.cmaher.game.asset;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetWrapper {
    private AssetManager assetManager;
    

    public AssetWrapper() {
        assetManager = new AssetManager();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void addTextureAsset(String assetStr) {
        assetManager.load(assetStr, Texture.class);
    }

    private void checkAndLoadTexture(String assetStr) {
        if (!assetManager.isLoaded(assetStr)) {
            addTextureAsset(assetStr);
        }
        assetManager.finishLoading();
    }

    public Texture getTexture(String assetStr) {
        checkAndLoadTexture(assetStr);
        return assetManager.get(assetStr, Texture.class);
    }
}
