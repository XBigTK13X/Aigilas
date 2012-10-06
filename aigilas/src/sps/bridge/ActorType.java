package sps.bridge;

import aigilas.management.SpriteType;
import sps.core.RNG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ActorType {
    NONPLAYER(null),
    PLAYER(SpriteType.PLAYER_STAND),
    MINION(SpriteType.MINION),
    ACID_NOZZLE(SpriteType.MINION),
    PEON(SpriteType.CREATURE, true),
    ZORB(SpriteType.ZORB, true),
    DART_TRAP(SpriteType.MINION),
    WRATH(SpriteType.WRATH),
    HAND(SpriteType.HAND),
    ENVY(SpriteType.ENVY),
    PRIDE(SpriteType.PRIDE),
    SLOTH(SpriteType.SLOTH),
    GREED(SpriteType.GREED),
    GLUTTONY(SpriteType.GLUTTONY),
    LUST(SpriteType.LUST),
    SERPENT(SpriteType.SLOTH),
    BREAKING_WHEEL(SpriteType.WHEEL),
    WRATH_ACOLYTE(SpriteType.WRATH_ACOLYTE, true),
    ENVY_ACOLYTE(SpriteType.ENVY_ACOLYTE, true),
    PRIDE_ACOLYTE(SpriteType.PRIDE_ACOLYTE, true),
    SLOTH_ACOLYTE(SpriteType.SLOTH_ACOLYTE, true),
    GREED_ACOLYTE(SpriteType.GREED_ACOLYTE, true),
    GLUTTONY_ACOLYTE(SpriteType.GLUTTONY_ACOLYTE, true),
    LUST_ACOLYTE(SpriteType.LUST_ACOLYTE, true);

    public final SpriteType Sprite;

    private final boolean Generatable;

    private ActorType(SpriteType sprite) {
        this(sprite, false);
    }

    public static final List<ActorType> Randoms = Arrays.asList(ActorType.PEON, ActorType.ZORB);


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
}
