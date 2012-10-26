package aigilas.reactions;


public enum Reaction {
    Sweat,
    Magma,
    Explosion,
    Scorch,
    Blind,
    Lactic_Acid,
    Mind_Blown,
    Vent,
    Drown,
    Reflect,
    Drench,
    Pneumonia,
    Lobotomy,
    Rust,
    Purify,
    Eclipse,
    Respect,
    Craftsman,
    Flash,
    Metabolism,
    Fast_Forward,
    Blank,
    Yin_Yang,
    Expose,
    Enlighten,
    Atrophy,
    Neurosis,
    Confuse;

    public ReactionInfo Info;

    public static Reaction get(String name) {
        for (Reaction s : values()) {
            if (s.toString().replace("_", "").equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }
}
