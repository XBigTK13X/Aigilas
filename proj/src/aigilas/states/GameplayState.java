package aigilas.states;

import aigilas.dungeons.DungeonFactory;
import spx.entities.EntityManager;
import spx.entities.IActor;
import spx.net.Client;
import spx.particles.ParticleEngine;
import spx.states.State;
import spx.states.StateManager;
import spx.text.TextManager;

import java.util.ArrayList;
import java.util.List;

public class GameplayState implements State {
    public GameplayState() {
        System.out.println("Generating the dungeon...");
        EntityManager.reset();
        DungeonFactory.start();
        Client.get().dungeonHasLoaded();
    }

    List<IActor> players = new ArrayList<>();
    @Override
    public void update() {
        players = EntityManager.getPlayers();
        boolean allDead = true;
        for(IActor player:players){
            if(player.isActive()){
                allDead = false;
            }
        }
        if(allDead){
            StateManager.loadState(new GameOverState());
        }
        EntityManager.update();
    }

    @Override
    public void loadContent() {
        EntityManager.loadContent();
    }

    @Override
    public void unload() {
        EntityManager.clear();
        ParticleEngine.reset();
        TextManager.clear();
    }

    @Override
    public void draw() {
        EntityManager.draw();
    }
}
