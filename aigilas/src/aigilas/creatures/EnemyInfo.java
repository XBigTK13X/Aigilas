package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.skills.SkillId;

import java.util.List;

public class EnemyInfo {
    public final List<StatType> Strengths;
    public final List<StatType> Weaknesses;
    public final List<aigilas.entities.Elements> Elements;
    public final List<SkillId> Skills;

    public EnemyInfo(List<StatType> strengths, List<StatType> weaknesses, List<Elements> elements, List<SkillId> skills) {
        Strengths = strengths;
        Weaknesses = weaknesses;
        Elements = elements;
        Skills = skills;
    }
}
