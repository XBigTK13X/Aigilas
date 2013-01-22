package sps.util;

import com.badlogic.gdx.graphics.Color;

public class Colors {
    private static float base = 255f;
    public static Color rgb(int r,int g,int b){
        return new Color(r/base,g/base,b/base,1f);
    }
}
