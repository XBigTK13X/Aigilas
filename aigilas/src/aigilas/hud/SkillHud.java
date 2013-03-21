package aigilas.hud;

import aigilas.Aigilas;
import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import com.badlogic.gdx.graphics.Color;
import sps.bridge.Commands;
import sps.bridge.DrawDepths;
import sps.core.Point2;
import sps.core.SpsConfig;
import sps.graphics.Renderer;
import sps.text.Text;
import sps.text.TextEffects;
import sps.text.TextPool;
import sps.util.MathHelper;

public class SkillHud extends BaseHud {
    private static final String __separator = "|";
    private Text skillHeader;

    private Point2 _energyPosition = new Point2();

    public SkillHud(BaseCreature owner) {
        super(owner, SpsConfig.get().spriteWidth, Renderer.get().VirtualHeight / 4);
        _energyPosition = new Point2(getMeterAnchor().X, getMeterAnchor().Y - Renderer.get().VirtualHeight / 4);
    }

    private int calculateHeight(StatType statType) {
        return MathHelper.clamp((((float) _parent.get(statType) / _parent.getMax(statType))) * _dimensions.Y, 0, (int) _dimensions.Y);
    }

    private int costOfCurrentSkill() {
        return (int) (_parent.getCurrentSkillCost() / (float) _parent.getMax(StatType.Energy) * _dimensions.Y);
    }

    private String getSkillStrings() {
        return "S:" + _parent.getActiveSkillName() + __separator + "Z:" + _parent.getHotSkillName(Commands.get(Aigilas.Commands.Hot_Skill_1)) + __separator + "X:" + _parent.getHotSkillName(Commands.get(Aigilas.Commands.Hot_Skill_2)) + __separator + "C:" + _parent.getHotSkillName(Commands.get(Aigilas.Commands.Hot_Skill_3)) + __separator;
    }

    public void update() {
        if (_isVisible) {
            if (skillHeader != null) {
                skillHeader.hide();
            }
            skillHeader = TextPool.get().write(getSkillStrings(), getSkillAnchor().add(SpsConfig.get().spriteWidth, 0), Text.NotTimed, TextEffects.None, Color.WHITE, .6f);
        }
    }

    @Override
    public void draw() {
        if (!_isVisible) {
            if (skillHeader != null) {
                skillHeader.hide();
            }
            return;
        }

        Renderer.get().draw(_menuBase, getMeterAnchor(), DrawDepths.get(Aigilas.DrawDepths.Hud_BG), Color.GREEN, SpsConfig.get().spriteWidth, calculateHeight(StatType.Health));
        Renderer.get().draw(_menuBase, _energyPosition, DrawDepths.get(Aigilas.DrawDepths.Hud_BG), Color.BLUE, SpsConfig.get().spriteWidth, calculateHeight(StatType.Energy));
        Renderer.get().draw(_menuBase, _energyPosition, DrawDepths.get(Aigilas.DrawDepths.Hud_BG), Color.YELLOW, SpsConfig.get().spriteWidth / 2, costOfCurrentSkill());
    }
}
