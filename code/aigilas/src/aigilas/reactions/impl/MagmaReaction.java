package aigilas.reactions.impl; import com.badlogic.gdx.graphics.Color;import aigilas.creatures.ICreature;import aigilas.reactions.IReaction;public class MagmaReaction implements IReaction {	@Override	public void Affect(ICreature target)	{		target.ApplyDamage(30f);	}}