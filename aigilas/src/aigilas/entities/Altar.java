package aigilas.entities;

import aigilas.Aigilas;
import aigilas.creatures.impl.Player;
import aigilas.gods.God;
import aigilas.gods.GodId;
import aigilas.items.GenericItem;
import sps.bridge.*;
import sps.core.Point2;
import sps.entities.Entity;
import sps.entities.EntityManager;
import sps.entities.IActor;
import sps.text.Text;
import sps.text.TextPool;

import java.util.List;

public class Altar extends Entity {
    private final God _god;
    private IActor _currentTarget;
    private Player _player;
    private List<Entity> _offerings;
    private Text text;

    public Altar(Point2 location, GodId godName) {
        _god = godName.getInstance();
        _graphic.setColor(_god.getColor());
        initialize(location, SpriteTypes.get(Aigilas.Entities.Altar), EntityTypes.get(Aigilas.Entities.Altar), DrawDepths.get(Aigilas.Entities.Altar));
        _graphic.gotoRandomFrame(false);
    }

    @Override
    public void update() {
        _currentTarget = EntityManager.get().getTouchingCreature(this);
        if (_currentTarget != null) {
            if (_currentTarget.getActorType() == ActorTypes.get(Sps.Actors.Player)) {
                _player = (Player) _currentTarget;
                if (_player.isInteracting()) {
                    _player.pray(_god);
                }
                _offerings = EntityManager.get().getEntities(EntityTypes.get(Aigilas.Entities.Item), _location);
                for (Entity offering : _offerings) {
                    _player.sacrifice(_god, (GenericItem) offering);
                }
                if (text == null || !text.isVisible()) {
                    text = TextPool.get().write(_god.NameText, getLocation());
                }
            } else {
                _currentTarget = null;
            }

        }
        if (_currentTarget == null) {
            if (text != null) {
                text.hide();
                text = null;
            }
        }
    }

    public God getGod() {
        return _god;
    }
}
