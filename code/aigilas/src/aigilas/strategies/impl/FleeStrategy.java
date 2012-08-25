package aigilas.strategies.impl; import com.badlogic.gdx.graphics.Color;import spx.core.Point2;import aigilas.creatures.ICreature;import aigilas.strategies.IStrategy;import aigilas.strategies.Strategy;public class FleeStrategy extends IStrategy {	public FleeStrategy(ICreature parent, int... targetTypes)	{		super(parent, Strategy.Flee);		for (int targetType : targetTypes) {			_targets.AddTargetTypes(targetType);		}	}	private Point2 _transfer = new Point2(0, 0);	@Override	public void Act() {		if (AbleToMove()) {			_transfer = targetPath.GetNextMove();			if (_transfer != null) {				nextMove.Copy(_parent.GetLocation().Add(						_transfer.Minus(_parent.GetLocation()).Rotate180()));				_parent.MoveTo(nextMove);			}		}	}}