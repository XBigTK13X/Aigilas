package aigilas.hud;

import aigilas.creatures.ICreature;
import aigilas.items.GenericItem;
import aigilas.management.Commands;
import spx.core.SpxManager;
import spx.io.Input;

import java.util.Arrays;
import java.util.List;

public class HotkeyHud extends IHud {
    public HotkeyHud(ICreature owner) {
        super(owner, SpxManager.WindowWidth / 2, SpxManager.WindowHeight / 2);
        _isVisible = true;
    }

    public void draw() {
    }

    private static List<Commands> _hotSkills = Arrays.asList(Commands.HotSkill1, Commands.HotSkill2, Commands.HotSkill3);

    public void update(GenericItem item, boolean refresh) {
        if (Input.isActive(Commands.LockSkill, _parent.getPlayerIndex(), false)) {
            for (Commands hotSkill : _hotSkills) {
                if (Input.isActive(hotSkill, _parent.getPlayerIndex(), false)) {
                    _parent.markHotSkill(hotSkill);
                }
            }
        }
        ;
    }
}
