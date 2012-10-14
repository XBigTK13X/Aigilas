package aigilas.reactions;

import aigilas.reactions.impl.*;

public class ReactionFactory {
    public static IReaction create(ReactionId id) {
        switch (id) {
            case Sweat:
                return new SweatReaction();
            case Magma:
                return new MagmaReaction();
            case Explosion:
                return new ExplosionReaction();
            case Scorch:
                return new ScorchReaction();
            case Blind:
                return new BlindReaction();
            case Lactic_Acid:
                return new LacticAcidReaction();
            case Mind_Blown:
                return new MindBlownReaction();
            case Vent:
                return new VentReaction();
            case Drown:
                return new DrownReaction();
            case Reflect:
                return new ReflectReaction();
            case Drench:
                return new DrenchReaction();
            case Pneumonia:
                return new PneumoniaReaction();
            case Lobotomy:
                return new LobotomyReaction();
            case Rust:
                return new RustReaction();
            case Purify:
                return new PurifyReaction();
            case Eclipse:
                return new EclipseReaction();
            case Respect:
                return new RespectReaction();
            case Craftsman:
                return new CraftsmanReaction();
            case Flash:
                return new FlashReaction();
            case Metabolism:
                return new MetabolismReaction();
            case Fast_Forward:
                return new FastForwardReaction();
            case Blank:
                return new BlankReaction();
            case Yin_Yang:
                return new YinYangReaction();
            case Expose:
                return new ExposeReaction();
            case Enlighten:
                return new EnlightenReaction();
            case Atrophy:
                return new AtrophyReaction();
            case Neurosis:
                return new NeurosisReaction();
            case Confuse:
                return new ConfuseReaction();
            default:
                return null;
        }
    }
}
