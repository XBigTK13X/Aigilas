package spx.sprites;import spx.bridge.DrawDepth;import spx.core.Point2;import spx.core.Settings;import spx.core.SpxManager;import com.badlogic.gdx.Gdx;import com.badlogic.gdx.graphics.Color;import com.badlogic.gdx.graphics.GL10;import com.badlogic.gdx.graphics.OrthographicCamera;import com.badlogic.gdx.graphics.Texture;import com.badlogic.gdx.graphics.g2d.BitmapFont;import com.badlogic.gdx.graphics.g2d.Sprite;import com.badlogic.gdx.graphics.g2d.SpriteBatch;public class Renderer {	private SpriteBatch batch;	private OrthographicCamera camera;	private BitmapFont font;	private static final Color __defaultFilter = Color.WHITE;	private static final float __defaultAlpha = 1f;	public Renderer() {		camera = new OrthographicCamera();		camera.setToOrtho(false, SpxManager.WindowWidth, SpxManager.WindowHeight);		batch = new SpriteBatch();		font = new BitmapFont();	}	public void Begin() {		Gdx.gl.glClearColor(0, 0, 0, 1);		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);		camera.update();		batch.setProjectionMatrix(camera.combined);		batch.begin();	}	public void End() {		batch.end();	}	// Texture rendering	public void Draw(Texture image, Point2 location, DrawDepth depth, Color filter) {		Render(image, location, filter, depth, __defaultAlpha);	}	public void Draw(Texture image, Point2 location, DrawDepth depth, float alpha) {		Render(image, location, __defaultFilter, depth, alpha);	}	public void Draw(Texture image, Point2 location, DrawDepth depth) {		Render(image, location, __defaultFilter, depth, __defaultAlpha);	}	public void Draw(Texture image, Point2 location) {		Render(image, location, __defaultFilter, DrawDepth.Default, __defaultAlpha);	}	public void Draw(Texture texture, Point2 position, DrawDepth depth, Color filter, int scaleX, int scaleY) {		batch.draw(texture, position.PosX, position.PosY, 0, 0, scaleX, scaleY);	}	private void Render(Texture image, Point2 location, Color filter, DrawDepth depth, float alpha) {		batch.setColor(filter);		batch.draw(image, location.PosX, location.PosY);		batch.setColor(0, 0, 0, 1);	}	// Sprite rendering	public void Draw(Sprite sprite, Point2 position, DrawDepth depth, Color color) {		Render(sprite, position, depth, color, Settings.Get().spriteWidth, Settings.Get().spriteHeight);	}	public void Draw(Sprite sprite, Point2 position, DrawDepth depth, Color color, float scaleX, float scaleY) {		Render(sprite, position, depth, color, scaleX, scaleY);	}	private void Render(Sprite sprite, Point2 position, DrawDepth depth, Color color, float scaleX, float scaleY) {		sprite.setColor(color);		sprite.setSize(scaleX, scaleY);		sprite.setPosition(position.X, position.Y);		sprite.draw(batch);	}	// String rendering	public void DrawString(String content, Point2 location, Color filter, float scale, DrawDepth depth) {		RenderString(content, location, filter, scale, depth);	}	private void RenderString(String content, Point2 location, Color filter, float scale, DrawDepth depth) {		font.setScale(scale);		font.setColor(filter);		CharSequence chars = content;		font.draw(batch, chars, location.X, location.Y);	}}