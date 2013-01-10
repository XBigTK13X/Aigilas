package sps.bridge;

public class ActorType {
    public final SpriteType Sprite;
    public final String Name;
    public final boolean Generatable;

    public ActorType(String name, SpriteType sprite, boolean canBeGenerated) {
        Sprite = sprite;
        Generatable = canBeGenerated;
        Name = name;
    }

    public ActorType(String name, SpriteType sprite) {
        this(name, sprite, false);
    }

    public boolean is(String name) {
        return Name.equalsIgnoreCase(name);
    }
}
