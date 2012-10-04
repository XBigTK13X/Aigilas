package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

import java.util.HashMap;

public class RemoteMineSkill extends BaseSkill {
    private static final HashMap<BaseCreature, RemoteMineSkill> _cache = new HashMap<BaseCreature,RemoteMineSkill>();

    public RemoteMineSkill()

    {
        super(SkillId.REMOTE_MINE, AnimationType.STATIONARY);

        addCost(StatType.MANA, 10);
        add(Elements.FIRE);

    }

    @Override
    public void activate(BaseCreature source) {
        if (!_cache.containsKey(source)) {
            _cache.put(source, this);
            super.activate(source);

        } else {
            if (_cache.get(source).isActive()) {
                _cache.get(source).explode(source);
                _cache.get(source).cleanup(source, null);
                _cache.remove(source);
                cleanup(source, null);

            } else {
                _cache.remove(source);
                _cache.put(source, this);
                super.activate(source);

            }

        }

    }

    private void explode(BaseCreature source) {
        if (_behavior.getGraphic() != null) {
            CreatureFactory.createMinion(SkillId.EXPLODE, _source, _behavior.getGraphic(), _behavior.getGraphic().getLocation());

        }

    }

}
