package aigilas.skills.behaviors;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.creatures.Stats;
import aigilas.entities.Extensions;
import aigilas.entities.SkillEffect;
import aigilas.management.SpriteType;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SideEffects;
import sps.entities.Entity;

public class SkillBehavior {
    protected final SideEffects _sideEffects;
    protected final BaseSkill _parent;
    protected boolean _used = false;
    protected final Stats _cost;

    public SkillBehavior(SpriteType effectGraphic, AnimationType animation, BaseSkill parentSkill) {
        _parent = parentSkill;
        _sideEffects = new SideEffects(effectGraphic, animation, _parent);
        _cost = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public SpriteType getSpriteType() {
        return _sideEffects.getSpriteType();
    }

    public void activate(BaseCreature target) {
    }

    public void cleanup(Entity target, SkillEffect source) {
    }

    public boolean isActive() {
        return !_used;
    }

    public SkillEffect getGraphic() {
        return _sideEffects.getFirstGraphic();
    }

    public void addCost(StatType stat, float cost) {
        _cost.addBuff(new StatBuff(stat, cost));
    }

    protected boolean SubtractCost(BaseCreature owner) {
        boolean costPaid = false;
        for (StatType stat : StatType.values()) {
            if (stat != StatType.REGEN) {
                if (owner.lowerStat(stat, _cost.get(stat))) {
                    costPaid = true;
                }
            }
        }
        return costPaid;
    }

    private Entity hitTarget;
    private BaseCreature hitCreature;

    public boolean affectTarget(BaseCreature source, SkillEffect graphic)

    {
        hitTarget = source.getTargets().getCollidedTarget(graphic);
        if (null != hitTarget && hitTarget != source) {
            _parent.affect(hitTarget);
            hitCreature = Extensions.isCreature(hitTarget);
            if (hitCreature != null) {
                hitCreature.combo(_parent.getElements());
                hitCreature.react(_parent.getSkillId());
            }
            if (!_parent.isPersistent()) {
                return false;
            }
        }
        return true;
    }

    public AnimationType getAnimationType() {
        return _sideEffects.getAnimationType();
    }

    public float getCost() {
        return _cost.get(StatType.MANA);
    }
}
