package aigilas.statuses;

public enum Status {
    Poison,
    Regen,
    StrengthUp,
    Confusion,
    WeakKnees,
    VenomFist,
    Mutiny,
    EnergyUp,
    SpeedUp,
    Electrify,
    Zap,
    PoisonOneHit,
    DefenseUp,
    ColdShoulder,
    Burn,
    Flee,
    Berserk,
    RandomBuff,
    Hord,
    LockSkillCycle,
    WeakenStrength,
    SelfMutilation,
    PreventMentalUsage,
    PreventRegeneration,
    PreventLightUsage,
    PreventDarkUsage,
    IntDown,
    SoakingWet,
    HealReflect,
    Mute,
    SlowDown,
    WeakMuscles,
    Blind,
    Toxic,
    Boil;

    public StatusInfo Info;

    public static Status get(String name) {
        for (Status s : values()) {
            if (s.toString().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }
}
