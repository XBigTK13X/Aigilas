package aigilas.reactions.impl; import com.badlogic.gdx.graphics.Color;import aigilas.creatures.ICreature;import aigilas.reactions.IReaction;import aigilas.statuses.Status;import aigilas.statuses.StatusFactory;public class RespectReaction implements IReaction {	@Override	public void Affect(ICreature target)	{		StatusFactory.Apply(target, Status.Mutiny);	}}