package aigilas.skills;

import aigilas.Common;
import aigilas.creatures.BaseCreature;
import aigilas.entities.Elements;
import aigilas.entities.Extensions;
import aigilas.entities.SkillEffect;
import aigilas.skills.behaviors.SkillBehavior;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;
import sps.bridge.SpriteTypes;
import sps.entities.Entity;
import sps.entities.EntityManager;

public abstract class BaseSkill {
    protected BaseCreature _source;
    protected final SkillId _id;
    protected final SkillBehavior _behavior;
    protected final SkillComponents _components;

    protected BaseSkill(SkillId implementationId, AnimationType animation, float strength, boolean isPersistent) {
        _id = implementationId;
        _components = new SkillComponents(strength, isPersistent);
        _behavior = SkillFactory.create(animation, SpriteTypes.get(Common.Skill_Effect), this);
        for (Elements element : _id.Info.Elements) {
            _components.addElements(element);
        }
        _behavior.addCost(_id.Info.Stat, _id.Info.Cost);
    }

    protected BaseSkill(SkillId implementationId, AnimationType animation, float strength) {
        this(implementationId, animation, strength, false);
    }

    protected BaseSkill(SkillId implementationId, AnimationType animation) {
        this(implementationId, animation, SkillEffect.DefaultStrength, false);
    }

    public void activate(BaseCreature source) {
    }

    public void prepForActivation(BaseCreature source) {
        _source = source;
        _behavior.activate(_source);
    }

    public void affect(Entity target) {
        BaseCreature creature = Extensions.isCreature(target);
        if (creature != null) {
            affect(creature);
        }
    }

    public void applyToPlayers(Status statusId) {
        for (Entity player : EntityManager.get().getPlayers()) {
            StatusFactory.apply((BaseCreature) player, statusId);
        }
    }

    public void affect(BaseCreature target) {

    }

    public SkillComponents components() {
        return _components;
    }

    public SkillBehavior behavior() {
        return _behavior;
    }

    public void cleanup(Entity target, SkillEffect source) {
    }

    public SkillId getSkillId() {
        return _id;
    }

    public boolean isAffordableTo(BaseCreature target) {
        return _behavior.subtractCost(target);
    }
}
