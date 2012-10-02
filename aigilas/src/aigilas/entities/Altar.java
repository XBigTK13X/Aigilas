package aigilas.entities;

import aigilas.creatures.impl.Player;
import aigilas.gods.God;
import aigilas.gods.GodId;
import aigilas.items.GenericItem;
import aigilas.management.SpriteType;
import sps.bridge.DrawDepth;
import sps.bridge.EntityType;
import sps.core.Point2;
import sps.entities.Entity;
import sps.entities.EntityManager;
import sps.entities.IEntity;
import sps.text.ActionText;
import sps.text.TextManager;

import java.util.List;

public class Altar extends Entity {
    private final God _god;
    private Player _currentTarget;
    private List<IEntity> _offerings;

    public Altar(Point2 location, GodId godName) {
        _god = godName.getInstance();
        _graphic.setColor(_god.getColor());
        initialize(location, SpriteType.ALTAR, EntityType.ALTAR, DrawDepth.Altar);
    }

    @Override
    public void update() {
        _currentTarget = (Player) EntityManager.get().getTouchingCreature(this);
        if (_currentTarget != null) {
            if (_currentTarget.isInteracting()) {
                _currentTarget.pray(_god);
            }
            _offerings = EntityManager.get().getEntities(EntityType.ITEM, _location);
            for (IEntity offering : _offerings) {
                _currentTarget.sacrifice(_god, (GenericItem) offering);
            }
            TextManager.add(new ActionText(_god.NameText, 1, (int) getLocation().PosX, (int) getLocation().PosY));
        }
    }

    public God getGod() {
        return _god;
    }
}
