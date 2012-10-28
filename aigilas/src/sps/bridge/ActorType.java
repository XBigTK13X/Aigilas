package sps.bridge;

import aigilas.management.SpriteType;
import sps.core.RNG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ActorType {
    Non_Player(null),
    Player(SpriteType.Player_Stand),
    Friendly(null),
    Minion(SpriteType.Minion),
    Acid_Nozzle(SpriteType.Minion),
    Peon(SpriteType.Creature, true),
    Zorb(SpriteType.Zorb, true),
    Dart_Trap(SpriteType.Minion),
    Wrath(SpriteType.Wrath),
    Hand(SpriteType.Hand),
    Envy(SpriteType.Envy),
    Pride(SpriteType.Pride),
    Sloth(SpriteType.Sloth),
    Greed(SpriteType.Greed),
    Gluttony(SpriteType.Gluttony),
    Lust(SpriteType.Lust),
    Serpent(SpriteType.Sloth),
    Breaking_Wheel(SpriteType.Wheel),
    Wrath_Acolyte(SpriteType.Wrath_Acolyte, true),
    Envy_Acolyte(SpriteType.Envy_Acolyte, true),
    Pride_Acolyte(SpriteType.Pride_Acolyte, true),
    Sloth_Acolyte(SpriteType.Sloth_Acolyte, true),
    Greed_Acolyte(SpriteType.Greed_Acolyte, true),
    Gluttony_Acolyte(SpriteType.Gluttony_Acolyte, true),
    Lust_Acolyte(SpriteType.Lust_Acolyte, true),
    Dummy(SpriteType.Player_Stand);

    public final SpriteType Sprite;

    private final boolean Generatable;

    private ActorType(SpriteType sprite) {
        this(sprite, false);
    }

    public static final List<ActorType> Randoms = Arrays.asList(ActorType.Peon, ActorType.Zorb);


    private static List<ActorType> __randoms = new ArrayList<ActorType>();

    private ActorType(SpriteType sprite, boolean canBeGenerated) {
        Sprite = sprite;
        Generatable = canBeGenerated;
    }

    public static ActorType getRandomGeneratable() {
        if (__randoms.size() == 0) {
            for (ActorType a : ActorType.values()) {
                if (a.Generatable) {
                    __randoms.add(a);
                }
            }
        }
        return __randoms.get(RNG.Rand.nextInt(__randoms.size()));
    }

    public static ActorType get(String value) {
        for (ActorType actor : values()) {
            if (actor.name().replace("_", "").equalsIgnoreCase(value)) {
                return actor;
            }
        }
        return null;
    }
}
