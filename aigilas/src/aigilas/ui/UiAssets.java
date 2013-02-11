package aigilas.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import sps.core.Loader;
import sps.graphics.Assets;

public class UiAssets {
    private static TextureRegion buttonBg = new TextureRegion(new Texture(Loader.get().graphics("button_bg.png").getAbsolutePath()));

    public static TextureRegionDrawable getNewBtnBg() {
        return new TextureRegionDrawable(buttonBg);
    }

    private static TextureRegion cursor = new TextureRegion(new Texture(Loader.get().graphics("cursor.png").getAbsolutePath()));

    public static TextureRegionDrawable getNewCursor() {
        return new TextureRegionDrawable(cursor);
    }

    private static TextButton.TextButtonStyle btnStyle;

    public static TextButton.TextButtonStyle getButtonStyle() {
        if (btnStyle == null) {
            btnStyle = new TextButton.TextButtonStyle();
            btnStyle.font = Assets.get().font();

            btnStyle.overFontColor = Color.YELLOW;
            btnStyle.downFontColor = Color.GREEN;
            btnStyle.fontColor = Color.WHITE;
            btnStyle.down = UiAssets.getNewBtnBg();
            btnStyle.up = UiAssets.getNewBtnBg();
            btnStyle.over = UiAssets.getNewBtnBg();
        }
        return btnStyle;
    }

    private static Label.LabelStyle lblStyle;
    public static Label.LabelStyle getLabelStyle(){
        if(lblStyle == null){
            lblStyle = new Label.LabelStyle(Assets.get().font(), Color.WHITE);
        }
        return lblStyle;
    }
}
