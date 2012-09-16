package aigilas.states;

import aigilas.dungeons.DungeonFactory;
import spx.entities.EntityManager;
import spx.net.Client;
import spx.states.State;

public class GameplayState implements State {
    public GameplayState() {
        System.out.println("Generating the dungeon...");
        EntityManager.reset();
        DungeonFactory.start();
        Client.get().dungeonHasLoaded();
    }

    @Override
    public void update() {
        EntityManager.update();
    }

    @Override
    public void loadContent() {
        EntityManager.loadContent();
    }

    @Override
    public void draw() {
        EntityManager.draw();
    }
}
