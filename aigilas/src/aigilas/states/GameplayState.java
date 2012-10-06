package aigilas.states;

import aigilas.management.AigilasManager;
import sps.entities.Entity;
import sps.entities.EntityManager;
import sps.particles.ParticleEngine;
import sps.states.State;
import sps.states.StateManager;
import sps.text.TextManager;

import java.util.ArrayList;
import java.util.List;

public class GameplayState implements State {
    public GameplayState() {
        AigilasManager.get().reset();
    }

    List<Entity> players = new ArrayList<Entity>();

    @Override
    public void update() {
        players = EntityManager.get().getPlayers();
        boolean allDead = true;
        for (Entity player : players) {
            if (player.isActive()) {
                allDead = false;
            }
        }
        if (allDead) {
            StateManager.loadState(new GameOverState());
        }
        EntityManager.get().update();
    }

    @Override
    public void loadContent() {
        EntityManager.get().loadContent();
    }

    @Override
    public void unload() {
        EntityManager.get().clear();
        ParticleEngine.reset();
        TextManager.clear();
    }

    @Override
    public void draw() {
        EntityManager.get().draw();
    }
}
