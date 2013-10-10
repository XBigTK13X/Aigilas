package aigilas.skills;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;

import java.util.List;

public class SkillInfo {
    public final String Name;
    public final StatType Stat;
    public final int Cost;
    public final int Magnitude;
    public final List<Elements> Elements;
    public final boolean Restrict;
    public final boolean OffCenter;

    public SkillInfo(String name, StatType stat, int cost, int magnitude, List<Elements> elements, boolean restrict, boolean offCenter) {
        Name = name;
        Stat = stat;
        Cost = cost;
        Magnitude = magnitude;
        Elements = elements;
        Restrict = restrict;
        OffCenter = offCenter;
    }
}
