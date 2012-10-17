package aigilas.hud;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.management.Commands;
import com.badlogic.gdx.graphics.Color;
import sps.bridge.DrawDepth;
import sps.core.Point2;
import sps.core.Settings;
import sps.core.SpxManager;

public class SkillHud extends BaseHud {
    private static final String __separator = "|";

    private Point2 _manaPosition = new Point2();
    private Point2 pos = new Point2();

    public SkillHud(BaseCreature owner) {
        super(owner, Settings.get().spriteWidth, SpxManager.VirtualHeight / 4);
        _manaPosition = new Point2(getHudOrigin().X, getHudOrigin().Y - SpxManager.VirtualHeight / 4);
        switch (_parent.getPlayerIndex()) {
            case 0:
                pos = new Point2(getHudOrigin().X + Settings.get().spriteWidth, Settings.get().spriteHeight);
                break;
            case 1:
                pos = new Point2(getHudOrigin().X - SpxManager.VirtualWidth / 2, Settings.get().spriteHeight);
                break;
            case 2:
                pos = new Point2(getHudOrigin().X + Settings.get().spriteWidth, SpxManager.VirtualHeight);
                break;
            case 3:
                pos = new Point2(getHudOrigin().X - SpxManager.VirtualWidth / 2, SpxManager.VirtualHeight);
                break;
        }
    }

    private int calculateHeight(StatType statType) {
        return (int) ((_parent.get(statType) / _parent.getMax(statType)) * _dimensions.Y);
    }

    private int costOfCurrentSkill() {
        return (int) (_parent.getCurrentSkillCost() / _parent.getMax(StatType.Mana) * _dimensions.Y);
    }

    private String getSkillStrings() {
        return "A:" + _parent.getActiveSkillName() + __separator + "X:" + _parent.getHotSkillName(Commands.HotSkill1) + __separator + "Y:" + _parent.getHotSkillName(Commands.HotSkill2) + __separator + "B:" + _parent.getHotSkillName(Commands.HotSkill3) + __separator;
    }

    public void update() {
        if (_isVisible) {
            _textHandler.update();
            _textHandler.clear();
            _textHandler.writeDefault(getSkillStrings(), (int) pos.X, (int) pos.Y, null);
        }
    }

    public void draw() {
        if (!_isVisible) {
            return;
        }

        SpxManager.Renderer.draw(_menuBase, getHudOrigin(), DrawDepth.HudBG, Color.GREEN, Settings.get().spriteWidth, calculateHeight(StatType.Health));
        SpxManager.Renderer.draw(_menuBase, _manaPosition, DrawDepth.HudBG, Color.BLUE, Settings.get().spriteWidth, calculateHeight(StatType.Mana));
        SpxManager.Renderer.draw(_menuBase, _manaPosition, DrawDepth.HudBG, Color.YELLOW, Settings.get().spriteWidth / 2, costOfCurrentSkill());

        _textHandler.draw();
    }
}
