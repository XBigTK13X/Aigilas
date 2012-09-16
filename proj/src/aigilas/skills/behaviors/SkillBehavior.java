package aigilas.skills.behaviors;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.creatures.Stats;
import aigilas.entities.Extensions;
import aigilas.entities.SkillEffect;
import aigilas.management.SpriteType;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SideEffects;
import spx.entities.Entity;
import spx.entities.IEntity;

public class SkillBehavior {
    protected SideEffects _sideEffects;
    protected ISkill _parent;
    protected boolean _used = false;
    protected Stats _cost;

    public SkillBehavior(SpriteType effectGraphic, AnimationType animation, ISkill parentSkill) {
        _parent = parentSkill;
        _sideEffects = new SideEffects(effectGraphic, animation, _parent);
        _cost = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public SpriteType GetSpriteType() {
        return _sideEffects.GetSpriteType();
    }

    public void Activate(ICreature target) {
    }

    public void Cleanup(Entity target, SkillEffect source) {
    }

    public boolean IsActive() {
        return !_used;
    }

    public SkillEffect GetGraphic() {
        return _sideEffects.GetFirstGraphic();
    }

    public void AddCost(StatType stat, float cost) {
        _cost.AddBuff(new StatBuff(stat, cost));
    }

    protected boolean SubtractCost(ICreature owner) {
        boolean costPaid = false;
        for (StatType stat : StatType.values()) {
            if (stat != StatType.REGEN) {
                if (owner.LowerStat(stat, _cost.Get(stat))) {
                    costPaid = true;
                }
            }
        }
        return costPaid;
    }

    private IEntity hitTarget;
    private ICreature hitCreature;

    public boolean AffectTarget(ICreature source, SkillEffect graphic)

    {
        hitTarget = source.GetTargets().GetCollidedTarget(graphic);
        if (null != hitTarget && hitTarget != source) {
            _parent.Affect(hitTarget);
            hitCreature = Extensions.IsCreature(hitTarget);
            if (hitCreature != null) {
                hitCreature.Combo(_parent.GetElements());
                hitCreature.React(_parent.GetSkillId());
            }
            if (!_parent.IsPersistent()) {
                return false;
            }
        }
        return true;
    }

    public AnimationType GetAnimationType() {
        return _sideEffects.GetAnimationType();
    }

    public float GetCost() {
        return _cost.Get(StatType.MANA);
    }
}
