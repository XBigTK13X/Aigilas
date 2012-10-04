package aigilas.skills;

import aigilas.entities.Elements;
import sps.bridge.ActorType;
import sps.core.RNG;
import sps.devtools.DevConsole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkillLogic {
    private static final HashMap<Elements, List<SkillId>> __elementMap = new HashMap<Elements,List<SkillId>>();
    private static final HashMap<SkillId, AnimationType> __skillAnimationMap = new HashMap<SkillId,AnimationType>();
    public static final HashMap<ActorType, List<SkillId>> __actorToSkillMapping = new HashMap<ActorType,List<SkillId>>();
    private static final List<SkillId> __invalidRandomSkills = new ArrayList<SkillId>();
    private static int skillPick = 0;

    private SkillLogic() {
    }

    static {
        if (__elementMap.size() == 0) {
            BaseSkill skill;
            for (SkillId skillId : SkillId.values()) {
                if (skillId != SkillId.NO_SKILL) {
                    if (!skillId.Restrict) {
                        __invalidRandomSkills.add(skillId);
                    }
                    skill = SkillFactory.create(skillId);
                    for (Elements elem : skill.getElements()) {
                        if (!__elementMap.containsKey(elem)) {
                            __elementMap.put(elem, new ArrayList<SkillId>());
                        }
                        __elementMap.get(elem).add(skillId);
                    }
                    __skillAnimationMap.put(skillId, skill.getAnimationType());
                }
            }
            __skillAnimationMap.put(SkillId.NO_SKILL, null);
        }
    }

    private static SkillId getElementalSkill(Elements elementId) {
        while (__invalidRandomSkills.contains(SkillId.values()[skillPick])) {
            skillPick = RNG.next(0, __elementMap.get(elementId).size());
        }
        DevConsole.get().add(skillPick + "");
        return __elementMap.get(elementId).get(skillPick);
    }

    public static List<SkillId> getElementalSkills(ActorType actorType, List<Elements> _elementalComposition) {
        if (!__actorToSkillMapping.containsKey(actorType)) {
            __actorToSkillMapping.put(actorType, new ArrayList<SkillId>());
            for (Elements elem : _elementalComposition) {
                __actorToSkillMapping.get(actorType).add(SkillLogic.getElementalSkill(elem));
            }
        }
        return __actorToSkillMapping.get(actorType);
    }

    public static boolean isSkill(SkillId skillId, AnimationType animationType) {
        return __skillAnimationMap.get(skillId) == animationType;
    }
}
