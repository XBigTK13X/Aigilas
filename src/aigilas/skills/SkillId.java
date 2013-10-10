package aigilas.skills;

public enum SkillId {
    Absorb,
    Acid_Drip,
    Acid_Nozzle,
    Boil,
    Breaking_Wheel,
    Brimstone,
    Cavalry,
    Cold_Shoulder,
    Combust,
    Confusion,
    Dart,
    Dart_Trap,
    Dismemberment,
    Electrify,
    Energy_Up,
    Envenom,
    Explode,
    Fireball,
    Flame_Hammer,
    Floor_Spikes,
    Forget_Skill,
    Gush,
    Horder,
    Horrify,
    Hypothermia,
    Ice_Shard,
    Magic_Map,
    Mimic,
    Mutiny,
    No_Skill,
    Plague,
    Poison_Cloud,
    Regen_All,
    Remote_Mine,
    Serpent_Supper,
    Soul_Crush,
    Soul_Reinforcement,
    Spawn_Item,
    Speed_Up,
    Steal_Item,
    Strength_Up,
    Throw_Item,
    Valedictorian,
    Vapor_Cloud,
    Vapor_Implant,
    Venom_Fist,
    Wall_Punch,
    Weak_Knees;


    public SkillInfo Info;

    public static SkillId get(String value) {
        for (SkillId id : values()) {
            if (id.toString().equalsIgnoreCase(value)) {
                return id;
            }
        }
        return null;
    }
}
