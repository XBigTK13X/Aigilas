using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OGUR.Reactions
{
    public class ReactionId
    {
        public const int SWEAT = 0;
        public const int MAGMA = 1;
        public const int EXPLOSION = 2;
        public const int SCORCH = 3;
        public const int BLIND = 4;
        public const int LACTIC_ACID = 5;
        public const int MIND_BLOWN = 6;
        public const int VENT = 7;
        public const int DROWN = 8;
        public const int REFLECT = 9;
        public const int DRENCH = 10;
        public const int PNEUMONIA = 11;
        public const int LOBOTOMY = 12;
        public const int RUST = 13;
        public const int PURIFY = 14;
        public const int ECLIPSE = 15;
        public const int RESPECT = 16;
        public const int CRAFTSMAN = 17;
        public const int FLASH = 18;
        public const int METABOLISM = 19;
        public const int FAST_FORWARD = 20;
        public const int BLANK = 21;
        public const int YIN_YANG = 22;
        public const int EXPOSE = 23;
        public const int ENLIGHTEN = 24;
        public const int ATROPHY = 25;
        public const int NEUROSIS = 26;
        public const int CONFUSE = 27;

        public static readonly List<string> Names = new List<string>()
        {
            "SWEAT",
            "MAGMA",
            "EXPLOSION",
            "SCORCH",
            "BLIND",
            "LACTIC ACID",
            "MIND BLOWN",
            "VENT",
            "DROWN",
            "REFLECT",
            "DRENCH",
            "PNEUMONIA",
            "LOBOTOMY",
            "RUST",
            "PURIFY",
            "ECLIPSE",
            "RESPECT",
            "CRAFTSMAN",
            "FLASH",
            "METABOLISM",
            "FAST FORWARD",
            "BLANK",
            "YIN YANG",
            "EXPOSE",
            "ENLIGHTEN",
            "ATROPHY",
            "NEUROSIS",
            "CONFUSE"
        };
    }
    public class ReactionFactory
    {
        public static Reaction Create(int id)
        {
            switch(id)
            {
                case ReactionId.SWEAT: return new SweatReaction();
                case ReactionId.MAGMA: return new MagmaReaction();
                case ReactionId.EXPLOSION: return new ExplosionReaction();
                case ReactionId.SCORCH: return new ScorchReaction();
                case ReactionId.BLIND: return new BlindReaction();
                case ReactionId.LACTIC_ACID: return new LacticAcidReaction();
                case ReactionId.MIND_BLOWN: return new MindBlownReaction();
                case ReactionId.VENT: return new VentReaction();
                case ReactionId.DROWN: return new DrownReaction();
                case ReactionId.REFLECT: return new ReflectReaction();
                case ReactionId.DRENCH: return new DrenchReaction();
                case ReactionId.PNEUMONIA: return new PneumoniaReaction();
                case ReactionId.LOBOTOMY: return new LobotomyReaction();
                case ReactionId.RUST: return new RustReaction();
                case ReactionId.PURIFY: return new PurifyReaction();
                case ReactionId.ECLIPSE: return new EclipseReaction();
                case ReactionId.RESPECT: return new RespectReaction();
                case ReactionId.CRAFTSMAN: return new CraftsmanReaction();
                case ReactionId.FLASH: return new FlashReaction();
                case ReactionId.METABOLISM: return new MetabolismReaction();
                case ReactionId.FAST_FORWARD: return new FastForwardReaction();
                case ReactionId.BLANK: return new BlankReaction();
                case ReactionId.YIN_YANG: return new YinYangReaction();
                case ReactionId.EXPOSE: return new ExposeReaction();
                case ReactionId.ENLIGHTEN: return new EnlightenReaction();
                case ReactionId.ATROPHY: return new AtrophyReaction();
                case ReactionId.NEUROSIS: return new NeurosisReaction();
                case ReactionId.CONFUSE: return new ConfuseReaction();
                default:return null;
            }
        }
    }
}
