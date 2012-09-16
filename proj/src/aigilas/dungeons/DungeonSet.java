package aigilas.dungeons;

import spx.particles.ParticleEngine;

import java.util.HashMap;

public class DungeonSet {
    private int _currentFloor = 0;
    private final HashMap<Integer, Dungeon> _floors = new HashMap<Integer, Dungeon>();

    /*
      * This whole "area" thing is very messy and doesn't work:an intuitive way.
      * In order to pre-load, we need to make sure we can easily get back to
      * start.
      */
    public DungeonSet() {
        _floors.put(_currentFloor, new Dungeon());
    }

    public void gotoNext() {
        _floors.get(_currentFloor).cacheContents();
        _currentFloor++;
        loadOrCreateDungeon(false);
    }

    public boolean gotoPrevious() {
        if (_currentFloor > 0) {
            _floors.get(_currentFloor).cacheContents();
            _currentFloor--;
            loadOrCreateDungeon(true);
            return true;
        }
        return false;
    }

    private void loadOrCreateDungeon(boolean goingUp) {
        if (!_floors.containsKey(_currentFloor)) {
            System.out.println("Creating a new dungeon for floor: " + _currentFloor);
            _floors.put(_currentFloor, new Dungeon(_floors.get(_currentFloor - 1).getDownstairsLocation()));
            DungeonFactory.increaseFloorCount();
        }
        ParticleEngine.reset();
        _floors.get(_currentFloor).loadTiles(goingUp);
    }
}
