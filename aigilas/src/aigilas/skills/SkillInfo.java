package aigilas.skills;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;

import java.util.List;

public class SkillInfo {
    public final String Name;
    public final StatType Stat;
    public final float Cost;
    public final float Magnitude;
    public final List<Elements> Elements;
    public final boolean Restrict;
    public SkillInfo(String name, StatType stat, float cost, float magnitude, List<Elements> elements, boolean bossOnly) {
        Name = name;
        Stat = stat;
        Cost = cost;
        Magnitude = magnitude;
        Elements = elements;
        Restrict = bossOnly;
    }
}
