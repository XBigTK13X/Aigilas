package aigilas.hud;

import aigilas.creatures.BaseCreature;
import com.badlogic.gdx.graphics.g2d.Sprite;
import sps.core.Point2;
import sps.core.Settings;
import sps.core.SpxManager;
import sps.text.TextHandler;

import java.util.ArrayList;
import java.util.List;

public class BaseHud {
    protected boolean _isVisible = false;
    protected final BaseCreature _parent;
    protected static Sprite _menuBase;
    protected final TextHandler _textHandler = new TextHandler();
    protected final List<Point2> playerHudPositions = new ArrayList<Point2>();
    protected final List<Point2> invertedPlayerHudPositions = new ArrayList<Point2>();
    protected final Point2 _dimensions;

    protected BaseHud(BaseCreature owner, int width, int height) {
        _parent = owner;
        if (_menuBase == null) {
            _menuBase = SpxManager.getMenuBaseAsset();
        }
        _dimensions = new Point2(width, height);
        playerHudPositions.add(new Point2(0, SpxManager.VirtualHeight - _dimensions.Y));
        playerHudPositions.add(new Point2(SpxManager.VirtualWidth - _dimensions.X, SpxManager.VirtualHeight - _dimensions.Y));
        playerHudPositions.add(new Point2(0, _dimensions.Y));
        playerHudPositions.add(new Point2(SpxManager.VirtualWidth - _dimensions.X, _dimensions.Y));

        invertedPlayerHudPositions.add(new Point2(getStart().X + Settings.get().spriteWidth, SpxManager.VirtualHeight - _dimensions.Y));
        invertedPlayerHudPositions.add(new Point2(getStart().X - SpxManager.VirtualWidth / 2, SpxManager.VirtualHeight));
        invertedPlayerHudPositions.add(new Point2(getStart().X + Settings.get().spriteWidth, Settings.get().spriteHeight));
        invertedPlayerHudPositions.add(new Point2(getStart().X - SpxManager.VirtualWidth / 2, Settings.get().spriteHeight));

    }

    public void toggle() {
        _isVisible = !_isVisible;
    }

    public boolean isVisible() {
        return _isVisible;
    }

    public void loadContent() {
        _menuBase = SpxManager.getMenuBaseAsset();
    }

    protected Point2 getStart() {
        return playerHudPositions.get(_parent.getPlayerIndex());
    }

    protected Point2 getStartI() {
        return invertedPlayerHudPositions.get(_parent.getPlayerIndex());
    }
}
