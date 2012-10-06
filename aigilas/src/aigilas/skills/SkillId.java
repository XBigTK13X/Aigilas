package aigilas.skills;

public enum SkillId {
    FIREBALL,
    NO_SKILL,
    FLOOR_SPIKES,
    DART,
    DART_TRAP,
    ACID_DRIP,
    ACID_NOZZLE,
    REMOTE_MINE,
    VAPOR_IMPLANT,
    CONFUSION,
    WEAK_KNEES,
    VENOM_FIST,
    ABSORB,
    MUTINY,
    SOUL_REINFORCEMENT,
    HORDER,
    SPAWN_ITEM,
    THROW_ITEM,
    STEAL_ITEM,
    FLAME_HAMMER,
    GUSH,
    SOUL_CRUSH,
    COMBUST,
    HORRIFY,
    FORGET_SKILL,
    REGEN_ALL,
    SPEED_UP,
    ENVENOM,
    MAGIC_MAP,
    COLD_SHOULDER,
    CAVALRY,
    STRENGTH_UP,
    MANA_UP,
    ELECTRIFY,
    WALL_PUNCH,
    MIMIC,
    VALEDICTORIAN,
    EXPLODE,
    VAPOR_CLOUD,
    DISMEMBERMENT,
    HYPOTHERMIA,
    ICE_SHARD,
    PLAGUE,
    POISON_CLOUD,
    SERPENT_SUPPER,
    BREAKING_WHEEL,
    BOIL,
    BRIMSTONE;

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
