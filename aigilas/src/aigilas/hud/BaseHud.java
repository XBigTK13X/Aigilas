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
    protected final List<Point2> meterPositions = new ArrayList<Point2>();
    protected final List<Point2> skillDisplayPositions = new ArrayList<Point2>();
    protected final List<Point2> inventoryPositions = new ArrayList<Point2>();
    protected final Point2 _dimensions;

    protected BaseHud(BaseCreature owner, int width, int height) {
        _parent = owner;
        if (_menuBase == null) {
            _menuBase = SpxManager.getMenuBaseAsset();
        }
        _dimensions = new Point2(width, height);

        inventoryPositions.add(new Point2(0, SpxManager.VirtualHeight / 2));
        inventoryPositions.add(new Point2(SpxManager.VirtualWidth / 2, SpxManager.VirtualHeight / 2));
        inventoryPositions.add(new Point2(0, 0));
        inventoryPositions.add(new Point2(SpxManager.VirtualWidth / 2, 0));

        meterPositions.add(new Point2(0, SpxManager.VirtualHeight - _dimensions.Y));
        meterPositions.add(new Point2(SpxManager.VirtualWidth - _dimensions.X, SpxManager.VirtualHeight - _dimensions.Y));
        meterPositions.add(new Point2(0, _dimensions.Y));
        meterPositions.add(new Point2(SpxManager.VirtualWidth - _dimensions.X, _dimensions.Y));

        skillDisplayPositions.add(new Point2(getMeterStart().X + Settings.get().spriteWidth, SpxManager.VirtualHeight - (int) (.2 * _dimensions.Y)));
        skillDisplayPositions.add(new Point2(getMeterStart().X - SpxManager.VirtualWidth / 2, SpxManager.VirtualHeight - (int) (.2 * _dimensions.Y)));
        skillDisplayPositions.add(new Point2(getMeterStart().X + Settings.get().spriteWidth, (int) (Settings.get().spriteHeight * .25)));
        skillDisplayPositions.add(new Point2(getMeterStart().X - SpxManager.VirtualWidth / 2, (int) (Settings.get().spriteHeight * .25)));
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

    protected Point2 getMeterStart() {
        return meterPositions.get(_parent.getPlayerIndex());
    }

    protected Point2 getSkillStart() {
        return skillDisplayPositions.get(_parent.getPlayerIndex());
    }

    protected Point2 getInventoryStart() {
        return inventoryPositions.get(_parent.getPlayerIndex());
    }
}
