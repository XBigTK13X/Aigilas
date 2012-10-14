package aigilas.skills;

public enum SkillId {
    Fireball,
    No_Skill,
    Floor_Spikes,
    Dart,
    Dart_Trap,
    Acid_Drip,
    Acid_Nozzle,
    Remote_Mine,
    Vapor_Implant,
    Confusion,
    Weak_Knees,
    Venom_Fist,
    Absorb,
    Mutiny,
    Soul_Reinforcement,
    Horder,
    Spawn_Item,
    Throw_Item,
    Steal_Item,
    Flame_Hammer,
    Gush,
    Soul_Crush,
    Combust,
    Horrify,
    Forget_Skill,
    Regen_All,
    Speed_Up,
    Envenom,
    Magic_Map,
    Cold_Shoulder,
    Cavalry,
    Strength_Up,
    Mana_Up,
    Electrify,
    Wall_Punch,
    Mimic,
    Valedictorian,
    Explode,
    Vapor_Cloud,
    Dismemberment,
    Hypothermia,
    Ice_Shard,
    Plague,
    Poison_Cloud,
    Serpent_Supper,
    Breaking_Wheel,
    Boil,
    Brimstone;

    public SkillInfo Info;

    public static SkillId get(String value) {
        for (SkillId id : values()) {
            if (id.toString().replace("_", "").equalsIgnoreCase(value)) {
                return id;
            }
        }
        return null;
    }
}
