package aigilas.hud;

import aigilas.creatures.BaseCreature;
import com.badlogic.gdx.graphics.g2d.Sprite;
import sps.core.Point2;
import sps.core.Settings;
import sps.graphics.Assets;
import sps.graphics.Renderer;
import sps.text.TextHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseHud {
    protected boolean _isVisible = false;
    protected final BaseCreature _parent;
    protected static Sprite _menuBase;
    protected final TextHandler _textHandler = new TextHandler();
    protected final List<Point2> meterAnchors = new ArrayList<Point2>();
    protected final List<Point2> skillAnchors = new ArrayList<Point2>();
    protected final List<Point2> inventoryAnchors = new ArrayList<Point2>();
    protected final Point2 _dimensions;

    protected BaseHud(BaseCreature owner, int width, int height) {
        _parent = owner;
        if (_menuBase == null) {
            _menuBase = Assets.get().baseMenu();
        }
        _dimensions = new Point2(width, height);

        inventoryAnchors.add(new Point2(0, Renderer.VirtualHeight / 2));
        inventoryAnchors.add(new Point2(Renderer.VirtualWidth / 2, Renderer.VirtualHeight / 2));
        inventoryAnchors.add(new Point2(0, 0));
        inventoryAnchors.add(new Point2(Renderer.VirtualWidth / 2, 0));

        meterAnchors.add(new Point2(0, Renderer.VirtualHeight - _dimensions.Y));
        meterAnchors.add(new Point2(Renderer.VirtualWidth - _dimensions.X, Renderer.VirtualHeight - _dimensions.Y));
        meterAnchors.add(new Point2(0, _dimensions.Y));
        meterAnchors.add(new Point2(Renderer.VirtualWidth - _dimensions.X, _dimensions.Y));

        skillAnchors.add(new Point2(getMeterAnchor().X + Settings.get().spriteWidth, Renderer.VirtualHeight - (int) (.2 * _dimensions.Y)));
        skillAnchors.add(new Point2(getMeterAnchor().X - Renderer.VirtualWidth / 2, Renderer.VirtualHeight - (int) (.2 * _dimensions.Y)));
        skillAnchors.add(new Point2(getMeterAnchor().X + Settings.get().spriteWidth, (int) (Settings.get().spriteHeight * .25)));
        skillAnchors.add(new Point2(getMeterAnchor().X - Renderer.VirtualWidth / 2, (int) (Settings.get().spriteHeight * .25)));
    }

    public void toggle() {
        _isVisible = !_isVisible;
    }

    public boolean isVisible() {
        return _isVisible;
    }

    public void loadContent() {
        _menuBase = Assets.get().baseMenu();
    }

    protected Point2 getMeterAnchor() {
        return meterAnchors.get(_parent.getPlayerIndex());
    }

    protected Point2 getSkillAnchor() {
        return skillAnchors.get(_parent.getPlayerIndex());
    }

    protected Point2 getInventoryAnchor() {
        return inventoryAnchors.get(_parent.getPlayerIndex());
    }

    public abstract void draw();
}
