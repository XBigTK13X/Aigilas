package aigilas.management;

import aigilas.classes.PlayerClassRegistry;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.dungeons.Dungeon;
import aigilas.dungeons.DungeonFloor;
import aigilas.reactions.ReactionRegistry;
import aigilas.skills.SkillRegistry;
import aigilas.statuses.StatusRegistry;
import sps.core.Logger;
import sps.entities.EntityManager;
import sps.net.Client;

public class AigilasManager {
    private AigilasManager() {
    }

    private static AigilasManager __instance;

    public static AigilasManager get() {
        if (__instance == null) {
            __instance = new AigilasManager();
        }
        return __instance;
    }

    public void reset() {
        __instance = new AigilasManager();
        Logger.info("Generating the dungeon...");
        SkillRegistry.get();
        StatusRegistry.get();
        ReactionRegistry.get();
        PlayerClassRegistry.get();
        EntityManager.reset();
        CreatureFactory.reset();
        Dungeon.start();
        DungeonFloor.reset();
        Client.get().dungeonHasLoaded();

    }
}
