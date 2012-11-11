package aigilas.skills;

import aigilas.entities.Elements;
import sps.bridge.ActorType;
import sps.core.RNG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkillLogic {
    private static SkillLogic __instance;

    public static SkillLogic get() {
        if (__instance == null) {
            __instance = new SkillLogic();
        }
        return __instance;
    }

    private SkillLogic() {
        if (__elementMap.size() == 0) {
            BaseSkill skill;
            for (SkillId skillId : SkillId.values()) {
                if (skillId != SkillId.No_Skill) {
                    if (!skillId.Info.Restrict) {
                        __invalidRandomSkills.add(skillId);
                    }
                    skill = SkillFactory.create(skillId);
                    for (Elements elem : skill.components().getElements()) {
                        if (!__elementMap.containsKey(elem)) {
                            __elementMap.put(elem, new ArrayList<SkillId>());
                        }
                        __elementMap.get(elem).add(skillId);
                    }
                    __skillAnimationMap.put(skillId, skill.behavior().getAnimationType());
                }
            }
            __skillAnimationMap.put(SkillId.No_Skill, null);
        }
    }

    private final HashMap<Elements, List<SkillId>> __elementMap = new HashMap<Elements, List<SkillId>>();
    private final HashMap<SkillId, AnimationType> __skillAnimationMap = new HashMap<SkillId, AnimationType>();
    public final HashMap<ActorType, List<SkillId>> __actorToSkillMapping = new HashMap<ActorType, List<SkillId>>();
    private final List<SkillId> __invalidRandomSkills = new ArrayList<SkillId>();
    private int skillPick = 0;

    static {

    }

    private SkillId getElementalSkill(Elements elementId) {
        while (__invalidRandomSkills.contains(SkillId.values()[skillPick])) {
            skillPick = RNG.next(0, __elementMap.get(elementId).size() - 1);
        }
        return __elementMap.get(elementId).get(skillPick);
    }

    public List<SkillId> getElementalSkills(ActorType actorType, List<Elements> _elementalComposition) {
        if (!__actorToSkillMapping.containsKey(actorType)) {
            __actorToSkillMapping.put(actorType, new ArrayList<SkillId>());
            for (Elements elem : _elementalComposition) {
                __actorToSkillMapping.get(actorType).add(SkillLogic.get().getElementalSkill(elem));
            }
        }
        return __actorToSkillMapping.get(actorType);
    }

    public boolean isSkill(SkillId skillId, AnimationType animationType) {
        return __skillAnimationMap.get(skillId) == animationType;
    }
}
