package sps.entities;

import aigilas.Common;
import sps.bridge.ActorType;
import sps.bridge.ActorTypes;
import sps.bridge.EntityType;
import sps.bridge.EntityTypes;
import sps.core.Core;
import sps.core.Point2;
import sps.core.RNG;
import sps.core.Settings;
import sps.graphics.Renderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class EntityManager {

    public static EntityManager __instance;

    public static EntityManager get() {
        if (__instance == null) {
            __instance = new EntityManager();
        }
        return __instance;
    }

    public static void reset() {
        __instance = new EntityManager();
    }

    private EntityManager() {
    }

    private List<Entity> _contents = new ArrayList<Entity>();
    private HashMap<Point2, List<Entity>> _gridContents = new HashMap<Point2, List<Entity>>();

    public Entity addObject(Entity Entity) {
        Entity.loadContent();
        _contents.add(Entity);
        Collections.sort(_contents);
        addToGrid(Entity);
        return Entity;
    }

    public void addObjects(List<Entity> cache) {
        _contents.addAll(cache);
        Collections.sort(_contents);
    }

    public Entity getObject(EntityType type) {
        if (_contents != null) {
            for (Entity entity : _contents) {
                if (entity.getEntityType() == type) {
                    return entity;
                }
            }
        }
        return null;
    }

    private final List<Entity> _gopResults = new ArrayList<Entity>();

    public List<Entity> getEntities(EntityType type, Point2 target)

    {
        if (_contents != null) {
            _goResults.clear();
            _goResults.addAll(getObjects(type));
            _gopResults.clear();
            for (Entity goResult : _goResults) {
                if (goResult.contains(target)) {
                    _gopResults.add(goResult);
                }
            }
            return _gopResults;
        }
        return null;
    }

    private final List<Entity> _goResults = new ArrayList<Entity>();

    public List<Entity> getObjects(EntityType type) {
        _goResults.clear();
        for (Entity _content : _contents) {
            if (_content.getEntityType() == type) {
                _goResults.add(_content);
            }
        }
        return _goResults;
    }

    private final List<IActor> _creatures = new ArrayList<IActor>();

    public List<IActor> getActors(ActorType type) {
        _creatures.clear();
        if (type != ActorTypes.get(Core.Non_Player)) {
            for (Entity elem : _contents) {
                if (elem.getEntityType() == EntityTypes.get(Common.Actor)) {
                    if (((IActor) elem).getActorType() == type) {
                        _creatures.add(((IActor) elem));
                    }
                }
            }
        }
        else {
            for (Entity elem : _contents) {
                if (elem.getEntityType() == EntityTypes.get(Common.Actor)) {
                    if (((IActor) elem).getActorType() != ActorTypes.get(Core.Player)) {
                        _creatures.add(((IActor) elem));
                    }
                }
            }
        }
        return _creatures;
    }

    private IActor _nextResult;

    public List<IActor> getActorsAt(Point2 target, ActorType actorType) {
        _creatures.clear();
        for (Entity elem : _gridContents.get(target)) {
            if (elem.getEntityType() == EntityTypes.get(Common.Actor)) {
                _nextResult = (IActor) elem;
                if (actorType == null || _nextResult.getActorType() == actorType || (actorType == ActorTypes.get(Core.Non_Player) && _nextResult.getActorType() != ActorTypes.get(Core.Player))) {
                    _creatures.add(_nextResult);
                }
            }
        }
        return _creatures;
    }

    public List<IActor> getActorsAt(Point2 target) {
        return getActorsAt(target, null);
    }

    private final List<IActor> _creatures2 = new ArrayList<IActor>();

    public List<IActor> getActorsSurrounding(Point2 target, int distance)

    {
        _creatures2.clear();
        for (int ii = -distance; ii < distance + 1; ii++) {
            for (int jj = -distance; jj < distance + 1; jj++) {
                if (ii != 0 || jj != 0) {
                    for (IActor creature : getActorsAt(target.add(new Point2(ii, jj)), null)) {
                        _creatures2.add(creature);
                    }
                }
            }
        }
        return _creatures2;
    }

    public boolean isLocationBlocked(Point2 location) {
        for (Entity elem : _gridContents.get(location)) {
            if (elem.isBlocking()) {
                return true;
            }
        }
        return false;
    }

    public IActor getNearestPlayer(Entity target) {
        List<IActor> actors = getActors(ActorTypes.get(Core.Player));
        if (actors.size() > 0) {
            Entity closest = (Entity) actors.get(0);
            Entity player;
            for (IActor actor : actors) {
                player = (Entity) actor;
                if (HitTest.getDistanceSquare(target, player) < HitTest.getDistanceSquare(target, closest)) {
                    closest = player;
                }
            }
            return (IActor) closest;
        }
        return null;
    }

    public boolean anyContains(Point2 target, EntityType type) {
        for (Entity entity : _gridContents.get(target)) {
            if (entity.getEntityType() == type) {
                return true;
            }
        }
        return false;
    }

    public void removeObject(Entity target) {
        _contents.remove(target);
    }

    public void clear() {
        _contents = new ArrayList<Entity>();
        _gridContents = new HashMap<Point2, List<Entity>>();
    }

    public void update() {
        for (int ii = 0; ii < _contents.size(); ii++) {
            if (ii >= _contents.size()) {
                return;
            }
            if (!_contents.get(ii).isActive()) {
                _gridContents.get(_contents.get(ii).getLocation()).remove(_contents.get(ii));
                _contents.remove(_contents.get(ii));
                ii--;
                continue;
            }
            _contents.get(ii).update();
        }
    }

    public void draw() {
        if (Renderer.get() != null) {
            for (Entity component : _contents) {
                component.draw();
            }
        }
    }

    public void loadContent() {
        if (Renderer.get() != null) {
            for (Entity component : _contents) {
                component.loadContent();
            }
        }
    }

    private void addToGrid(Entity entity) {
        if (!_gridContents.containsKey(entity.getLocation())) {
            _gridContents.put(entity.getLocation(), new ArrayList<Entity>());
        }
        _gridContents.get(entity.getLocation()).add(entity);
    }

    public void updateGridLocation(Entity Entity, Point2 oldLocation) {
        if (_gridContents != null && oldLocation != null) {
            _gridContents.get(oldLocation).remove(Entity);
            addToGrid(Entity);
        }
    }

    private final List<Entity> _players = new ArrayList<Entity>();

    public List<Entity> getPlayers() {
        _players.clear();
        for (Entity tile : _contents) {
            if (tile.getEntityType() == EntityTypes.get(Common.Actor) && ((IActor) tile).getActorType() == ActorTypes.get(Core.Player)) {
                _players.add(tile);
            }
        }
        return _players;
    }

    public Point2 getEmptyLocation() {
        List<Point2> emptyLocations = new ArrayList<Point2>();
        for (Point2 location : _gridContents.keySet()) {
            if (location.GridX > 0 && location.GridY > 0 && location.GridX < Settings.get().tileMapWidth - 1 && location.GridY < Settings.get().tileMapHeight - 1) {
                boolean exclude = false;
                for (int ii = 0; ii < _gridContents.get(location).size(); ii++) {
                    if (_gridContents.get(location).get(ii).getEntityType() == EntityTypes.get(Common.Actor)) {
                        exclude = true;
                    }
                }
                if (!exclude) {
                    emptyLocations.add(location);
                }
            }
        }
        return emptyLocations.get(RNG.next(0, emptyLocations.size()));
    }

    public List<Entity> getEntitiesToCache() {
        List<Entity> results = new ArrayList<Entity>();
        for (Entity _content : _contents) {
            if (_content.getEntityType() != EntityTypes.get(Common.Floor)) {
                results.add(_content);
            }
        }
        return results;
    }

    public IActor getTouchingCreature(Entity entity) {
        for (Entity _content : _contents) {
            if (_content.getEntityType() == EntityTypes.get(Common.Actor)) {
                if (_content.contains(entity.getLocation())) {
                    return (IActor) _content;
                }
            }
        }
        return null;
    }
}
