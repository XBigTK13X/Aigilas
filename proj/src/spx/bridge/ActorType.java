package spx.bridge;

import aigilas.management.SpriteType;

public enum ActorType {
    NONPLAYER(null),
    PLAYER(SpriteType.PLAYER_STAND),
    MINION(SpriteType.MINION),
    ACID_NOZZLE(SpriteType.MINION),
    PEON(SpriteType.CREATURE),
    ZORB(SpriteType.ZORB),
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
    BREAKING_WHEEL(SpriteType.WHEEL);

    public final SpriteType Sprite;

    private ActorType(SpriteType sprite) {
        Sprite = sprite;
    }
}