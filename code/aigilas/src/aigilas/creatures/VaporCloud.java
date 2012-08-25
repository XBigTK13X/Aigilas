package aigilas.creatures; import com.badlogic.gdx.graphics.Color;import spx.entities.EntityManager;import spx.entities.IActor;import aigilas.entities.Elements;import aigilas.skills.SkillId;import aigilas.strategies.Strategy;import aigilas.strategies.StrategyFactory;public class VaporCloud extends Minion {	private ICreature _host = null;	public VaporCloud() {		super(AigilasActorType.MINION);		_strategy = StrategyFactory.Create(Strategy.MinionCloud, this);		Add(SkillId.VAPOR_CLOUD);		_composition.add(Elements.WATER);	}	@Override	public void Update() {		super.Update();		if (_host == null) {			for (IActor creature : EntityManager.GetActorsAt(_location)) {				if (creature != this) {					_host = (ICreature) creature;				}			}			if (_host == null) {				SetInactive();			}		}		if (_host != null) {			SetLocation(_host.GetLocation());		}	}}