package aigilas.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UiAssets {
    private static TextureRegion buttonBg = new TextureRegion(new Texture("assets/graphics/button_bg.png"));
    public static TextureRegionDrawable getNewBtnBg(){
        return new TextureRegionDrawable(buttonBg);
    }
}
