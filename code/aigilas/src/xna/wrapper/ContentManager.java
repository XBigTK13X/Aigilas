package xna.wrapper; import com.badlogic.gdx.graphics.Color;import java.util.HashMap;public class ContentManager {	static public void Load() {	}	public String RootDirectory;	private final HashMap<String, Integer> spriteIndices = new HashMap<>();	private SpriteSheet _spriteSheet;	public Texture LoadTexture(String resourceName) {		return AssetManager.Get().GetImage(resourceName);	}	public Texture LoadSprite(int verticalIndex) {		if (_spriteSheet == null) {			_spriteSheet = AssetManager.Get().GetSpriteSheet();		}		return _spriteSheet.getSprite(0, verticalIndex);	}	public SpriteFont LoadSpriteFont(String resourceName) {		// TODO Auto-generated method stub		return null;	}}