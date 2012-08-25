package aigilas.skills.behaviors; import com.badlogic.gdx.graphics.Color;import spx.core.Point2;import aigilas.creatures.ICreature;import aigilas.entities.SkillEffect;import aigilas.skills.AnimationType;import aigilas.skills.ISkill;public class SelfBehavior extends SkillBehavior {	public SelfBehavior(int effectGraphic, ISkill parentSkill) {		super(effectGraphic, AnimationType.SELF, parentSkill);	}	@Override	public void Activate(ICreature target) {		if (SubtractCost(target)) {			_sideEffects.Generate(target.GetLocation(), new Point2(0, 0),					target);		}	}	@Override	public boolean AffectTarget(ICreature source, SkillEffect graphic) {		if (!_used) {			source.React(_parent.GetSkillId());			_parent.Affect(source);			_used = true;		}		return true;	}}