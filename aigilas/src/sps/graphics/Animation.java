package sps.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import sps.bridge.DrawDepth;
import sps.bridge.DrawDepths;
import sps.bridge.SpriteType;
import sps.bridge.Sps;
import sps.core.Point2;
import sps.core.RNG;
import sps.core.Settings;

public class Animation {
    private int _currentFrame;
    private SpriteInfo _spriteInfo;
    private int _animationTimer;
    private Color _color = Color.WHITE;
    private Sprite _sprite;
    private DrawDepth _depth;
    private int _rotation = 0;
    private boolean animationEnabled = true;
    private boolean flipX = false;
    private boolean flipY = false;
    private float _height = -1;
    private float _width = -1;

    protected Point2 _position = new Point2(0, 0);

    public Animation() {
        _depth = DrawDepths.get(Sps.DrawDepths.Animated_Texture);
        setEdge(SpriteEdge.None);
    }

    public void setAnimationEnabled(boolean value) {
        animationEnabled = value;
    }

    public void loadContent(SpriteType assetName) {
        _spriteInfo = SpriteSheetManager.getSpriteInfo(assetName);
        _animationTimer = Sps.AnimationFps;
    }

    public void draw() {
        if (_sprite == null) {
            _sprite = Assets.get().sprite(_spriteInfo.SpriteIndex);
        }
        if (_color.a > 0) {
            _sprite.setRotation(_rotation);
            if(_width >= 0 &&_height >= 0){
                _sprite.setSize(_width,_height);
            }
            else{
                _sprite.setSize(Settings.get().spriteWidth,Settings.get().spriteHeight);
            }
            _sprite = Assets.get().sprite(_currentFrame, _spriteInfo.SpriteIndex);
            updateAnimation();
            Renderer.get().draw(_sprite, _position, _depth, _color, flipX, flipY);
        }
    }

    private void updateAnimation() {
        if (animationEnabled && _spriteInfo.MaxFrame != 1) {
            _animationTimer--;
            if (_animationTimer <= 0) {
                _currentFrame = (_currentFrame + 1) % _spriteInfo.MaxFrame;
                _animationTimer = Sps.AnimationFps;
            }
        }
    }

    public void setSpriteInfo(SpriteInfo sprite) {
        if (_spriteInfo != sprite) {
            _spriteInfo = sprite;
            _currentFrame = 0;
        }
    }

    public void setPosition(Point2 position) {
        _position.reset(position.PosX, position.PosY);
    }

    public void setColor(Color color) {
        _color = color;
    }

    public Color getColor() {
        return _color;
    }

    public void setAlpha(float alpha) {
        _color = new Color(_color.r, _color.g, _color.b, alpha);
    }

    public void setDrawDepth(DrawDepth depth) {
        _depth = depth;
    }

    public DrawDepth getDepth() {
        return _depth;
    }

    public void setRotation(int degrees) {
        _rotation = degrees;
    }

    public void setEdge(SpriteEdge edge) {
        _currentFrame = edge.Frame;
        _rotation = edge.Rotation;
    }

    public void flip(boolean x, boolean y) {
        flipX = x;
        flipY = y;
    }

    public void gotoRandomFrame(){
        gotoRandomFrame(true);
    }

    public void gotoRandomFrame(boolean disableAnimation){
        setAnimationEnabled(!disableAnimation);
        _currentFrame = RNG.next(0,_spriteInfo.MaxFrame,false);
    }

    public void setSize(float width, float height) {
        _width = width;
        _height = height;
    }
}
