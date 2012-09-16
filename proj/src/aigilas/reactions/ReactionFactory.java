package aigilas.reactions;

import aigilas.reactions.impl.*;

public class ReactionFactory {
    public static IReaction Create(ReactionId id) {
        switch (id) {
            case SWEAT:
                return new SweatReaction();
            case MAGMA:
                return new MagmaReaction();
            case EXPLOSION:
                return new ExplosionReaction();
            case SCORCH:
                return new ScorchReaction();
            case BLIND:
                return new BlindReaction();
            case LACTIC_ACID:
                return new LacticAcidReaction();
            case MIND_BLOWN:
                return new MindBlownReaction();
            case VENT:
                return new VentReaction();
            case DROWN:
                return new DrownReaction();
            case REFLECT:
                return new ReflectReaction();
            case DRENCH:
                return new DrenchReaction();
            case PNEUMONIA:
                return new PneumoniaReaction();
            case LOBOTOMY:
                return new LobotomyReaction();
            case RUST:
                return new RustReaction();
            case PURIFY:
                return new PurifyReaction();
            case ECLIPSE:
                return new EclipseReaction();
            case RESPECT:
                return new RespectReaction();
            case CRAFTSMAN:
                return new CraftsmanReaction();
            case FLASH:
                return new FlashReaction();
            case METABOLISM:
                return new MetabolismReaction();
            case FAST_FORWARD:
                return new FastForwardReaction();
            case BLANK:
                return new BlankReaction();
            case YIN_YANG:
                return new YinYangReaction();
            case EXPOSE:
                return new ExposeReaction();
            case ENLIGHTEN:
                return new EnlightenReaction();
            case ATROPHY:
                return new AtrophyReaction();
            case NEUROSIS:
                return new NeurosisReaction();
            case CONFUSE:
                return new ConfuseReaction();
            default:
                return null;
        }
    }
}
