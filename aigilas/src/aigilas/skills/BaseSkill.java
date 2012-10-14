package aigilas.skills;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.entities.Extensions;
import aigilas.entities.SkillEffect;
import aigilas.management.SpriteType;
import aigilas.skills.behaviors.SkillBehavior;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;
import com.badlogic.gdx.graphics.Color;
import sps.bridge.EntityType;
import sps.entities.Entity;
import sps.entities.EntityManager;

import java.util.List;

public abstract class BaseSkill {
    protected BaseCreature _source;
    protected final SkillId _id;
    protected final SkillBehavior _behavior;
    protected final SkillComponents _components;

    protected BaseSkill(SkillId implementationId, AnimationType animation, float strength, boolean isPersistent, SpriteType effectGraphic) {
        _id = implementationId;
        _components = new SkillComponents(strength, isPersistent);
        _behavior = SkillFactory.create(animation, effectGraphic, this);
        for (Elements element : _id.Info.Elements) {
            add(element);
        }
        addCost(_id.Info.Stat, _id.Info.Cost);
    }

    protected BaseSkill(SkillId implementationId, AnimationType animation, float strength, boolean isPersistent) {
        this(implementationId, animation, strength, isPersistent, SpriteType.Skill_Effect);
    }

    protected BaseSkill(SkillId implementationId, AnimationType animation, float strength) {
        this(implementationId, animation, strength, false, SpriteType.Skill_Effect);
    }

    protected BaseSkill(SkillId implementationId, AnimationType animation) {
        this(implementationId, animation, SkillEffect.DefaultStrength, false, SpriteType.Skill_Effect);
    }

    protected void add(Elements... elements) {
        _components.addElements(elements);
    }

    protected void addCost(StatType stat, float cost) {
        _behavior.addCost(stat, cost);
    }

    public void setBuff(StatType stat, float amount) {
        _components.setBuff(stat, amount);
    }

    public void activate(BaseCreature source) {
        _source = source;
        _behavior.activate(source);
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

    public List<Elements> getElements() {
        return _components.getElements();
    }

    public void affect(BaseCreature target) {

    }

    public SpriteType getSpriteType() {
        return _behavior.getSpriteType();
    }

    public AnimationType getAnimationType() {
        return _behavior.getAnimationType();
    }

    public float getStrength() {
        return _components.getStrength();
    }

    public boolean isPersistent() {
        return _components.isPersistent();
    }

    public Color getElementColor() {
        return _components.getElements().get(0).Tint;
    }

    public void cleanup(Entity target, SkillEffect source) {
        _behavior.cleanup(target, source);
    }

    public boolean affectTarget(BaseCreature target, SkillEffect graphic) {
        return _behavior.affectTarget(target, graphic);
    }

    public boolean isActive() {
        return _behavior.isActive();
    }

    public List<EntityType> getTargetTypes() {
        return _components.getTargetTypes();
    }

    public SkillId getSkillId() {
        return _id;
    }

    public float getCost() {
        return _behavior.getCost();
    }
}
