package sps.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import sps.bridge.DrawDepth;
import sps.bridge.DrawDepths;
import sps.bridge.SpriteType;
import sps.bridge.Sps;
import sps.core.Point2;
import sps.core.RNG;
import sps.core.SpsConfig;

public class Animation {
    protected Point2 _position = new Point2(0, 0);
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
    private float flashes = 30;
    private float flashCount = flashes + 1;
    private Color flashColor = Color.BLUE;
    private int alternateCount = 7;
    private boolean alternate;
    private SpriteEdge _edge = null;
    private boolean _dynamicEdges = false;

    public Animation() {
        _depth = DrawDepths.get(Sps.DrawDepths.Animated_Texture);
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
            if (_width >= 0 && _height >= 0) {
                _sprite.setSize(_width, _height);
            }
            else {
                _sprite.setSize(SpsConfig.get().spriteWidth, SpsConfig.get().spriteHeight);
            }
            _sprite = Assets.get().sprite(_currentFrame, _spriteInfo.SpriteIndex);
            updateAnimation();

            if (flashCount < flashes) {
                if (flashCount % alternateCount == 1) {
                    alternate = !alternate;
                }
                flashCount++;
                if (flashCount >= flashes) {
                    alternate = false;
                }
            }
            Color renderColor = (alternate) ? _color.tmp().mul(flashColor) : _color;
            Renderer.get().draw(_sprite, _position, _depth, renderColor, flipX, flipY);
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

    public Color getColor() {
        return _color;
    }

    public void setColor(Color color) {
        _color = color;
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
        _edge = edge;
    }

    public void flip(boolean x, boolean y) {
        flipX = x;
        flipY = y;
    }

    public void gotoRandomFrame() {
        gotoRandomFrame(true);
    }

    public void gotoRandomFrame(boolean disableAnimation) {
        setAnimationEnabled(!disableAnimation);
        _currentFrame = RNG.next(0, _spriteInfo.MaxFrame, false);
    }

    public void setSize(float width, float height) {
        _width = width;
        _height = height;
    }

    public void flash(Color attackColor) {
        flashCount = 0;
        flashColor = attackColor;
    }

    public SpriteEdge getSpriteEdge() {
        return _edge;
    }

    public void setDynamicEdges(boolean value) {
        _dynamicEdges = value;
    }

    public boolean hasDynamicEdges() {
        return _dynamicEdges;
    }
}
