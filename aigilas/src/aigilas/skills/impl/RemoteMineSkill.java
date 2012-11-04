package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;

import java.util.HashMap;

public class RemoteMineSkill extends BaseSkill {
    private static final HashMap<BaseCreature, RemoteMineSkill> _cache = new HashMap<BaseCreature, RemoteMineSkill>();

    public RemoteMineSkill()

    {
        super(SkillId.Remote_Mine, AnimationType.STATIONARY);


    }

    @Override
    public void activate(BaseCreature source) {
        if (!_cache.containsKey(source)) {
            _cache.put(source, this);


        }
        else {
            if (_cache.get(source).behavior().isActive()) {
                _cache.get(source).explode(source);
                _cache.get(source).cleanup(source, null);
                _cache.remove(source);
                cleanup(source, null);

            }
            else {
                _cache.remove(source);
                _cache.put(source, this);


            }

        }

    }

    private void explode(BaseCreature source) {
        if (_behavior.getGraphic() != null) {
            CreatureFactory.createMinion(SkillId.Explode, _source, _behavior.getGraphic(), _behavior.getGraphic().getLocation());

        }

    }

}
