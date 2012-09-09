package aigilas.skills.impl; import spx.bridge.EntityType;import spx.entities.IEntity;import aigilas.creatures.StatType;import aigilas.dungeons.DungeonFactory;import aigilas.entities.Elements;import aigilas.skills.AnimationType;import aigilas.skills.ISkill;import aigilas.skills.SkillId;public class WallPunchSkill extends ISkill {	public WallPunchSkill()	{		super(SkillId.WALL_PUNCH, AnimationType.RANGED);		Add(Elements.EARTH);		AddCost(StatType.MANA, 10);	}	@Override	public void Affect(IEntity target)	{		if (target.GetEntityType() == EntityType.WALL) {			if (target.GetLocation().GridX > 0					&& target.GetLocation().GridX < DungeonFactory.BlocksWide - 1					&& target.GetLocation().GridY > 0					&& target.GetLocation().GridY < DungeonFactory.BlocksHigh - 1) {				target.SetInactive();			}		}	}}