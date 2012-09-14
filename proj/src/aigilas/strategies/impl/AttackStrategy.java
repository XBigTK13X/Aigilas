package aigilas.strategies.impl;

import aigilas.skills.SkillLogic;
import spx.bridge.ActorType;
import aigilas.creatures.ICreature;
import aigilas.skills.AnimationType;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;

public class AttackStrategy extends IStrategy {
	private int _skillCooldown = 0;
	private int _skillCooldownMax = 10;

	public AttackStrategy(ICreature parent, ActorType... targetTypes)

	{
		super(parent, Strategy.Attack);

		for (ActorType targetType : targetTypes) {
			_targets.AddTargetTypes(targetType);
		}
	}

	@Override
	public void Act() {
		if (AbleToMove()) {
			_skillCooldown--;
			if (_skillCooldown <= 0) {
				_parent.CycleActiveSkill(1);
				if (SkillLogic.IsSkill(_parent.GetActiveSkill(), AnimationType.RANGED)) {
					if (opponent != null) {
						_parent.SetSkillVector(CalculateTargetVector(_parent.GetLocation(), opponent.GetLocation()));
					}
					if (_parent.GetSkillVector().GridX != 0 || _parent.GetSkillVector().GridY != 0) {
						_parent.UseActiveSkill();
					}
				}
				else {
					_parent.UseActiveSkill();
				}
				_skillCooldown = _skillCooldownMax;
			}
			if (targetPath.HasMoves()) {
				nextMove.Copy(targetPath.GetNextMove());
				_parent.MoveTo(nextMove);
			}
		}
	}
}
